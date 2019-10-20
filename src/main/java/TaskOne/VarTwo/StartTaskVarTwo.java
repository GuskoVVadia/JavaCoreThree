package TaskOne.VarTwo;

class StartTaskVarTwo {
    public static void main(String[] args) {

        Object[] a = {new Object(), 15, new Exception(), new Throwable(), 25.2};
        Integer[] b = {15, 2, 13, 5, 7, 6};
        Number[] c = {22.0, 15, 5, 6L, 33D, 12};

        try {
            shift(a, 0, 2);
            shift(b, 1, 2);
            shift(null, 0, 1);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    static <T extends Object> void shift(T[] array, int valueBegin, int valueEnd) throws Exception
    {
        if (array == null) throw new Exception("Объект не может быть null");
        if ((array[valueBegin] == null) || (array[valueEnd] == null)) throw
                new Exception("Ошибка элементов в массиве: not null");
        if ((valueBegin > array.length) || (valueBegin < 0)) throw
                new Exception("Ошибка числа начальной позиции смещения.");
        if ((valueEnd > array.length) || (valueEnd < 0)) throw
                new Exception("Ошибка числа конечной позиции смещения.");
        if (valueBegin == valueEnd) throw new Exception("Единицы смещения не должны быть одинаковыми.");

        T noname = array[valueBegin];
        array[valueBegin] = array[valueEnd];
        array[valueEnd] = noname;

    }
}
