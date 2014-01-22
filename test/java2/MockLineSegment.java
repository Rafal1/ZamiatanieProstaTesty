package java2;

import algs.model.IPoint;
import algs.model.twod.TwoDLineSegment;
import algs.model.twod.TwoDPoint;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.*;

/**
 * @author Rafal Zawadzki
 */
@RunWith(EasyMockRunner.class)
public class MockLineSegment {

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
    public void testLineConstr() {

        expect(mock.getX()).andReturn(1.0);
        expect(mock.getY()).andReturn(5.0);
        replay(mock);

        expect(mock1.getX()).andReturn(2.0);
        expect(mock1.getY()).andReturn(3.0);
        replay(mock1);

        TwoDLineSegment cx = new TwoDLineSegment(mock, mock1);

        verify(mock);
        verify(mock1);
    }

}
