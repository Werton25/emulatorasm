package com.equinoxltd.emulatorasm;

import java.util.Arrays;

/**
 * Created by werton on 17.01.14.
 */
public class Stack {
    public double st[];
    public int ptr = 0;

    public Stack(int n) {
        st = new double[n];
    }

    public void push(double x) {
        for (int i = ptr - 1; i >= 0; --i)
            st[i + 1] = st[i];
        st[0] = x;
        ++ptr;
    }
    public double popLeave() {
        return st[0];
    }
    public double pop() {
        double res = st[0];
        for (int i = 1; i < ptr; ++i)
            st[i - 1] = st[i];
        --ptr;
        return res;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "st=" + Arrays.toString(st) +
                '}';
    }
}
