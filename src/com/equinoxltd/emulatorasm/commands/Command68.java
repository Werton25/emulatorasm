package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Processor;

/**
 * Created by werton on 17.01.14.
 *
 * Syntax: faddst st(idx) st(idx)
 */
public class Command68 extends Command {
    public Command68(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int idx0 = (Integer) Math.abs(operands[0]) + 1;
        int idx1 = (Integer) Math.abs(operands[1]) + 1;
        Processor.stack.st[idx0] += Processor.stack.st[idx1];
    }
}
