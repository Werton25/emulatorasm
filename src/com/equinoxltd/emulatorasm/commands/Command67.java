package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Controller;
import com.equinoxltd.emulatorasm.Memory;

/**
 * @author dark-wizard
 * PRINT command
 * Syntax: PRINT idx
 */
public class Command67 extends Command {
    public Command67(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int idx0 = operands[0];
        Controller.output(Memory.mem[idx0]);
    }
}
