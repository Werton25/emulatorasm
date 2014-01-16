package com.equinoxltd.emulatorasm;

/**
 * Created by werton on 12.01.14.
 *
 * Здесь просто обрабатываются команды, точнее происходит получение команды - нет, именно обрабатываются
 */
public class Processor {
    private Command _comm;
    private CommandProcessor _commproc;

    public Processor() {
        _commproc = new CommandProcessor();
        _commproc.loadListOfCommands();
    }

    public void runCommand(int code) {
        // загрузка команды по ее коду и исполнение
        Command comm = _commproc.getCommandByCode(code);
        // backdoor for fill operands list
        //-----------
        comm.execute();
    }
}
