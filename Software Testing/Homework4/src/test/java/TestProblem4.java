import static org.junit.Assert.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TestProblem4 {

    private Problem_4_isPrimeShipper p4;

    @Before
    public void setUp() {
        p4 = new Problem_4_isPrimeShipper();
    }

    @Test
    @FileParameters("src/Problem4.csv")
    public void test(int testcaseNumber, double total, double shipping_cost, int num_items, int years_cust, boolean a)
    {
        assertEquals(a, p4.isPrimeShipper(num_items, years_cust, total, shipping_cost));
    }

}
