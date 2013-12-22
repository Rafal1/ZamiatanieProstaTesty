package algs.model.problems.segmentIntersection;

import algs.model.ILineSegment;
import algs.model.IPoint;
import algs.model.twod.TwoDLineSegment;
import algs.model.twod.TwoDPoint;
import org.junit.Test;
import algs.model.problems.segmentIntersection.linkedlist.LineSweep;

import java.util.ArrayList;
import java.util.Hashtable;

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
    public void testTheSamePointWhiteBox() {
        ArrayList<ILineSegment> als = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(1, 5), new TwoDPoint(3, 8));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(-4, -2), new TwoDPoint(-4, -2));
        als.add(line1);
        als.add(line2);

        Hashtable<IPoint, ILineSegment[]> res = dba.intersections(als.iterator());

        // w res znajduje sie 1 punkt - ten powtarzający się -4,-2
        assertTrue(res.isEmpty());
    }

    @Test
    public void testTheSameStretchsWhiteBox() {
        //punkt wspólny
        ArrayList<ILineSegment> als = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(1, 5), new TwoDPoint(3, 8));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(1, 5), new TwoDPoint(3, 8));

        als.add(line1);
        als.add(line2);

        Hashtable<IPoint, ILineSegment[]> res = dba.intersections(als.iterator());

        assertTrue(res.isEmpty());
    }

}