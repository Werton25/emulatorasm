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
        //Controller.output("Command 0 example");
        String op0 = operands[0].toString();
        Controller.log(op0);
        int idx0, idx1;
        if (op0.matches("[0-9]")) {
            // indexes
            idx0 = (Integer) operands[0];
            idx1 = (Integer) operands[1];
            Controller.log("Indexes found: " + idx0 + ", " + idx1);
        } else {
            idx0 = Memory.getIndexByName(op0);
            idx1 = Memory.getIndexByName((String) operands[1]);
        }
        Memory.mem[idx0] += Memory.mem[idx1];
    }
}
