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
    
    //=====================================
    
    private void testuj(TwoDLineSegment wejscie[], TwoDPoint punkty[]){
        boolean[] punktySpr = new boolean[punkty.length];
        for (int i = 0; i < punkty.length; i++){
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
            assertEquals(odcinek1, odcinek1a + odcinek1b, eps*100);

            double odcinek2 = Math.sqrt((x2k - x2s) * (x2k - x2s) + (y2k - y2s) * (y2k - y2s));
            double odcinek2a = Math.sqrt((x0 - x2k) * (x0 - x2k) + (y0 - y2k) * (y0 - y2k));
            double odcinek2b = Math.sqrt((x2s - x0) * (x2s - x0) + (y2s - y0) * (y2s - y0));
            assertEquals(odcinek2, odcinek2a + odcinek2b, eps*100);
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

    @After
    public void tearDown() {
    }

    
    /**
     * Test of intersections method, of class BruteForceAlgorithm.
     */
    
    @Test
    public void test2ProstePrzecinajaceSie() {  //prosty test, 2 proste przecinajace sie
        
        TwoDLineSegment seg1 = new TwoDLineSegment(-2, 4, 1, -5);   //kolejne odcinki
        TwoDLineSegment seg2 = new TwoDLineSegment(-4, -2, 2, 4);
        TwoDLineSegment wejscie[] = {seg1, seg2};   //laczenie odcinkow w dane wejsciowe
        TwoDPoint punkty[] = {new TwoDPoint(-1, 1)};    //punkty w ktorych ma nastapic przeciecie
        
        testuj(wejscie, punkty);
    }
    
    @Test
    public void test3Odcinki() {  //trzy odcinki, w tym jeden pionowy i jeden poziomy
        
        TwoDLineSegment seg1 = new TwoDLineSegment(-5, 2, 3, 2);
        TwoDLineSegment seg2 = new TwoDLineSegment(-2, 5, -2, -3);
        TwoDLineSegment seg3 = new TwoDLineSegment(-3, -3, 1, 5);
        TwoDLineSegment wejscie[] = {seg1, seg2, seg3};
        TwoDPoint punkty[] = {new TwoDPoint(-2, 2), new TwoDPoint(-0.5,2), new TwoDPoint(-2,-1)};
        
        testuj(wejscie, punkty);
    }
    
    @Test
    public void test3OdcinkiWspolnyPunkt() {  //trzy odcinki, majace wspolny punkt przeciecia

        TwoDLineSegment seg1 = new TwoDLineSegment(-3, 0, 3, 0);
        TwoDLineSegment seg2 = new TwoDLineSegment(-2, 2, 2, -2);
        TwoDLineSegment seg3 = new TwoDLineSegment(-1, -2, 1, 2);
        TwoDLineSegment wejscie[] = {seg1, seg2, seg3};
        TwoDPoint punkty[] = {new TwoDPoint(0, 0)};
                
        testuj(wejscie, punkty);
    }
    
    @Test
    public void testNull() {  //brak odcinków

        TwoDLineSegment wejscie[] = {};
        TwoDPoint punkty[] = {};
                
        testuj(wejscie, punkty);
    }
    
    @Test
    public void test2OdcinkiRownolegle() {  //dwa odcinki rownolegle

        TwoDLineSegment seg1 = new TwoDLineSegment(-2, 2, -2, -2);
        TwoDLineSegment seg2 = new TwoDLineSegment(-3, 3, -3, -3);
        TwoDLineSegment wejscie[] = {seg1, seg2};
        TwoDPoint punkty[] = {};
                
        testuj(wejscie, punkty);
    }
    
    @Test
    public void test2OdcinkiProstopadle() {  //dwa odcinki prostopadle

        TwoDLineSegment seg1 = new TwoDLineSegment(0, 5, 0, -9);
        TwoDLineSegment seg2 = new TwoDLineSegment(-46, 0, 2.6, 0);
        TwoDLineSegment wejscie[] = {seg1, seg2};
        TwoDPoint punkty[] = {new TwoDPoint(0,0)};
                
        testuj(wejscie, punkty);
    }
    
    @Test
    public void testDokladnosc() {  //sprawdzenie dokladnosci obliczen

        TwoDLineSegment seg1 = new TwoDLineSegment(-13.71108, -4.23499, 7.14234, 22.74214);
        TwoDLineSegment seg2 = new TwoDLineSegment(4.21435, 14.24521, -11.67434, 3.94042);
        TwoDLineSegment wejscie[] = {seg1, seg2};
        TwoDPoint punkty[] = {new TwoDPoint(-3.085553669955579, 9.510774729431969)};
                
        testuj(wejscie, punkty);
    }
    
    @Test
    public void testDuzoOdcinkow() {  //masa odcinkow
        
        TwoDLineSegment seg1 = new TwoDLineSegment(10, 10, 10, 1);
        TwoDLineSegment seg2 = new TwoDLineSegment(9, 10, 9, 1);
        TwoDLineSegment seg3 = new TwoDLineSegment(8, 10, 8, 1);
        TwoDLineSegment seg4 = new TwoDLineSegment(7, 10, 7, 1);
        TwoDLineSegment seg5 = new TwoDLineSegment(6, 10, 6, 1);
        TwoDLineSegment seg6 = new TwoDLineSegment(5, 10, 5, 1);
        TwoDLineSegment seg7 = new TwoDLineSegment(4, 10, 4, 1);
        TwoDLineSegment seg8 = new TwoDLineSegment(3, 10, 3, 1);
        TwoDLineSegment seg9 = new TwoDLineSegment(2, 10, 2, 1);
        TwoDLineSegment seg10 = new TwoDLineSegment(1, 10, 1, 1);
        TwoDLineSegment seg11 = new TwoDLineSegment(1, 1, 10, 1);
        TwoDLineSegment seg12 = new TwoDLineSegment(1, 2, 10, 2);
        TwoDLineSegment seg13 = new TwoDLineSegment(1, 3, 10, 3);
        TwoDLineSegment seg14 = new TwoDLineSegment(1, 4, 10, 4);
        TwoDLineSegment seg15 = new TwoDLineSegment(1, 5, 10, 5);
        TwoDLineSegment seg16 = new TwoDLineSegment(1, 6, 10, 6);
        TwoDLineSegment seg17 = new TwoDLineSegment(1, 7, 10, 7);
        TwoDLineSegment seg18 = new TwoDLineSegment(1, 8, 10, 8);
        TwoDLineSegment seg19 = new TwoDLineSegment(1, 9, 10, 9);
        TwoDLineSegment seg20 = new TwoDLineSegment(1, 10, 10, 10);
        
        TwoDLineSegment wejscie[] = {seg1, seg2, seg3, seg4, seg5, seg6, seg7, seg8, seg9, seg10,
                                     seg11, seg12, seg13, seg14, seg15, seg16, seg17, seg18, seg19, seg20};
        TwoDPoint punkty[];
        TwoDPoint[] temp = new TwoDPoint[100];
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                temp[i*10 + j] = new TwoDPoint(i+1,j+1);
            }
        }
        punkty = temp;
        testuj(wejscie, punkty);
    }
    
}