package com.tiwo;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;


public class Window{
    private JFrame frame;
    private JComboBox algChooser;
    private JFileChooser fileChooser;
    private JButton calculateButton;
    private JButton chooseFileButton;
    private Surface surface;
    
    public void createAndShow(){
        initButtons();
        initSurface();
        initAlgChooser();
        initFileChooser();
        initFrame();
        GridBagConstraints c = new GridBagConstraints();        
        c.gridx=0;
        c.gridy=0;
        frame.add(calculateButton, c.gridy );
        c.gridx=1;
        c.gridy=0;
        frame.add(algChooser, c.gridy);
        c.gridx=2;
        c.gridy=0;
        frame.add(chooseFileButton, c.gridy);
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=5;
        frame.add(surface, c);
        frame.pack();
        frame.setVisible(true);
        System.out.println(fileChooser.showOpenDialog(frame));
        System.out.println(fileChooser.getSelectedFile());
    }
    
    private void initButtons(){
        calculateButton = new JButton();
        calculateButton.setText("Calculate!");        
        chooseFileButton = new JButton();
        chooseFileButton.setText("Choose data file");
    }
    
    private void initSurface(){        
        surface = new Surface(500,500);
    }
    
    private void initFrame(){        
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(600,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.setLayout(new GridBagLayout());
    }
    
    private void initAlgChooser(){
        this.algChooser = new JComboBox();
    }
    
    private void initFileChooser(){
        this.fileChooser = new JFileChooser();
    }
    
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Window().createAndShow();
            }
        });
    }
    
}
