package com.equinoxltd.emulatorasm;

/**
 * Created by werton on 12.01.14.
 *
 * Здесь просто обрабатываются команды, точнее происходит получение команды - нет, именно обрабатываются
 */
public class Processor {
    private Command _comm;
    private CommandProcessor _commproc;
    public static int EIP = 0;
    // регистры общего назначения с прямым доступом, для упрощения работы, массив не катит, лишние условия
    public static int EAX, EBX, ECX, EDX, ESI, EDI, EBP, ESP;

    public Processor() {
        _commproc = new CommandProcessor();
        _commproc.loadListOfCommands();
    }

    public int runCommand(int code) {
        // загрузка команды по ее коду и исполнение
        Command comm = _commproc.getCommandByCode(code);
        // backdoor for fill operands list
        int operands[] = new int[2];
        //int operands[] = new int[comm.size - 1];
        for (int i = 0; i < comm.size - 1; ++i)
            operands[i] = Memory.mem[EIP + i + 1];
        //-----------
        comm.setOperands(operands);
        comm.execute();
        return comm.size;
    }

    public Command getCommand(String name) {
        return _commproc.getCommandByName(name);
    }

    public boolean hasCommand(String name) {
        try {
            _commproc.getCommandByName(name);
            return true;
        } catch (Error e) {
            return false;
        }
    }
}
