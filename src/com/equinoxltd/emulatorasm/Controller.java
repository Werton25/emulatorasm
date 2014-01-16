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
        _mem = new Memory(1024);
        //code to run
        //_proc.runCommand(1);
        //_proc.runCommand(0);
    }
    public void run() {
        preprocessText();
        // while program is not ended
        while (Memory.mem[_proc.EIP] != -1) {
            _proc.EIP += _proc.runCommand(Memory.mem[_proc.EIP]);
        }
    }

    public void preprocessText() {
        // searching for any word which is not command
        String lines[] = programToProcess.split("\n");
        for (String line : lines) {
            // searching in every line
            String words[] = line.trim().split(" ");
            for (String word : words) {
                // in every word
                // our assembler is case-independent
                word = word.toLowerCase();
                if (!word.matches("[0-9]"))
                    try {
                        // trying to get one of the errors -
                        // if it's not command and not assoc. var
                        if (_proc.hasCommand(word)) {
                            // this is processor command, load it to memory
                            int idx = Memory.getFreeMemoryIndex();
                            Memory.mem[idx] = _proc.getCommand(word).code;
                        } else {
                            int existingIndex = Memory.getIndexByName(word);
                            // here we have the index of the existing variable
                            // add it as a param to command
                            int idx = Memory.getFreeMemoryIndex();
                            Memory.mem[idx] = existingIndex;
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
    }
}
