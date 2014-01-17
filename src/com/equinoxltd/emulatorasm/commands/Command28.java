package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Processor;

/**
 * Created by werton on 17.01.14.
 *
 * Syntax: fchs
 */
public class Command28 extends Command {
    public Command28(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        Processor.stack.st[0] = -1 * Processor.stack.st[0];
    }
}
