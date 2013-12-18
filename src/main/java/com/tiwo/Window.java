package com.tiwo;

import algs.model.ILineSegment;
import algs.model.IPoint;
import algs.model.list.List;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.*;


public class Window{
    private JFrame frame;
    private JComboBox algChooser;
    private JFileChooser fileChooser;
    private JButton calculateButton;
    private JButton chooseFileButton;
    private Surface surface;
    private JLabel time;
    
    public void plot(Hashtable<IPoint, ILineSegment[]> res){
        surface.res = res;
        surface.repaint();
    }
    
    public Window(){

    }
    
    public void setDispalyedAlgorithms(ArrayList<String> algs){
        this.algChooser.removeAllItems();
        for( String alg : algs)
            algChooser.addItem(alg);
    }
    
    public void createAndShow(){
        GridBagConstraints c = new GridBagConstraints();
        initFrame();
        initButtons(c);
        initSurface(c);
        initAlgChooser(c);
        initFileChooser();
        initTimeLabel(c);
        frame.pack();
        frame.setVisible(true);
    }
    
    private void initTimeLabel(GridBagConstraints c){
        time = new JLabel();
        c.gridx=1;
        c.gridy=2;
        c.gridwidth=5;
        frame.add(time, c);
    }
    
    private void initButtons(GridBagConstraints c){       
        calculateButton = new JButton();
        calculateButton.setText("Calculate!");        
        chooseFileButton = new JButton();
        chooseFileButton.setText("Choose data file"); 
        c.gridx=0;
        c.gridy=0;
        frame.add(calculateButton, c.gridy );
        c.gridx=2;
        c.gridy=0;
        frame.add(chooseFileButton, c.gridy);
    }
    
    private void initSurface(GridBagConstraints c){ 
        surface = new Surface(500,500);       
        c.gridx=0;
        c.gridy=1;
        c.gridwidth=5;
        frame.add(surface, c);
    }
    
    private void initFrame(){        
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(600,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        frame.setLayout(new GridBagLayout());
    }
    
    private void initAlgChooser(GridBagConstraints c){
        this.algChooser = new JComboBox();
        c.gridx=1;
        c.gridy=0;
        frame.add(algChooser, c.gridy);
    }
    
    private void initFileChooser(){
        this.fileChooser = new JFileChooser();
    }
    
    public void addListener(ActionListener l){
      this.chooseFileButton.addActionListener(l);
      this.algChooser.addActionListener(l);
      this.calculateButton.addActionListener(l);
      this.fileChooser.addActionListener(l);
    }
    
    public String getSelectedAlgorithm(){
        return (String)this.algChooser.getSelectedItem();
    }
    
    public void showFileChooserDialog(){
        this.fileChooser.showOpenDialog(frame);
    }
    
    public File getSelectedFile(){
        System.out.println("Selected file " + 
                this.fileChooser.getSelectedFile().getAbsolutePath());
        return this.fileChooser.getSelectedFile();
    }
    
    public void displayExecutionTime(long t){
        time.setText("Execution time: " + t+ "tu jednostka");
    }
    
}
