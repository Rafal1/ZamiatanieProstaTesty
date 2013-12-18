package com.tiwo;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Hashtable;
import javax.swing.JPanel;
import algs.model.twod.TwoDPoint;
import algs.model.twod.TwoDLineSegment;
import algs.model.ILineSegment;
import algs.model.IPoint;
/**
 *
 * @author marcin
 */
public class Surface extends JPanel {
    Hashtable<IPoint, ILineSegment[]> res = new Hashtable<IPoint, ILineSegment[]>();
    SurfaceTransformationTool transformer;
    
    public Surface(int x, int y){
        this.setPreferredSize(new Dimension(x,y));
        
        // Hardcoded data 
        // TODO delete before integrating with SweepLine
        TwoDPoint key1 = new TwoDPoint(0,0);
        TwoDLineSegment[] segs = {
                new TwoDLineSegment(200,-1000, 500, 500),
                new TwoDLineSegment(-300,-20, 100, 200),
        };
        res.put(key1, segs);
        
    }
    
    private void drawLines(Graphics2D g2d){
        for(IPoint point : res.keySet()){
            for(ILineSegment segment : res.get(point)){
                transformAndDrawLine(g2d, segment);
            }
        }
    }
    
    private void drawPoints(Graphics2D g2d){
        for(IPoint point : res.keySet()){
            g2d.setColor(Color.red);
            transformAndDrawPoint(g2d,point);
            drawPointLabel(g2d,point);
            g2d.setColor(Color.green);
            for(ILineSegment segment : res.get(point)){
                transformAndDrawPoint(g2d, segment.getStart());
                drawPointLabel(g2d,segment.getStart());
                transformAndDrawPoint(g2d, segment.getEnd());
                drawPointLabel(g2d,segment.getEnd());
            }
        }
    }
    
    private void transformAndDrawLine(Graphics g2d, ILineSegment segment){
        ILineSegment line = transformer.transformNoDistoriton(segment, 
                getHeight(), getWidth());
        this.drawLine(g2d, line);
    }
    
    private void drawLine(Graphics g2d, ILineSegment segment){
        int h = getHeight();
        int startX = (int)segment.getStart().getX();
        int startY = h-(int)segment.getStart().getY();
        int endX = (int)segment.getEnd().getX();
        int endY = h-(int)segment.getEnd().getY();
        g2d.drawLine(startX,startY,endX,endY);
    }
    
    private void drawAxises(Graphics g2d){
        int h = getHeight();
        int w = getWidth();
        this.transformAndDrawLine(g2d, new TwoDLineSegment(Integer.MIN_VALUE,0,
                Integer.MAX_VALUE,0));
        this.transformAndDrawLine(g2d, new TwoDLineSegment(0,Integer.MIN_VALUE,0,
                Integer.MAX_VALUE));
    }
    
    private void drawPoint(Graphics g2d, IPoint point){
        int d = 4;
        int h = getHeight();
        int x = (int)point.getX() - d/2;
        int y = h - ((int)point.getY() + d/2);
        g2d.fillOval(x, y, d, d);
    }
    
    private void drawPointLabel(Graphics g2d, IPoint point){
        int h = getHeight();
        int w = getWidth();
        String coordinates = "("+point.getX()+","+point.getY()+")";
        IPoint transformed = transformer.transformNoDistortion(point, h, w);
        g2d.drawChars(coordinates.toCharArray(), 0, coordinates.length(),
                (int)transformed.getX(), h-(int)transformed.getY());
    }
    
    private void transformAndDrawPoint(Graphics g2d, IPoint point){
        IPoint p = transformer.transformNoDistortion(point, 
                getHeight(), getWidth() );
        drawPoint(g2d, p);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        // TODO find good place to call that
        transformer = new SurfaceTransformationTool(res);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.yellow);
        this.drawAxises(g2d);
        g2d.setColor(Color.blue);
        this.drawLines(g2d);
        this.drawPoints(g2d);
    }
    
}
