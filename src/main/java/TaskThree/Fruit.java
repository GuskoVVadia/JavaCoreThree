package TaskThree;

public class Fruit implements Comparable<Fruit>{

    protected Integer count;
    protected String name;

    protected Double weightOneFriut;
    protected Double weightCountFruit;

    Fruit(String name, int count){
        this.name = name;
        this.count = count;

        weightOneFriut = Fruits.valueOf(name).getWeight();
        weightCountFruit = count * weightOneFriut;
    }

    public String getName() {
        return name;
    }
    public Double getWeightOneFriut() {
        return weightOneFriut;
    }
    public Integer getCount(){
        return count;
    }
    public Double getWeightCountFruit(){
        return weightCountFruit;
    };


    @Override
    public int compareTo(Fruit o) {
        if ((this.weightCountFruit - o.weightCountFruit) > 0) return 1;
        if ((this.weightCountFruit - o.weightCountFruit) < 0) return -1;
        else return 0;
    }
}
