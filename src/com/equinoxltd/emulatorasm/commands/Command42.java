package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Processor;

/**
 * Created by werton on 17.01.14.
 *
 * Syntax: fsqrt
 */
public class Command42 extends Command {
    public Command42(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        Processor.stack.st[0] = Math.sqrt(Processor.stack.st[0]);
    }
}
