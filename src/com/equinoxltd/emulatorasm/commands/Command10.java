package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Processor;

/**
 * @author dark-wizard
 * JBE command
 * Syntax: JBE label
 */
public class Command10 extends Command {
    public Command10(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int fl = Processor.FLAGS;
        if ((fl & 1 << 7) > 0 || (fl & 1 << 6) > 0) {
            Processor.EIP = operands[0];
            Processor.FLAGS = 0;
        }
    }
}
