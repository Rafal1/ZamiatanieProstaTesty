/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algs.model.problems.segmentIntersection;

import algs.model.ILineSegment;
import algs.model.IPoint;
import algs.model.twod.*;
import java.util.Enumeration;
import java.util.Hashtable;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
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
    
    private static int ileP(int i){
        if (i<=1) return 0;
        return ((i-1)*i)/2;
    }
    
    @Before
    public void setUp() {
        BF = new BruteForceAlgorithm();
        eps = 0.000000000000001;    //dok³adnosc obliczen
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of intersections method, of class BruteForceAlgorithm.
     */
    @Test
    public void testIntersections1() {  //prosty test, 2 proste przecinajace sie
        wynik = new Hashtable();
        TwoDLineSegment seg1 = new TwoDLineSegment(-2, 4, 1, -5);   //kolejne odcinki
        TwoDLineSegment seg2 = new TwoDLineSegment(-4, -2, 2, 4);
        TwoDLineSegment wejscie[] = {seg1, seg2};   //laczenie odcinkow w dane wejsciowe
        TwoDPoint punkty[] = {new TwoDPoint(-1, 1)};    //punkty w ktorych ma nastapic przeciecie
        int punktySpr[] = {ileP(2)};  //sprawdza czy kazdy punkt jest znaleziony, tyle false ile punktow

        wynik = BF.intersections(wejscie);
        numer = wynik.keys();

        while (numer.hasMoreElements()) {
            IPoint punkt = (IPoint) numer.nextElement();
            ILineSegment wypis[];

            pytajnik = false;
            for (int i = 0; i < punkty.length; i++) {   //sprawdzamy czy punkt znajduje sie w tych ktore chcielismy uzyskac...
                if (punkt.equals(punkty[i])) {
                    pytajnik = true;
                    punktySpr[i]--;    //...i odnotowujemy to
                }
            }
            assertTrue(pytajnik);

            wypis = (ILineSegment[]) wynik.get(punkt);

            double x1s = wypis[0].getStart().getX();    //to dla czytelnosci
            double y1s = wypis[0].getStart().getY();
            double x1k = wypis[0].getEnd().getX();
            double y1k = wypis[0].getEnd().getY();
            double x2s = wypis[1].getStart().getX();
            double y2s = wypis[1].getStart().getY();
            double x2k = wypis[1].getEnd().getX();
            double y2k = wypis[1].getEnd().getY();
            double x0 = punkt.getX();
            double y0 = punkt.getY();

            double odcinek1 = Math.sqrt((x1k - x1s) * (x1k - x1s) + (y1k - y1s) * (y1k - y1s)); //sprawdzamy czy punkt lezy na jednym odcinku
            double odcinek1a = Math.sqrt((x0 - x1k) * (x0 - x1k) + (y0 - y1k) * (y0 - y1k));
            double odcinek1b = Math.sqrt((x1s - x0) * (x1s - x0) + (y1s - y0) * (y1s - y0));
            assertEquals(odcinek1, odcinek1a + odcinek1b, eps);

            double odcinek2 = Math.sqrt((x2k - x2s) * (x2k - x2s) + (y2k - y2s) * (y2k - y2s)); //sprawdzamy czy na drugim
            double odcinek2a = Math.sqrt((x0 - x2k) * (x0 - x2k) + (y0 - y2k) * (y0 - y2k));
            double odcinek2b = Math.sqrt((x2s - x0) * (x2s - x0) + (y2s - y0) * (y2s - y0));
            assertEquals(odcinek2, odcinek2a + odcinek2b, eps);
        }

        for (int i = 0; i < punkty.length; i++) {   //sprawdzamy czy algorytm znalazl wszystkie punkty
            assertEquals(punktySpr[i],0);
        }
    }

    
    
    @Test
    public void testIntersections2() {  //trzy odcinki, w tym jeden pionowy i jeden poziomy
        wynik = new Hashtable();

        TwoDLineSegment seg1 = new TwoDLineSegment(-5, 2, 3, 2);
        TwoDLineSegment seg2 = new TwoDLineSegment(-2, 5, -2, -3);
        TwoDLineSegment seg3 = new TwoDLineSegment(-3, -3, 1, 5);
        TwoDLineSegment wejscie[] = {seg1, seg2, seg3};
        TwoDPoint punkty[] = {new TwoDPoint(-2, 2), new TwoDPoint(-0.5,2), new TwoDPoint(-2,-1)};
        int punktySpr[] = {ileP(2), ileP(2), ileP(2)};

        wynik = BF.intersections(wejscie);
        numer = wynik.keys();

        while (numer.hasMoreElements()) {
            IPoint punkt = (IPoint) numer.nextElement();
            ILineSegment wypis[];

            pytajnik = false;
            for (int i = 0; i < punkty.length; i++) {
                if (punkt.equals(punkty[i])) {
                    pytajnik = true;
                    punktySpr[i]--;
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
            assertEquals(odcinek1, odcinek1a + odcinek1b, eps);

            double odcinek2 = Math.sqrt((x2k - x2s) * (x2k - x2s) + (y2k - y2s) * (y2k - y2s));
            double odcinek2a = Math.sqrt((x0 - x2k) * (x0 - x2k) + (y0 - y2k) * (y0 - y2k));
            double odcinek2b = Math.sqrt((x2s - x0) * (x2s - x0) + (y2s - y0) * (y2s - y0));
            assertEquals(odcinek2, odcinek2a + odcinek2b, eps);
        }

        for (int i = 0; i < punkty.length; i++) {
            assertEquals(punktySpr[i],0);
        }
    }
    
    @Test
    public void testIntersections3() {  //trzy odcinki, majace wspolny punkt przeciecia
        wynik = new Hashtable();

        TwoDLineSegment seg1 = new TwoDLineSegment(-3, 0, 3, 0);
        TwoDLineSegment seg2 = new TwoDLineSegment(-2, 2, 2, -2);
        TwoDLineSegment seg3 = new TwoDLineSegment(-1, -2, 1, 2);
        TwoDLineSegment wejscie[] = {seg1, seg2, seg3};
        TwoDPoint punkty[] = {new TwoDPoint(0, 0)};
        int punktySpr[] = {ileP(2)};

        wynik = BF.intersections(wejscie);
        numer = wynik.keys();

        while (numer.hasMoreElements()) {
            IPoint punkt = (IPoint) numer.nextElement();
            ILineSegment wypis[];

            pytajnik = false;
            for (int i = 0; i < punkty.length; i++) {
                if (punkt.equals(punkty[i])) {
                    pytajnik = true;
                    punktySpr[i]--;
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
            assertEquals(odcinek1, odcinek1a + odcinek1b, eps);

            double odcinek2 = Math.sqrt((x2k - x2s) * (x2k - x2s) + (y2k - y2s) * (y2k - y2s));
            double odcinek2a = Math.sqrt((x0 - x2k) * (x0 - x2k) + (y0 - y2k) * (y0 - y2k));
            double odcinek2b = Math.sqrt((x2s - x0) * (x2s - x0) + (y2s - y0) * (y2s - y0));
            assertEquals(odcinek2, odcinek2a + odcinek2b, eps);
        }

        for (int i = 0; i < punkty.length; i++) {
            assertEquals(punktySpr[i],0);
        }
    }
}