package com.equinoxltd.emulatorasm.commands;

import com.equinoxltd.emulatorasm.Command;
import com.equinoxltd.emulatorasm.Memory;

/**
 * @author dark-wizard
 * command 54 is for dq
 */
public class Command54 extends Command {
    public Command54(String name, int code, Object[] operands) {
        super(name, code, operands);
    }

    @Override
    public void execute() {
        // получаем новую свободную ячейку для переменной
        int idx = Memory.getFreeMemoryIndex();
        // создаем новую ассоциацию в массиве переменных
        Memory.setNameByIndex((String) operands[0], idx);
        // если задано начальное значение, устанавливаем его
        if (operands.length == 2)
            Memory.mem[idx] = (Integer) operands[1];
        else Memory.mem[idx] = 0;
    }
}
