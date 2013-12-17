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
        
    public void createWindow(){        
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                view = new Window();
                view.createAndShow();
            }
        });
    }
    
    public void createModel(){
        model = new Model();
    }
    
    public void actionPerformed(ActionEvent e){
    }
    
    
    
}
