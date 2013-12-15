package com.tiwo;

/**
 *
 * @author marcin
 */
public class Point implements IPoint {
    private double x,y;
    
    public Point(double x, double y){
        this.x=x;
        this.y=y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }
    
}
