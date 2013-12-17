package com.tiwo;


import java.util.Hashtable;
import java.util.List;
import algs.model.IPoint;
import algs.model.ILineSegment;
import algs.model.twod.TwoDPoint;
import algs.model.twod.TwoDLineSegment;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marcin
 */
public class SurfaceTransformationTool {

    private double x;
    private double y;
    private double xOffset = 0;
    private double yOffset = 0;

    private double[] getExtremes(ILineSegment segment) {
        double maxX = Math.max(segment.getStart().getX(), segment.getEnd().getX());
        double maxY = Math.max(segment.getStart().getY(), segment.getEnd().getY());
        double minX = Math.min(segment.getStart().getX(), segment.getEnd().getX());
        double minY = Math.min(segment.getStart().getY(), segment.getEnd().getY());
        double[] extremes = {minX, minY, maxX, maxY};
        return extremes;
    }

    public SurfaceTransformationTool(Hashtable<IPoint, ILineSegment[]> intersections) {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        for (IPoint p : intersections.keySet()) {
            for (ILineSegment segment : intersections.get(p)) {
                double[] extremes = this.getExtremes(segment);
                if (extremes[0] < minX) {
                    minX = extremes[0];
                }
                if (extremes[1] < minY) {
                    minY = extremes[1];
                }
                if (extremes[2] > maxX) {
                    maxX = extremes[2];
                }
                if (extremes[3] > maxY) {
                    maxY = extremes[3];
                }
            }
        }
        xOffset = - minX;
        yOffset = - minY;
        x = maxX - minX;
        y = maxY - minY;
    }

    public double getXratio(int width) {
        return ((double) width) / x;
    }

    private double getYratio(int height) {
        return ((double) height) / y;
    }

    private double getNonDistortingRatio(int height, int width) {
        return Math.min(getYratio(height), getXratio(width));
    }

    public double getXoffset() {
        return xOffset;
    }

    public double getYoffset() {
        return yOffset;
    }

    public ILineSegment transformNoDistoriton(ILineSegment line, int height, int width){
        double ratio = getNonDistortingRatio(height, width);
        return transform(line,ratio);
    }
    
    private ILineSegment transform(ILineSegment line, double ratio){
        IPoint start = transform( line.getStart(), ratio);
        IPoint end = transform( line.getEnd(), ratio);
        return new TwoDLineSegment(start, end);       
    }
    
    public IPoint transformNoDistortion(IPoint point, int height, int width){
        double ratio = getNonDistortingRatio(height, width);
        return transform(point, ratio);
    }
        
    public IPoint transform(IPoint point, double ratio){
        double px = transformX(point.getX(), ratio);
        double py = transformY(point.getY(), ratio);
        return new TwoDPoint(px,py);
    }
    
    private double transformX(double x, double ratio){
        return (x+xOffset)*ratio;        
    }
    
    private double transformY(double y, double ratio){
        return (y+yOffset)*ratio;       
    }
}
