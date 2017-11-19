import static org.junit.Assert.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TestProblem5 {

    private Problem_5_calcY p5;

    @Before
    public void setUp() {
        p5 = new Problem_5_calcY();
    }

    @Test
    @FileParameters("src/Problem5.csv")
    public void test(int testcaseNumber, double x, double y)
    {
        assertEquals(y, p5.calcY(x), 0.01);
    }

}
