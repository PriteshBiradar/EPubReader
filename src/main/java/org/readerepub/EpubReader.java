package org.readerepub;


import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;

import static javax.swing.UIManager.setLookAndFeel;

public class EpubReader {
    private JFrame frame;
    private JTextArea textArea;

    public EpubReader() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        frame = new JFrame("Epub Reader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);


        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("file");
        JMenuItem openItem = new JMenuItem("Open EPUB");
        openItem.addActionListener(e -> openEpub());
        fileMenu.add(openItem);
        jMenuBar.add(fileMenu);
        frame.setJMenuBar(jMenuBar);

        frame.setVisible(true);

    }


    private void openEpub(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("EPUB Files","epub"));
        int returnValue = fileChooser.showOpenDialog(frame);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            readEpub(file);
        }
    }

    private void readEpub(File file){
    try(FileInputStream epubfile = new FileInputStream(file)){
        
    }catch (IOException e){
        e.printStackTrace();
    }
    }
}


