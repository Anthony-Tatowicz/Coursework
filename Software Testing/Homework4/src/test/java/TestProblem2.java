import static org.junit.Assert.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TestProblem2 {

    private Problem_2_GndCollWarnClass p2;

    @Before
    public void setUp() {
        p2 = new Problem_2_GndCollWarnClass();
    }

    @Test
    @FileParameters("src/Problem2.csv")
    public void test(int testcaseNumber, boolean gear_up, float alt_feet, boolean coll_caut, boolean coll_warn, boolean coll_alert, boolean emer_GD)
    {
        p2.gndCollWarning(gear_up, alt_feet);
        assertEquals(coll_caut, p2.isGndCollCaut());
        assertEquals(coll_warn, p2.isGndCollWarn());
        assertEquals(coll_alert, p2.isGndCollAlert());
        assertEquals(emer_GD, p2.isEmerGD());
    }

}