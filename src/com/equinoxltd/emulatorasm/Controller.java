package com.equinoxltd.emulatorasm;

/**
 * @author dark-wizard
 */
public class Controller {
    // !! ВАЖНО !!
    // В этом коде (как и почти везде) не используется вывод в консоль через System.out.print
    // Для вывода используются функции, описанные внизу этого класса

    // флаг для переключения режимов вывода, используется для вывода отладочной информации
    public static boolean VERBOSE = true;
    private Processor _proc;
    private Memory _mem;
    public String programToProcess = "";

    public Controller() {
        _proc = new Processor();
        Processor.EIP = 0;
        _mem = new Memory(1024);
        //code to run
        //_proc.runCommand(1);
        //_proc.runCommand(0);
    }
    public void run() {
        preprocessText();
        int idx = 0;
        // while program is not ended
        while (Memory.mem[idx] != -100) {
            int size = _proc.runCommand(Memory.mem[idx]);
            if (Processor.EIP == idx)
                Processor.EIP += size;
            idx = Processor.EIP;
        }
    }
    public void searchForThisLabelOnNewLine(String labelName) {
        String lines[] = programToProcess.split("\n");
        int wordNumber = 0;
        int pos = 0;
        for (String line : lines) {
            // searching in every line
            int wordsOnLine = 0;
            String words[] = line.trim().split(" ");
            for (String word : words) {
                ++wordNumber;
                ++wordsOnLine;
                word = word.trim().toLowerCase();
                if (word.substring(0, 1).compareTo("@") == 0 && wordsOnLine == 1)
                    --wordNumber;
                if (word.compareTo(labelName) == 0 && wordsOnLine == 1) {
                    // wow, we found our label!
                    pos = wordNumber;

                }
            }
        }
        Controller.log("Adding label " + labelName + " to pos " + pos);
        Memory.addLabel(labelName, pos);
    }
    public void preprocessText() {
        // searching for any word which is not command
        String lines[] = programToProcess.split("\n");
        for (String line : lines) {
            // searching in every line
            String words[] = line.trim().split(" ");
            int wordNumber = 0;
            for (String word : words) {
                // in every word
                // our assembler is case-independent
                word = word.toLowerCase();
                ++wordNumber;
                if (word.substring(0, 1).compareTo("@") == 0) {
                    // this is label
                    // add check if it's not first
                    if (wordNumber != 1 && !Memory.hasLabel(word))
                        searchForThisLabelOnNewLine(word);
                    try {
                        int idx = Memory.getLabelIndexByName(word);
                        // wow, this label exists! add link to memory
                        if (wordNumber != 1) {
                            int pos = Memory.getFreeMemoryIndex();
                            Memory.mem[pos] = idx;
                        }
                    } catch (Error e) {
                        // this label is new, create alliance
                        int pos = Memory.getFreeMemoryIndex();
                        Memory.addLabel(word, pos);
                    }
                } else
                // if it's not a digit parameter
                if (!word.matches("[0-9]+"))
                    try {
                        // trying to get one of the errors -
                        // if it's not command and not assoc. var
                        if (_proc.hasCommand(word)) {
                            // this is processor command, load it to memory
                            int idx = Memory.getFreeMemoryIndex();
                            Memory.mem[idx] = _proc.getCommand(word).code;
                        } else {
                            //make sure it's not stack!
                            if (word.matches("st\\(.\\)")) {
                                //wow, stack found!
                                String number = word.split("st\\(")[1].substring(0, 1);
                                int idx = Memory.getFreeMemoryIndex();
                                Memory.mem[idx] = -Integer.parseInt(number) - 1;
                            } else {
                                //memory, 100%
                                int existingIndex = Memory.getIndexByName(word);
                                // here we have the index of the existing variable
                                // add it as a param to command
                                int idx = Memory.getFreeMemoryIndex();
                                Memory.mem[idx] = existingIndex;
                            }
                        }
                    } catch (Error e) {
                        // finally, allocating memory
                        int idx = Memory.getFreeMemoryIndex();
                        Memory.setNameByIndex(word, idx);
                        Memory.mem[idx] = idx;
                    }
                else {
                    int idx = Memory.getFreeMemoryIndex();
                    // this is just number param, add it to memory
                    Memory.mem[idx] = Integer.parseInt(word);
                }
            }
        }
        Controller.log("Memory:");
        Memory.dump();
    }

    // функция для отладочной информации
    public static void log(Object obj) {
        if (Controller.VERBOSE) System.out.println(obj);
    }

    // функция для вывода в обход флагу, используется для вывода важной информации
    public static void output(Object obj) {
        System.out.println(obj);
        TextEditor.runningApp.setConsoleText(obj.toString());
    }
}
