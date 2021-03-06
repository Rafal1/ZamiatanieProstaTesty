package com.tiwo;

import algs.model.ILineSegment;
import algs.model.IPoint;
import algs.model.twod.TwoDLineSegment;
import algs.model.twod.TwoDPoint;

import java.util.Hashtable;
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
    private int margin = 20;

    private double[] getExtremes(ILineSegment segment) {
        double maxX = Math.max(segment.getStart().getX(), segment.getEnd().getX());
        double maxY = Math.max(segment.getStart().getY(), segment.getEnd().getY());
        double minX = Math.min(segment.getStart().getX(), segment.getEnd().getX());
        double minY = Math.min(segment.getStart().getY(), segment.getEnd().getY());
        double[] extremes = {minX, minY, maxX, maxY};
        return extremes;
    }

    public SurfaceTransformationTool(Iterable<ILineSegment> segs, int margin) {
        this.margin  = margin;
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = -minX;
        double maxY = -minY;
        for (ILineSegment segment : segs) {
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
        xOffset = -minX;
        yOffset = -minY;
        x = maxX - minX;
        y = maxY - minY;
    }

    public double getXratio(int width) {
        return ((double) width - 2 * margin) / x;
    }

    private double getYratio(int height) {
        return ((double) height - 2 * margin) / y;
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

    public ILineSegment transformNoDistoriton(ILineSegment line, int height, int width) {
        double ratio = getNonDistortingRatio(height, width);
        return transform(line, ratio);
    }

    private ILineSegment transform(ILineSegment line, double ratio) {
        IPoint start = transform(line.getStart(), ratio);
        IPoint end = transform(line.getEnd(), ratio);
        return new TwoDLineSegment(start, end);
    }

    public IPoint transformNoDistortion(IPoint point, int height, int width) {
        double ratio = getNonDistortingRatio(height, width);
        return transform(point, ratio);
    }

    public IPoint transform(IPoint point, double ratio) {
        double px = transformX(point.getX(), ratio);
        double py = transformY(point.getY(), ratio);
        return new TwoDPoint(px, py);
    }

    private double transformX(double x, double ratio) {
        return margin + (x + xOffset) * ratio;
    }

    private double transformY(double y, double ratio) {
        return margin + (y + yOffset) * ratio;
    }
}
