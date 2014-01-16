package com.equinoxltd.emulatorasm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by werton on 12.01.14.
 *
 * Это переписанный код Ермоченко с одним дополнением ввиде массива регистров
 */
public class Memory {
    private int _mem_size = 0;
    public static int mem[];
    public static Map<String, Integer> vars;
    // регистры общего назначения с прямым доступом, для упрощения работы, массив не катит, лишние условия
    public static int EAX, EBX, ECX, EDX, ESI, EDI, EBP, ESP;

    public Memory(int size) {
        this._mem_size = size;
        Memory.vars = new HashMap<String, Integer>(size);
        Memory.mem = new int[size];
        for (int i = 0; i < size; ++i)
            Memory.mem[i] = -1;
    }

    public int getSize() {
        return this._mem_size;
    }

    public static int getIndexByName(String name) {
        if (vars.containsKey(name))
            return vars.get(name);
        throw new Error("Not associated variable!");
    }

    public static void setNameByIndex(String name, Integer idx) {
        Memory.vars.put(name, idx);
    }

    public static int getFreeMemoryIndex() {
        for (int i = 0; i < Memory.mem.length; ++i)
            if (Memory.mem[i] == -1)
                return i;
        throw new Error("Not enough memory!");
    }

    // function for printing memory
    public static void dump() {
        Controller.log(Arrays.toString(Memory.mem));
    }

    @Override
    public String toString() {
        return Arrays.toString(Memory.mem);
    }
}
