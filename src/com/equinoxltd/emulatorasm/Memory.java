package com.equinoxltd.emulatorasm;

import java.util.Arrays;

/**
 * Created by werton on 12.01.14.
 *
 * Это переписанный код Ермоченко с одним дополнением ввиде массива регистров
 */
public class Memory {
    private int _mem_size = 0;
    private int _mem[];
    // регистры общего назначения с прямым доступом, для упрощения работы, массив не катит, лишние условия
    public static int EAX, EBX, ECX, EDX, ESI, EDI, EBP, ESP;

    public Memory(int size) {
        this._mem_size = size;
        this._mem = new int[size];
    }

    public int getSize() {
        return this._mem_size;
    }

    // function for printing memory
    @Override
    public String toString() {
        return Arrays.toString(this._mem);
    }
}
