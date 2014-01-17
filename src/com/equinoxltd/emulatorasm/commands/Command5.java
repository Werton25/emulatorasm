package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Memory;
import com.equinoxltd.emulatorasm.TextEditor;

/**
 * @author dark-wizard
 */
public class Command5 extends Command {
    public Command5(String name, int code, int[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        int idx = operands[0];
        Memory.mem[idx] = Integer.parseInt(TextEditor.runningApp.getConsoleText());
    }
}
