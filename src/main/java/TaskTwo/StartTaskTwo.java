package TaskTwo;

import java.util.ArrayList;

class StartTaskTwo {
    public static void main(String[] args) {

        Object[] arrayObject = {new Exception(), new Throwable(), "asd",
        12, 0.5, 0.000D, new Integer[5], new Object[2]};
        Integer[] arrayInteger = {15, 11, 22, 2, 36, 95, 50, 7};

        TransformArray transform = o ->{
            ArrayList<Object> local = new ArrayList<>(o.length);
            for (int i = 0; i < o.length; i++){
                local.add(o[i]);
            }
            return local;
        };


        ArrayList<Object> noname = new StartTaskTwo().changeArray(arrayObject);
        noname = new StartTaskTwo().changeArray(getArray(10));
        noname = new StartTaskTwo().changeArray(arrayInteger);

        noname = transform.changeArray(getArray(10));
        noname = transform.changeArray(arrayObject);
        noname = transform.changeArray(arrayInteger);

    }

    <T extends Object> ArrayList<T> changeArray(T[] array){
        ArrayList<T> arrayList = new ArrayList<>(array.length);
        for (T a: array){
            arrayList.add(a);
        }
        return arrayList;
    }

    static Object[] getArray(int length){
        ArrayList<Object> arrayList = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(new One(i));
        }
        return arrayList.toArray();
    }
}
