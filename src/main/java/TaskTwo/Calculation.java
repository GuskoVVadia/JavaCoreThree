package TaskTwo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Calculation {
    private static final Logger logger = LogManager.getLogger(Calculation.class);

    public Integer[] getCalculationArray(Integer[] array) throws Exception
    {
        int valueFindPosition = -1;
        int valueLocalLength = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) throw new NullPointerException("Null element.");
            if (array[i] == 4) valueFindPosition = i;
        }
        if (valueFindPosition == -1) throw new RuntimeException();

        if ((valueLocalLength = array.length - (valueFindPosition + 1)) == 0)
            throw new Exception("Неверное количество элементов массива.");

        ArrayList<Integer> al = new ArrayList<>();
        for (int i = valueFindPosition +1; i < array.length; i++)
            al.add(array[i]);

        return al.toArray(new Integer[valueLocalLength]);
    }
}
