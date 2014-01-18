package java.algs.model.problems.segmentIntersection;

import algs.model.ILineSegment;
import algs.model.IPoint;
import algs.model.problems.segmentIntersection.BruteForceAlgorithm;
import algs.model.problems.segmentIntersection.linkedlist.LineSweep;
import algs.model.twod.TwoDLineSegment;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Hashtable;


public class FigureChapter9Test extends TestCase {

	@Test
	public void testThis() {
		LineSweep dba1 = new LineSweep();
		BruteForceAlgorithm dba2 = new BruteForceAlgorithm();
		
		ILineSegment[] segments = new ILineSegment[] {
				new TwoDLineSegment(0,3,0,13),
				new TwoDLineSegment(0,13,12,1),
				new TwoDLineSegment(0,3,8,7),
				new TwoDLineSegment(8,7,13,2),
				new TwoDLineSegment(9,0,12,9),
				new TwoDLineSegment(0,3,12,3)
		};

		Hashtable<IPoint, ILineSegment[]> res = dba1.intersections(segments);
		for (IPoint ip : res.keySet()) {
			System.out.println (ip + ":");
			ILineSegment[] ils = res.get(ip);
			for (int i = 0; i < ils.length; i++) {
				System.out.println ("  " + ils[i]);
			}
		}
		long time1 = dba1.time();
			
		/* Hashtable<IPoint, ILineSegment[]> res2 = */ dba2.intersections(segments);
		long time2 = dba2.time();
			
		System.out.println (res.size() + "," + time1 + "," + time2);
			
	}
}
