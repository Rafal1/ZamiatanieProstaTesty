import algs.model.problems.segmentIntersection.FigureChapter9Test;
import algs.model.problems.segmentIntersection.IntersectionsTest;
import algs.model.problems.segmentIntersection.LineSweepBasicTest;
import algs.model.problems.segmentIntersection.linkedlist.LineSweepTest;
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