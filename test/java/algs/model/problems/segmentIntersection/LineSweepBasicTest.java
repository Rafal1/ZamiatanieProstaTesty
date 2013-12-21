package algs.model.problems.segmentIntersection;

import algs.model.ILineSegment;
import algs.model.IPoint;
import algs.model.twod.TwoDLineSegment;
import algs.model.twod.TwoDPoint;
import org.junit.Test;

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
    public void testBlackBox3() {
        //punkt wspólny
        ArrayList<ILineSegment> als = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(1, 5), new TwoDPoint(3, 8));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(-4, 2), new TwoDPoint(1, 5));
        als.add(line1);
        als.add(line2);

        Hashtable<IPoint, ILineSegment[]> res = dba.intersections(als.iterator());

        //spodziewany wynik
        IPoint expPoint = new TwoDPoint(1, 5);

        assertEquals(1, res.size());
        assertTrue(res.containsKey(expPoint));
    }

    @Test
    public void testBlackBox4() {
        //prostopadle i rownolegle
        //test nie przechodzi ze wzgledu na dokładność
        //-1.2000000000000002,0.6000000000000001 - końcówki!
        //0.0,0.0 - tu jest ok

        ArrayList<ILineSegment> als = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(-3, -6), new TwoDPoint(8, 16));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(-3, -3), new TwoDPoint(2, 7));
        TwoDLineSegment line3 = new TwoDLineSegment(new TwoDPoint(-4, 2), new TwoDPoint(5, -2.5));

        als.add(line1);
        als.add(line2);
        als.add(line3);

        Hashtable<IPoint, ILineSegment[]> res = dba.intersections(als.iterator());

        //spodziewany wynik
        IPoint expPoint = new TwoDPoint(0, 0);
        IPoint expPoint1 = new TwoDPoint(-1.2, 0.6);
        System.out.print(res.containsKey(expPoint1));

        assertEquals(2, res.size());
        assertTrue(res.containsKey(expPoint) && res.containsKey(expPoint1));
    }

    @Test
    public void testBlackBox5() {
        //pionowe i poziome linie
        ArrayList<ILineSegment> als = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(0, 4), new TwoDPoint(10, 4));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(2, 0), new TwoDPoint(2, 10));
        TwoDLineSegment line3 = new TwoDLineSegment(new TwoDPoint(6, 1), new TwoDPoint(6, 5));

        als.add(line1);
        als.add(line2);
        als.add(line3);

        Hashtable<IPoint, ILineSegment[]> res = dba.intersections(als.iterator());

        //spodziewany wynik
        IPoint expPoint = new TwoDPoint(2, 4);
        IPoint expPoint1 = new TwoDPoint(6, 4);

        assertEquals(2, res.size());
        assertTrue(res.containsKey(expPoint) && res.containsKey(expPoint1));
    }

    @Test
    public void testBlackBox6() {
        //pionowe i poziome linie
        ArrayList<ILineSegment> als = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(-1, -1), new TwoDPoint(2, 5));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(0.99999, 3), new TwoDPoint(1.0001, 3));

        als.add(line1);
        als.add(line2);

        Hashtable<IPoint, ILineSegment[]> res = dba.intersections(als.iterator());

        //spodziewany wynik
        IPoint expPoint = new TwoDPoint(1, 3);

        assertEquals(1, res.size());
        assertTrue(res.containsKey(expPoint));
    }
}