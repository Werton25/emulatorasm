package com.equinoxltd.emulatorasm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by werton on 15.01.14.
 */
public class TextEditor extends JFrame implements Runnable {
    private JPanel textEditor;
    private JButton run;
    private JButton debug;
    private JScrollPane scrollText;
    private JTextPane textPane1;
    private JTabbedPane tabbedPane1;
    private JTextPane textPane2;
    private JPanel consolePanel;
    private JScrollPane scrollConsole;
    private JButton clearButton;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem itemOpen;
    private JMenuItem itemSave;
    private JMenuItem itemSaveAs;
    private JMenuItem itemExit;
    private String fullPath;

    public Object lock = new Object();
    public Thread controllerThread;

    public static TextEditor runningApp;

    public TextEditor() {
        super("TextEditor");
        setContentPane(this.textEditor);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        this.menuBar = new JMenuBar();
        this.menuFile = new JMenu("File");

        this.itemOpen = new JMenuItem("Open...");
        this.menuFile.add(this.itemOpen);

        this.itemSave = new JMenuItem("Save...");
        this.menuFile.add(this.itemSave);

        this.itemSaveAs = new JMenuItem("Save As...");
        this.menuFile.add(this.itemSaveAs);

        this.itemExit = new JMenuItem("Exit...");
        this.menuFile.add(this.itemExit);

        this.menuBar.add(this.menuFile);

        setJMenuBar(this.menuBar);

        this.tabbedPane1.setTitleAt(0, "Console");

        itemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openFile();
            }
        });

        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveFile();
            }
        });

        itemSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveAsFile();
            }
        });

        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.print(textPane1.getText());
                tabbedPane1.setVisible(true);
                //textPane2.setText(textPane1.getText());
                controllerThread = new Thread(TextEditor.runningApp);
                controllerThread.start();
            }
        });

        debug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.print(textPane1.getText());
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textPane1.selectAll();
                textPane1.cut();
                tabbedPane1.setVisible(false);
            }
        });
    }

    @Override
    public void run() {
        Controller controller = new Controller();
        controller.programToProcess = textPane1.getText();
        controller.run();
    }

    public void openFile() {
        FileDialog fileDialog; // диалоговое окно (для поиска файлов)
        fileDialog = new FileDialog(this, "Open file", FileDialog.LOAD);
        fileDialog.setVisible(true);
        // определим полный путь к выбранному (с помошью диалогового окна файлу)
        this.fullPath = fileDialog.getDirectory() + fileDialog.getFile();

        byte buf[];
        String szStr;

        // "заготовим" объект для чтения данных из файлов
        FileInputStream fileInputStream;//для чтения данных из файлов

        try {
            fileInputStream = new FileInputStream(this.fullPath); // "связываем" объект класса FileInputStream
            //  c файлом, полный путь к которму хранится в переменной fullPath
            buf = new byte[fileInputStream.available()]; // создаем массив байтов (по количеству данных в файле (они считываются из входного потока))
            fileInputStream.read(buf);// сохраняем прочитанные данные в массиве байтов buf
            szStr = new String(buf);
            // полученная строка содржит символы перехода на новую строку (\r\n)
            // Чтобы "разбить" строку по этим символам, создадим объект класса StringTokenizer

            // Итак, массив buf теперь хранит прочитанные ищз файла данные
            // Выберем всё содержимое тектсовой области
            this.textPane1.selectAll();

            StringTokenizer st = new StringTokenizer(szStr, "\r\n");//выделяем перевод строки...

            // Цикл будет работать, пока находятся новые "метки" - символы (\r\n)
            while(st.hasMoreElements()) {
                szStr = new String((String)st.nextElement());// получаем очередной элемент токенайзера и преобразуем его в строку
                this.textPane1.setText(szStr + "\r\n");// добавляем полученную строку к текстовой области
            }

            // Цикл отработал, всё содержимое файла отображается в текстовой области
            fileInputStream.close();// FileInputStream нам больше не нужен
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    public void setConsoleText(String txt) {
        String oldText = textPane2.getText();
        textPane2.setText(oldText.trim() + "\n" + txt);
    }

    public String getConsoleText() {
        Waiter w = new Waiter();
        Thread t = new Thread(w);
        String txt = "";
        t.start();
        try {
            t.join();
            synchronized (lock) {
                txt = w.getString();
            }
        } catch (InterruptedException e) {
            Controller.log("Wow, thread error!");
        }
        return txt;
    }

    public void saveFile() {
        FileOutputStream fileOutputStream;//FileOutputStream - для записи данных в файлы
        String sz = this.textPane1.getText(); // всё содержимое текстовой области помещаем в строку
        byte buf[] = sz.getBytes(); // преобразуем строку в массив байтов

        if(fullPath == "" || fullPath == null) {
            System.out.println("No File Selected!!!");
        } else { // файл выбран
            try {
                fileOutputStream = new FileOutputStream(this.fullPath);
                fileOutputStream.write(buf);
                fileOutputStream.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public void saveAsFile() {
        FileDialog fileDialog;
        fileDialog = new FileDialog(this, "Save file as...",  FileDialog.SAVE);
        fileDialog.setVisible(true);

        if(fileDialog.getDirectory() == null || fileDialog.getFile() == null) {
            System.out.println("No File Selected!!!");
        } else {
            this.fullPath = fileDialog.getDirectory() + fileDialog.getFile(); // полный путь к целевому файлу
            saveFile();
        }
    }

    public static void main(String[] args) {
        TextEditor.runningApp = new TextEditor();
        TextEditor.runningApp.setVisible(true);
    }

    class Waiter implements Runnable {
        String _res = "";
        @Override
        public void run() {
            try {
                synchronized (lock) {
                    while (true) {
                        _res = textPane2.getText();
                        //System.out.println("Text:" + _res);
                        //System.out.println("Check1:" + (_res.trim().compareTo("") != 0));
                        //System.out.println("Check2:" + (_res.endsWith("\n")));
                        if (_res.trim().compareTo("") != 0 && _res.endsWith("\n")) break;
                        Thread.sleep(100);
                    }
                }
            } catch (Exception e) {
                Controller.log("Waiter error!");
            }
        }
        public String getString() {
            return _res.trim();
        }
    }
}
