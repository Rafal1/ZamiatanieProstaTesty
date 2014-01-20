/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tiwo;

import algs.model.twod.TwoDLineSegment;
import algs.model.twod.TwoDPoint;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import algs.model.ILineSegment;
import algs.model.IPoint;
import java.util.Hashtable;

/**
 *
 * @author marcin
 */
public class Model {
    private Algorithms algs;
    private ArrayList<ILineSegment> segments;
    
    public Model(){
        algs = new Algorithms();
        segments = new ArrayList<ILineSegment>();
    }
    
    public boolean readFromFile(String path){
        File f = new File(path);
        return this.readFromFile(f);
    }
    
    public boolean readFromFile(File f){
        segments.clear();
        try {
            Scanner sc = new Scanner(f).useLocale(Locale.US);
            while (sc.hasNext()) {
                Double[] vals = new Double[4];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = sc.nextDouble();
                }
                System.out.println("reading");
                ILineSegment segment= new TwoDLineSegment(new TwoDPoint(vals[0], vals[1]),
                        new TwoDPoint(vals[2], vals[3]));
                segments.add(segment);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Unable to locate file:" + f);
            return false;
        }
        return true;
    }
    
    public ArrayList<String> getAlgoritmsNames(){
        return algs.getAlgorithmsNames();
    }
    
    public ArrayList<ILineSegment> getSegments(){
        return this.segments;
    }
    
    public void setAlgorithm(String name){
        algs.setChoosed(name);
    }
    
    public Hashtable<IPoint,ILineSegment[]> intersections(){
        if(segments == null)
            return null;
        return algs.getChoosed().intersections(segments.iterator());
    }
    
    public long getLastExecutionTime(){
        return algs.getChoosed().time();
    }
        
        
}
