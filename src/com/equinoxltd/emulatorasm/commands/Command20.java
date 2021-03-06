package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Memory;

/**
 * Created by werton on 17.01.14.
 * NEG command
 * Syntax: NEG idx
 */
public class Command20 extends Command {
    public Command20(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int idx0 = (Integer) operands[0];
        Memory.mem[idx0] *= -1;
    }
}
