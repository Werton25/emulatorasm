package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Processor;

/**
 * @author dark-wizard
 * JG command
 * Syntax: JG label
 */
public class Command12 extends Command {
    public Command12(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int fl = Processor.FLAGS;
        if ((fl & 1 << 7) == 0 && (fl & 1 << 6) > 0) {
            Processor.EIP = operands[0];
            Processor.FLAGS = 0;
        }
    }
}
