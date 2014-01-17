package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Controller;
import com.equinoxltd.emulatorasm.Memory;
import com.equinoxltd.emulatorasm.Processor;

/**
 * Created by werton on 17.01.14.
 *
 * Syntax: fcom st(idx) st(idx)
 */
public class Command29 extends Command {
    public Command29(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        double val0 = Processor.stack.st[Math.abs(operands[0]) - 1],
               val1 = Processor.stack.st[Math.abs(operands[1]) - 1];
        Controller.log("var 0: " + val0 + ", var 1: " + val1);
        val0 -= val1;
        int flags = 0;
        //parity
        if (val0 % 2 == 0)
            Controller.log("PARITY");
        flags = (val0 % 2 == 0) ? flags | 1 << 2 : flags;
        //zero
        if (val0 == 0)
            Controller.log("ZERO");
        flags = (val0 == 0) ? flags | 1 << 6 : flags;
        // sign
        if (val0 < 0)
            Controller.log("SIGN");
        flags = (val0 < 0) ? flags | 1 << 7 : flags;
        Processor.FLAGS = flags;
        Controller.log("FLAGS state:" + flags);
    }
}
