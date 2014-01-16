package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Controller;
import com.equinoxltd.emulatorasm.Memory;

/**
 * Created by werton on 16.01.14.
 *
 * Вроде так, но я не уверен.
 * Команда add
 * Syntax: ADD idx, idx
 *         ADD var, var
 */
public class Command0 extends Command {
    public Command0(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int idx0 = operands[0], idx1 = operands[1];
        //Controller.log("Indexes found: " + idx0 + ", " + idx1);
        Memory.mem[idx0] += Memory.mem[idx1];
    }
}
