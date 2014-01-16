package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Memory;

/**
 * Created by werton on 16.01.14.
 *
 * Syntax: div var var
 */
public class Command4 extends Command {
    public Command4(String name, int code, Object[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        String op0 = (String) operands[0];
        int idx0, idx1;
        if (op0.matches("[0-9]")) {
            // indexes
            idx0 = (Integer) operands[0];
            idx1 = (Integer) operands[1];
        } else {
            idx0 = Memory.getIndexByName(op0);
            idx1 = Memory.getIndexByName((String) operands[1]);
        }
        Memory.mem[idx0] /= Memory.mem[idx1];
    }
}
