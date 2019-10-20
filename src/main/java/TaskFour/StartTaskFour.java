package TaskFour;

import java.util.ArrayList;
import java.util.HashMap;

class StartTaskFour {
    public static void main(String[] args) {

        ArrayList<SomeClass> list = new StartTaskFour().getList();
        HashMap<String, ArrayList<Integer>> hashMap = new StartTaskFour().getHashMap(list);

        System.out.println(list);
        System.out.println(hashMap);




    }

    ArrayList<SomeClass> getList(){
        ArrayList<SomeClass> localList = new ArrayList<>();
        for (int i = 1, j = 1; i <= 8; i++) {
            if (i == 4) ++j;
            if (i == 6) ++j;
            if (i == 8) ++j;
            String name = "Test" + j;
            localList.add(new SomeClass(i, name));
        }
        return localList;
    }

//    HashMap<String, Integer[]> getHashMap(ArrayList<SomeClass> list){
//        HashMap<String, Integer[]> hashMap = new HashMap<>();
//
//        for (SomeClass o: list){
//
//            if (hashMap.containsKey(o.getName())){
//                Integer[] array = hashMap.get(o.getName());
//                Integer[] copy = new Integer[array.length + 1];
//                System.arraycopy(array, 0, copy, 0, array.length);
//                copy[array.length + 1] = o.getId();
//
//            }else {
//                ArrayList<Integer> valueId = new ArrayList<>();
//                valueId.add(o.getId());
//                hashMap.put(o.getName(), valueId);
//            }
//        }
//        return hashMap;
//    }

//    HashMap<String, ArrayList<Integer>> getHashMap(ArrayList<SomeClass> list){
//        HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>();
//
//        for (SomeClass o: list){
//            if (hashMap.containsKey(o.getName())){
//                ArrayList<Integer> value = hashMap.get(o.getName());
//                value.add(o.getId());
//            }else {
//                ArrayList<Integer> valueId = new ArrayList<>();
//                valueId.add(o.getId());
//                hashMap.put(o.getName(), valueId);
//            }
//        }
//        return hashMap;
//    }
    HashMap<String, ArrayList<Integer>> getHashMap(ArrayList<SomeClass> list){
        HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>();
        for (SomeClass o: list){

            if (hashMap.containsKey(o.getName())) {
                ArrayList<Integer> copy = hashMap.get(o.getName());
                copy.add(o.getId());
                hashMap.put(o.getName(), copy);

            }else{
                ArrayList<Integer> local = new ArrayList<Integer>();
                local.add(o.getId());
                hashMap.putIfAbsent(o.getName(), local);
            }
        }
        return hashMap;
    }
}
