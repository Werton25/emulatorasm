package com.equinoxltd.emulatorasm;

import java.util.Arrays;

/**
 * @author dark-wizard
 */
public class Command extends AbstractCommand {
    public String name;
    public int code;
    public int operands[];
    public int size;

    public Command(String name, int code, int[] operands) {
        this.name = name;
        this.code = code;
        this.operands = operands;
        this.size = this.operands.length + 1;
    }

    @Override
    public String toString() {
        return "[" + super.toString() + " (" + this.name + "):[" + this.code + ", " + Arrays.toString(operands) + "] == " + size + "]";
    }

    @Override
    public void execute() {
        Controller.output("default action");
    }
}
