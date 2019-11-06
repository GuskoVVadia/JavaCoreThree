import TaskTwo.Calculation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculationTest {

    Calculation calc;

    @Before
    public void init(){
        calc = new Calculation();
    }

    @Test
    public void getArrayTestOne() throws Exception {

        Integer[] out = {4, 5, 6, 7};
        Integer[] in = {5, 6, 7};
        Assert.assertArrayEquals(in, calc.getCalculationArray(out));
    }
    @Test
    public void getArrayTestTwo() throws Exception {

        Integer[] out = {0, 5, 4, 7};
        Integer[] in = {7};
        Assert.assertArrayEquals(in, calc.getCalculationArray(out));
    }
    @Test(expected = Exception.class)
    public void getArrayTestThree() throws Exception {

        Integer[] out = {3, 5, 6, 7};
        Integer[] in = {5, 6, 7};
        Assert.assertArrayEquals(in, calc.getCalculationArray(out));
    }

    @Test (expected = NullPointerException.class)
    public void getArrayTestFour() throws Exception {

        Integer[] out = new Integer[3];
        Integer[] in = {5, 6, 7};
        Assert.assertArrayEquals(in, calc.getCalculationArray(out));
    }
}
