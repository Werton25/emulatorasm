package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Memory;

/**
 * Created by werton on 17.01.14.
 * MOV command
 * Syntax: MOV idx idx
 */
public class Command18 extends Command {
    public Command18(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int idx0 = (Integer) operands[0];
        int idx1 = (Integer) operands[1];

        Memory.mem[idx0] = Memory.mem[idx1];
    }
}
