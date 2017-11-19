import static org.junit.Assert.*;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class TestProblem3 {

    private Problem_3_maverickFinancialPlanner p3;

    @Before
    public void setUp() {
        p3 = new Problem_3_maverickFinancialPlanner();
    }

    @Test
    @FileParameters("src/Problem3.csv")
    public void test(int testcaseNumber, int number_shares, float port_amt)
    {
        p3.calculate_balance(number_shares, 140);
        assertEquals(port_amt, p3.getPortfolio_amount(), 0.01);
    }

}