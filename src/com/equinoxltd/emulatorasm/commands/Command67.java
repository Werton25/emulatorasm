package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Controller;
import com.equinoxltd.emulatorasm.Memory;

/**
 * @author dark-wizard
 * PRINT command
 * Syntax: PRINT var
 *         PRINT idx
 */
public class Command67 extends Command {
    public Command67(String name, int code, Object[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        String op0 = operands[0].toString();
        Controller.log(op0);
        int idx0;
        if (op0.matches("[0-9]"))
            idx0 = Integer.parseInt(op0);
        else idx0 = Memory.getIndexByName(op0);
        Controller.output(Memory.mem[idx0]);
    }
}
