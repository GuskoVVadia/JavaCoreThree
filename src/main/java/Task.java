import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Task {

    static void forest(Integer[] array){

        TreeMap<Integer, Integer> map = new TreeMap<>();
        Integer localValue = 0;

        for(Integer i: array){
            if (map.containsKey(i)){
                localValue = map.get(i);
                map.put(i, (localValue + 1));
            }else {
                map.putIfAbsent(i, 1);
            }
        }

        for (Map.Entry<Integer, Integer> item: map.entrySet()){
            System.out.printf("Число : %d, количество в массиве: %d \n", item.getKey(), item.getValue());
        }
    }

    public static void main(String[] args) {
        Integer[] array = {5, 1, 5, 2, 2, 4, 1, 4, 5, 1, 5, 3, 2, 4, 4, 4, 5, 1, 3, 4, 2};
        Task.forest(array);
    }
}
