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
    public Command0(String name, int code, Object[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int idx0 = Integer.parseInt(operands[0].toString());
        int idx1 = Integer.parseInt(operands[1].toString());
        //Controller.log("Indexes found: " + idx0 + ", " + idx1);
        Memory.mem[idx0] += Memory.mem[idx1];
    }
}
