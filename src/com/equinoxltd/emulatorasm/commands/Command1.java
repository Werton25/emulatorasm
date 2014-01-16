package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Controller;

/**
 * @author dark-wizard
 *
 * будущая реализация команды AND
 */
public class Command1 extends Command {
    public Command1(String name, int code, Object[] operands) {
        super(name, code, operands);
    }
    @Override
    public void execute() {
        Controller.output("Command 1 example");
    }
}
