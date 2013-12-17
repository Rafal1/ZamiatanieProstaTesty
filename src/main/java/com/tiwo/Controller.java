package com.tiwo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marcin
 */
public class Controller implements ActionListener {
        private Window view;
        private Model model;
        
    public Controller(){
        model = new Model();
        view = new Window();
        view.createAndShow();
        view.setDispalyedAlgorithms(model.getAlgoritmsNames());
    }
    
    public void actionPerformed(ActionEvent e){
    }
    
    
    
}
