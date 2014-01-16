package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Memory;

/**
 * Created by werton on 16.01.14.
 *
 * Syntax: div idx idx
 */
public class Command4 extends Command {
    public Command4(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int idx0 = operands[0], idx1 = operands[1];
        Memory.mem[idx0] /= Memory.mem[idx1];
    }
}
