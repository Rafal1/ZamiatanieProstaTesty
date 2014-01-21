package com.tiwo;

import algs.model.list.List;
import algs.model.problems.segmentIntersection.IntersectionDetection;
import java.util.ArrayList;
/**
 *
 * @author marcin
 */
public class Algorithms{
    ArrayList<IntersectionDetection> algs;
    ArrayList<String> names;
    int selectedIndex = 0;
    
    public Algorithms(){
        algs = new ArrayList<IntersectionDetection>();
        names = new ArrayList<String>();
        algs.add(new algs.model.problems.segmentIntersection.LineSweep());
        names.add("Line Sweep");
        algs.add(new algs.model.problems.segmentIntersection.BruteForceAlgorithm());
        names.add("Brute Force");
        algs.add(new algs.model.problems.segmentIntersection.linkedlist.LineSweep());
        names.add("Line Sweep Linked List");
    }
    
    public ArrayList<String> getAlgorithmsNames(){
        return names;
        
    }
    
    public void setChoosed(String name){
        for(int i=0; i < names.size(); i++){
            if( names.get(i).equals(name)){
                this.selectedIndex = i;
                return;  
            }
        }       
    }
    
    public IntersectionDetection getChoosed(){
        return algs.get(selectedIndex);
    }
}