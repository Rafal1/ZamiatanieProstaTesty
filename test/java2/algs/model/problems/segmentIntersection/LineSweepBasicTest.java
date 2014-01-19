package java2.algs.model.problems.segmentIntersection;

import algs.model.ILineSegment;
import algs.model.IPoint;
import algs.model.twod.TwoDLineSegment;
import algs.model.twod.TwoDPoint;
import org.junit.Test;
import algs.model.problems.segmentIntersection.LineSweep;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Rafal Zawadzki
 */
public class LineSweepBasicTest {
    private LineSweep dba = new LineSweep(); // uwaga na wybor klasy!

    @Test
    public void testLackOfDataLineSweepWhiteBox() {
        ArrayList<ILineSegment> als = new ArrayList<ILineSegment>();

        Hashtable<IPoint, ILineSegment[]> res = dba.intersections(als.iterator());

        assertTrue(res.isEmpty());
    }

    @Test
    public void testIntersectsWhiteBox() {
        ArrayList<ILineSegment> als = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(0, 5), new TwoDPoint(3, 8));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(-1, -1), new TwoDPoint(2, 8));
        als.add(line1);
        als.add(line2);

        Hashtable<IPoint, ILineSegment[]> res = dba.intersections(als.iterator());
        System.out.println();

        IPoint expPoint = new TwoDPoint(1.5, 6.5);
        assertEquals(1, res.size());
        assertTrue(res.containsKey(expPoint));

    }

    @Test
    public void testEPNullWhiteBox() {
        //punkt wspólny
        ArrayList<ILineSegment> als = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(1, 5), new TwoDPoint(3, 8));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(-4, 2), new TwoDPoint(1, 6));

        als.add(line1);
        als.add(line2);

        Hashtable<IPoint, ILineSegment[]> res = dba.intersections(als.iterator());

        assertTrue(res.isEmpty());
    }

    @Test
    public void testEPNotNullWhiteBox() {
        //punkt wspólny
        ArrayList<ILineSegment> als = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(1, 5), new TwoDPoint(3, 8));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(-4, 2), new TwoDPoint(1, 5));

        als.add(line1);
        als.add(line2);

        Hashtable<IPoint, ILineSegment[]> res = dba.intersections(als.iterator());

        IPoint expPoint = new TwoDPoint(1, 5);
        assertEquals(1, res.size());
        assertTrue(res.containsKey(expPoint));
    }

}