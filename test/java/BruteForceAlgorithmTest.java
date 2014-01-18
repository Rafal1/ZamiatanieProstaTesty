/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import algs.model.ILineSegment;
import algs.model.IPoint;
import algs.model.problems.segmentIntersection.BruteForceAlgorithm;
import algs.model.twod.TwoDLineSegment;
import algs.model.twod.TwoDPoint;
import org.junit.Before;
import org.junit.Test;

import java.util.Enumeration;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Marcin Laskowski
 */
public class BruteForceAlgorithmTest {

    public BruteForceAlgorithmTest() {
    }

    private Enumeration numer;
    private Hashtable<IPoint, ILineSegment[]> wynik;
    private BruteForceAlgorithm BF;
    private double eps;
    private boolean pytajnik;

    //=====================================

    private void testuj(TwoDLineSegment wejscie[], TwoDPoint punkty[]) {
        boolean[] punktySpr = new boolean[punkty.length];
        for (int i = 0; i < punkty.length; i++) {
            punktySpr[i] = false;
        }

        wynik = BF.intersections(wejscie);
        numer = wynik.keys();

        while (numer.hasMoreElements()) {
            IPoint punkt = (IPoint) numer.nextElement();
            ILineSegment wypis[];

            pytajnik = false;
            for (int i = 0; i < punkty.length; i++) {
                if (punkt.equals(punkty[i])) {
                    pytajnik = true;
                    punktySpr[i] = true;
                }
            }
            assertTrue(pytajnik);

            wypis = (ILineSegment[]) wynik.get(punkt);

            double x1s = wypis[0].getStart().getX();
            double y1s = wypis[0].getStart().getY();
            double x1k = wypis[0].getEnd().getX();
            double y1k = wypis[0].getEnd().getY();
            double x2s = wypis[1].getStart().getX();
            double y2s = wypis[1].getStart().getY();
            double x2k = wypis[1].getEnd().getX();
            double y2k = wypis[1].getEnd().getY();
            double x0 = punkt.getX();
            double y0 = punkt.getY();

            double odcinek1 = Math.sqrt((x1k - x1s) * (x1k - x1s) + (y1k - y1s) * (y1k - y1s));
            double odcinek1a = Math.sqrt((x0 - x1k) * (x0 - x1k) + (y0 - y1k) * (y0 - y1k));
            double odcinek1b = Math.sqrt((x1s - x0) * (x1s - x0) + (y1s - y0) * (y1s - y0));
            assertEquals(odcinek1, odcinek1a + odcinek1b, eps * 100);

            double odcinek2 = Math.sqrt((x2k - x2s) * (x2k - x2s) + (y2k - y2s) * (y2k - y2s));
            double odcinek2a = Math.sqrt((x0 - x2k) * (x0 - x2k) + (y0 - y2k) * (y0 - y2k));
            double odcinek2b = Math.sqrt((x2s - x0) * (x2s - x0) + (y2s - y0) * (y2s - y0));
            assertEquals(odcinek2, odcinek2a + odcinek2b, eps * 100);
        }

        for (int i = 0; i < punkty.length; i++) {
            assertEquals(punktySpr[i], true);
        }
    }

    //======================================================


    @Before
    public void setUp() {
        BF = new BruteForceAlgorithm();
        eps = 0.000000000000001;    //dokladnosc obliczen
    }

    /**
     * Test of intersections method, of class BruteForceAlgorithm.
     */

    @Test
    public void test2ProstePrzecinajaceSie() {  //przechodzi przez kazda linijke programu

        TwoDLineSegment seg1 = new TwoDLineSegment(-2, 4, 1, -5);   //kolejne odcinki
        TwoDLineSegment seg2 = new TwoDLineSegment(-4, -2, 2, 4);
        TwoDLineSegment wejscie[] = {seg1, seg2};   //laczenie odcinkow w dane wejsciowe
        TwoDPoint punkty[] = {new TwoDPoint(-1, 1)};    //punkty w ktorych ma nastapic przeciecie

        testuj(wejscie, punkty);
    }

    @Test
    public void testNull() {  //nie wchodzi w if'a

        TwoDLineSegment wejscie[] = {};
        TwoDPoint punkty[] = {};

        testuj(wejscie, punkty);
    }

    @Test
    public void test2OdcinkiRownolegle() {  //nie wchodzi w for'a

        TwoDLineSegment seg1 = new TwoDLineSegment(-2, 2, -2, -2);
        TwoDLineSegment seg2 = new TwoDLineSegment(-3, 3, -3, -3);
        TwoDLineSegment wejscie[] = {seg1, seg2};
        TwoDPoint punkty[] = {};

        testuj(wejscie, punkty);
    }

}