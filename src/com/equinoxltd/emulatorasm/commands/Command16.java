package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Controller;
import com.equinoxltd.emulatorasm.Processor;

/**
 * @author dark-wizard
 * JMP command
 * Syntax: JMP idx
 */
public class Command16 extends Command {
    public Command16(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        Controller.log("jumping...");
        Processor.EIP = operands[0];
        //this.size = operands[0] - Processor.EIP;
    }
}
