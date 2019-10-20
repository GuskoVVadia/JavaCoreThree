package TaskOne.VarOne;

import java.util.ArrayList;

class StartTaskVarOne {


    public static void main(String[] args) {

        ShiftArray displacement = (objectArray, valueBegin, valueEnd) ->{
            if (objectArray == null) throw
                    new Exception("Массив не может быть пуст/null");
            if ((objectArray[valueBegin] == null) || (objectArray[valueEnd] == null)) throw
                    new Exception("Ошибка элементов в массиве: not null");
            if ((valueBegin > objectArray.length) || (valueBegin < 0)) throw
                    new Exception("Ошибка числа начальной позиции смещения.");
            if ((valueEnd > objectArray.length) || (valueEnd < 0)) throw
                    new Exception("Ошибка числа конечной позиции смещения.");
            if (valueBegin == valueEnd) throw new Exception("Единицы смещения не должны быть одинаковыми.");

            Object noname = objectArray[valueBegin];
            objectArray[valueBegin] = objectArray[valueEnd];
            objectArray[valueEnd] = noname;
        };


        Number[] a = {15, 1, 2.0, 5f, 52, 78, 66, 60.5};    //8
        Object[] b = new Object[4];
        Object[] c = {new One(), new Three(), new Two(), new Four(), new One()};


        try{
            displacement.shift(c, 0, 1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
