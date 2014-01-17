package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Controller;
import com.equinoxltd.emulatorasm.Processor;

/**
 * @author dark-wizard
 * FPRINT command
 * syntax: FPRINT
 */
public class Command1 extends Command {
    public Command1(String name, int code, int[] operands) {
        super(name, code, operands);
    }
    @Override
    public void execute() {
        Controller.output(Processor.stack.popLeave());
    }
}
