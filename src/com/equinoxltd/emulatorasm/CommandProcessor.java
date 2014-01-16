package com.equinoxltd.emulatorasm;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author dark-wizard
 * класс для загрузки и обработки доступных команд процессора
 */
public class CommandProcessor {
    public List<Command> commands = new ArrayList<Command>();
    public CommandProcessor() {

    }

    public void loadListOfCommands() {
        try {
            Scanner sc = new Scanner(new File("commands-ready.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String comms[] = line.split(" ");
                int code = Integer.parseInt(comms[0]);
                Object operands[] = new Object[comms.length - 2];
                for (int i = 2; i < comms.length; ++i)
                    operands[i-2] = Integer.parseInt(comms[i]);

                // using reflection to determine which class we use
                // даже не пытайся это понять, ну, попытка не пытка, но пояснять не буду, гугли Reflection

                Class[] params = new Class[] {String.class, int.class, Object[].class};
                Command mycomm;
                try {
                    mycomm = (Command) Class.forName("com.equinoxltd.emulatorasm.commands.Command" + code).getConstructor(params).newInstance(comms[1], code, operands);
                    Controller.log("[Code " + code + ": loading special instruction...]");
                } catch (ClassNotFoundException e) {
                    //generating default command
                    mycomm = new Command(comms[1], code, operands);
                    Controller.log("[Code " + code + ": using default command...]");
                }
                this.commands.add(mycomm);
                Controller.log(mycomm);
            }

        } catch (Exception e) {
            Controller.log("Error while opening!");
        }
    }
    public Command getCommandByName(String name) {
        for (Command comm : this.commands)
            if (comm.name.toLowerCase().compareTo(name.toLowerCase()) == 0)
                return comm;
        throw new Error("No command found by name \"" + name + "\"");
    }

    public Command getCommandByCode(int code) {
        for (Command comm : this.commands)
            if (comm.code == code)
                return comm;
        throw new Error("No command found by code " + code);
    }


    // !! функция используется исключительно для предгенерации списка, не использовать в релизе !!!
    public void generateListOfCommands() {
        Scanner sc;
        try {
            sc = new Scanner(new File("commands.txt"));
            PrintWriter pw = new PrintWriter(new File("commands-ready.txt"));
            int total = 0;
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String comms[] = line.split(" ");
                for (int i = 0; i < comms.length; ++i)
                    pw.print((i + total) + " " + comms[i].trim() + " 1 1\n");
                total += comms.length;
            }
            pw.close();
        } catch (IOException e) {
            Controller.log("Error while opening!");
        }
    }
}
