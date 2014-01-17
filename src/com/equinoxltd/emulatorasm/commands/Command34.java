package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Processor;

/**
 * Created by werton on 17.01.14.
 *
 * Syntax: fld1
 */
public class Command34 extends Command {
    public Command34(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        Processor.stack.push(1);
    }
}
