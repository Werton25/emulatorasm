package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Processor;

/**
 * Created by werton on 17.01.14.
 *
 * Syntax: fcos
 */
public class Command47 extends Command {
    public Command47(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        Processor.stack.st[0] = Math.cos(Processor.stack.st[0]);
    }
}
