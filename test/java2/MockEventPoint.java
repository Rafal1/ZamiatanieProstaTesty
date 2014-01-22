package java2;

import algs.model.IPoint;
import algs.model.problems.segmentIntersection.EventPoint;
import algs.model.twod.TwoDPoint;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Rafal Zawadzki
 */
@RunWith(EasyMockRunner.class)
public class MockEventPoint {

    @Mock
    private IPoint mock;

    @Mock
    private IPoint mock1;

    @Before
    public void setUp() throws Exception {
        mock = createMock(TwoDPoint.class);
        mock1 = createMock(TwoDPoint.class);
    }

    @Test
    public void testCompareY() {

        expect(mock.getY()).andReturn(5.0);
        replay(mock);

        expect(mock1.getY()).andReturn(2.0);
        replay(mock1);

        EventPoint e1 = new EventPoint(mock);
        EventPoint e2 = new EventPoint(mock1);

        Integer r = e1.compare(e1, e2);

        verify(mock);
        verify(mock1);

        assertEquals((Object) r,-1);

    }

    @Test
    public void testCompareX() {

        expect(mock.getY()).andReturn(5.0);
        expect(mock.getX()).andReturn(5.0);

        replay(mock);

        expect(mock1.getY()).andReturn(5.0);
        expect(mock1.getX()).andReturn(2.0);

        replay(mock1);

        EventPoint e1 = new EventPoint(mock);
        EventPoint e2 = new EventPoint(mock1);

        Integer r = e1.compare(e1, e2);

        verify(mock);
        verify(mock1);

        assertEquals((Object) r,1);

    }

}
