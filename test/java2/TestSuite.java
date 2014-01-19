package java2;

import java2.algs.model.problems.segmentIntersection.FigureChapter9Test;
import java2.algs.model.problems.segmentIntersection.IntersectionsTest;
import java2.algs.model.problems.segmentIntersection.LineSweepBasicTest;
import java2.algs.model.problems.segmentIntersection.linkedlist.LineSweepTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LineSweepBasicTest.class,
        LineSweepTest.class,
        IntersectionsTest.class,
        FigureChapter9Test.class
        //simply add next classes
})
public class TestSuite {
}