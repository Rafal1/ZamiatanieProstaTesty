package algs.model.problems.segmentIntersection;

import org.junit.*;
import static org.easymock.EasyMock.*;

/**
 * @author Rafal Zawadzki
 */
public class LineSweepMock {

//    private ClassUnderTest classUnderTest;
//    private Collaborator mock;

    @Before
    public void setUp() {
        classUnderTest = new ClassUnderTest();
        classUnderTest.setListener(mock);
        mock = createMock(Collaborator.class); // 1
        classUnderTest = new ClassUnderTest();
        classUnderTest.setListener(mock);
    }

    @Test
    public void testRemoveNonExistingDocument() {
        // This call should not lead to any notification
        // of the Mock Object:
        classUnderTest.removeDocument("Does not exist");
    }

    @Before
    public void setUp() {
        mock = createMock(Collaborator.class); // 1
        classUnderTest = new ClassUnderTest();
        classUnderTest.setListener(mock);
    }

    @Test
    public void testRemoveNonExistingDocument() {
        // 2 (we do not expect anything)
        replay(mock); // 3
        classUnderTest.removeDocument("Does not exist");
    }

    @Test
    public void testRemoveNonExistingDocument() {
        // 2 (we do not expect anything)
        replay(mock); // 3
        classUnderTest.removeDocument("Does not exist");
    }
}