package org.readerepub;


import com.formdev.flatlaf.FlatLightLaf;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
//import nl.siegmann.epublib.epub.EpubReader;  // If the method expects InputStream



import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

import static javax.print.attribute.standard.MediaSizeName.C;
import static javax.swing.UIManager.setLookAndFeel;

public class EpubReaderApp {
    private JFrame frame;
    private JTextArea textArea;

    public EpubReaderApp() {
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
        jMenu.add(openItem);
        jMenuBar.add(jMenu);
        frame.setJMenuBar(jMenuBar);

        frame.setVisible(true);

    }


    private void openEpub(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("EPUB Files","epub"));
        int returnValue = fileChooser.showOpenDialog(frame);
        if(returnValue == JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            readEpub(selectedFile);
        }
    }

    private void readEpub(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            EpubReader epubReader = new EpubReader();
            Book book = epubReader.readEpub(fis);
            StringBuilder content = new StringBuilder();
            for (Resource resource : book.getContents()) {
                content.append(new String(resource.getData()));
            }
            textArea.setText(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error reading EPUB file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EpubReaderApp()); // Corrected here
    }
}





