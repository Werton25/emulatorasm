package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Controller;
import com.equinoxltd.emulatorasm.Memory;
import com.equinoxltd.emulatorasm.Processor;

/**
 * @author dark-wizard
 * CMP command
 * Syntax: CMP idx idx
 */
public class Command2 extends Command {
    public Command2(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int val0 = Memory.mem[operands[0]], val1 = Memory.mem[operands[1]];
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
