package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Memory;

/**
 * Created by werton on 17.01.14.
 */
public class Command6 extends Command {
    public Command6(String name, int code, Object[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        String op0 = (String) operands[0];
        int idx0;
        if (op0.matches("[0-9]")) {
            // indexes
            idx0 = (Integer) operands[0];
        } else {
            idx0 = Memory.getIndexByName(op0);
        }
        Memory.mem[idx0] += 1;
    }
}
