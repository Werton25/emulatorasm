package com.equinoxltd.emulatorasm;

/**
 * Created by werton on 12.01.14.
 *
 * Здесь просто обрабатываются команды, точнее происходит получение команды - нет, именно обрабатываются
 */
public class Processor {
    private Command _comm;
    private CommandProcessor _commproc;
    public int EIP = 0;

    public Processor() {
        _commproc = new CommandProcessor();
        _commproc.loadListOfCommands();
    }

    public int runCommand(int code) {
        // загрузка команды по ее коду и исполнение
        Command comm = _commproc.getCommandByCode(code);
        // backdoor for fill operands list
        Object operands[] = new Object[comm.size - 1];
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
