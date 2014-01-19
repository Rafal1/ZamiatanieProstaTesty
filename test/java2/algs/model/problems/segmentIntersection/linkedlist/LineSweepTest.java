package java2.algs.model.problems.segmentIntersection.linkedlist;

import algs.model.ILineSegment;
import algs.model.IPoint;
import algs.model.problems.segmentIntersection.linkedlist.LineSweep;
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
public class LineSweepTest {
    @Test
    //   sprawdzenie zgodnosci wyników tych samych algorytmów z różną strukturą danych
    public void testTheSameResultsLineSweep() {
        LineSweep alg = new LineSweep(); // LinkedList - lower performance
        algs.model.problems.segmentIntersection.LineSweep alg1 = new algs.model.problems.segmentIntersection.LineSweep();
        ArrayList<ILineSegment> lines = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(0, 4), new TwoDPoint(10, 4));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(2, 0), new TwoDPoint(2, 10));
        TwoDLineSegment line3 = new TwoDLineSegment(new TwoDPoint(6, 1), new TwoDPoint(6, 5));

        lines.add(line1);
        lines.add(line2);
        lines.add(line3);

        Hashtable<IPoint, ILineSegment[]> results = alg.intersections(lines.iterator());
        Hashtable<IPoint, ILineSegment[]> results1 = alg1.intersections(lines.iterator());

        IPoint expPoint = new TwoDPoint(2, 4);
        IPoint expPoint1 = new TwoDPoint(6, 4);

        assertEquals(2, results.size());
        assertEquals(2, results1.size());
        assertTrue(results.containsKey(expPoint) && results.containsKey(expPoint1));
        assertTrue(results1.containsKey(expPoint) && results1.containsKey(expPoint1));

    }

    @Test
    //porównanie czasu działania algorytmów z różną strukturą danych
    public void testTimeLineSweep() {
        LineSweep alg = new LineSweep(); // LinkedList - lower performance
        algs.model.problems.segmentIntersection.LineSweep alg1 = new algs.model.problems.segmentIntersection.LineSweep();
        ArrayList<ILineSegment> lines = new ArrayList<ILineSegment>();

        TwoDLineSegment line1 = new TwoDLineSegment(new TwoDPoint(-3, -6), new TwoDPoint(8, 16));
        TwoDLineSegment line2 = new TwoDLineSegment(new TwoDPoint(-3, -3), new TwoDPoint(2, 7));
        TwoDLineSegment line3 = new TwoDLineSegment(new TwoDPoint(-4, 2), new TwoDPoint(5, -2.5));

        lines.add(line1);
        lines.add(line2);
        lines.add(line3);

        Long time1 = System.nanoTime();
        Hashtable<IPoint, ILineSegment[]> results1 = alg1.intersections(lines.iterator());
        Long time2 = System.nanoTime();
        Long diff21 = time2 - time1;

        Long timeA = System.nanoTime();
        Hashtable<IPoint, ILineSegment[]> results = alg.intersections(lines.iterator());
        Long timeB = System.nanoTime();
        Long diffBA = timeB - timeA;

        System.out.println("Czas wykonania alg. ze strukturą LinkedListLineState: " + diffBA + ", czas alg. ze strukturą LineState: " + diff21);
        //alg.time() - doesn't work
        assertTrue(diff21 < diffBA);
    }
}
