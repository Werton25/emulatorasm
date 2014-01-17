package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Processor;

/**
 * Created by werton on 17.01.14.
 *
 * Syntax: fldln2
 */
public class Command37 extends Command {
    public Command37(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        Processor.stack.push(Math.log(2));
    }
}
