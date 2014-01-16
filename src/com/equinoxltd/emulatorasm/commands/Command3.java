package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Controller;
import com.equinoxltd.emulatorasm.Memory;

/**
 * Created by werton on 16.01.14.
 *
 * Syntax: dec var
 */
public class Command3 extends Command {
    public Command3(String name, int code, int[] operands) {
        super(name, code, operands);
    }
    @Override
    public void execute() {
        int idx0 = operands[0];
        Memory.mem[idx0] -= 1;
    }
}
