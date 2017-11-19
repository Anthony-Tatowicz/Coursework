import static org.junit.Assert.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TestProblem1 {

    private Problem_1_setWarnings p1;

    @Before
    public void setUp() {
        p1 = new Problem_1_setWarnings();
    }

    @Test
    @FileParameters("src/Problem1.csv")
    public void test(int testcaseNumber, float distance, boolean red_light, boolean yellow_light, boolean green_light, boolean buzzer, boolean brakes)
    {
        p1.setWarnings(distance);
        assertEquals(red_light, p1.isRedLight());
        assertEquals(yellow_light, p1.isYellowLight());
        assertEquals(green_light, p1.isGreenLight());
        assertEquals(buzzer, p1.isBuzzer());
        assertEquals(brakes, p1.isBrakes());
    }

}