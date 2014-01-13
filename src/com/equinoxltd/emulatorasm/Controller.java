package com.equinoxltd.emulatorasm;

/**
 * @author dark-wizard
 */
public class Controller {
    // !! ВАЖНО !!
    // В этом коде (как и почти везде) не используется вывод в консоль через System.out.print
    // Для вывода используются функции, описанные внизу этого класса

    // флаг для переключения режимов вывода, используется для вывода отладочной информации
    public static boolean VERBOSE = false;
    private Processor _proc;
    private Memory _mem;

    public Controller() {
        _proc = new Processor();
        _mem = new Memory(1024);
        //code to run
        _proc.runCommand(1);
        _proc.runCommand(0);
    }
    // функция для отладочной информации
    public static void log(Object obj) {
        if (Controller.VERBOSE) System.out.println(obj);
    }

    // функция для вывода в обход флагу, используется для вывода важной информации
    public static void output(Object obj) {
        System.out.println(obj);
    }
}
