import TaskTwo.Calculation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalculationTestAuto {

    @Parameterized.Parameters
    public static Collection<Integer[]> data(){
        return Arrays.asList(new Integer[][]{

        });
    }

    private Integer[] inArray;
    private Integer[] outArray;

    public CalculationTestAuto(Integer[] inArray, Integer[] outArray) {
        this.inArray = inArray;
        this.outArray = outArray;
    }

    Calculation calc;

    @Before
    public void init(){
        calc = new Calculation();
    }

    @Test
    public void getArrayTestOne() throws Exception {
        Assert.assertArrayEquals(inArray, calc.getCalculationArray(outArray));
    }
}
