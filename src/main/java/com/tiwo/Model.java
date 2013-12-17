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
import algs.model.problems.segmentIntersection.IntersectionDetection;

/**
 *
 * @author marcin
 */
public class Model {
    private Algorithms algs;
    private ILineSegment[] segments;
    
    public Model(){
        algs = new Algorithms();
    }
    
    public boolean readFromFile(String path){
        File f = new File(path);
        return this.readFromFile(f);
    }
    
    public boolean readFromFile(File f){
        ArrayList<ILineSegment> segs = new ArrayList<ILineSegment>();
        try {
            Scanner sc = new Scanner(f).useLocale(Locale.US);;
            while (sc.hasNextDouble()) {
                Double[] vals = new Double[4];
                for (int i = 0; i < vals.length; i++) {
                    vals[i] = sc.nextDouble();
                }
                ILineSegment segment= new TwoDLineSegment(new TwoDPoint(vals[0], vals[1]),
                        new TwoDPoint(vals[2], vals[3]));
                segs.add(segment);
            }
        } catch (FileNotFoundException fnfe) {
            System.err.println("Unable to locate file:" + f);
            return false;
        }
        segs.toArray(segments);
        return true;
    }
    
    public String[] getAlgoritmsNames(){
        return (String[])algs.getAlgorithmsNames();
    }
    
    public void setAlgorithm(String name){
        algs.setChoosed(name);
    }
    
    public Hashtable<IPoint,ILineSegment[]> intersections(){
        if(segments == null)
            return null;
        return algs.getChoosed().intersections(segments);
    }
        
        
}
