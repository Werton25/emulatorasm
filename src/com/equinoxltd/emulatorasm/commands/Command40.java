package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Memory;
import com.equinoxltd.emulatorasm.Processor;

/**
 * Created by werton on 17.01.14.
 *
 * Syntax: fmul var
 */
public class Command40 extends Command {
    public Command40(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int idx0 = (Integer) operands[0];
        Processor.stack.st[0] *= Memory.mem[idx0];
    }
}
