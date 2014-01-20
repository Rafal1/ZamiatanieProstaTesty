package com.tiwo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcin
 */
public class Controller implements ActionListener, ChangeListener {
        private Window view;
        private Model model;
        
    public Controller(){
        model = new Model();
        view = new Window();
        view.createAndShow();
        view.setDispalyedAlgorithms(model.getAlgoritmsNames());
        view.addListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        System.out.println("Action Command: " + e.getActionCommand());
        if( e.getActionCommand().equals("comboBoxChanged") )
            model.setAlgorithm(view.getSelectedAlgorithm());
        else if( e.getActionCommand().equals("Calculate!") ){
            view.plot( model.getSegments(), model.intersections() );
            view.displayExecutionTime(model.getLastExecutionTime());
        }
        else if( e.getActionCommand().equals("Choose data file") )
            view.showFileChooserDialog();
        else if( e.getActionCommand().equals("ApproveSelection") )
            model.readFromFile( view.getSelectedFile() );           
    }

    public void stateChanged(ChangeEvent ce) {
        view.setMargin( view.getRequestedMargin() );
        System.out.println("Margin change");
    }
    
    
    
}
