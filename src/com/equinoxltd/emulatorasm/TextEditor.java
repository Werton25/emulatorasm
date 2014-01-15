package com.equinoxltd.emulatorasm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by werton on 15.01.14.
 */
public class TextEditor extends JFrame {
    private JPanel textEditor;
    private JButton run;
    private JButton debug;
    private JTextPane textPane1;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem itemOpen;
    private JMenuItem itemSave;
    private JMenuItem itemSaveAs;
    private JMenuItem itemExit;

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

        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.print(textPane1.getText());
            }
        });

        debug.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.print(textPane1.getText());
            }
        });
    }

    public static void main(String[] args) {
        new TextEditor().setVisible(true);
    }
}
