package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Controller;

/**
 * Created by werton on 16.01.14.
 *
 * Вроде так, но я не уверен.
 * Команда add
 */
public class Command0 extends Command {
    public Command0(String name, int code, int[] operands) {
        super(name, code, operands);
        operands[0] += operands[1];
    }

    @Override
    public void execute() {
        Controller.output("Command 0 example");
    }
}
