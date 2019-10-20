package TaskThree;

public class Box<T extends Fruit>{

    final static Integer MAXCOUNT = 50;
    final static Double MAXWEIGHT = 75.0;
    static {
        System.out.println("Максимальная вместимость коробки = " + MAXCOUNT + " фруктов.");
        System.out.println("Максимальный вес = " + MAXWEIGHT + " единиц.");
    }

    private Integer countFruit;
    private Double weightFruit;

    <T> Box(){
        isEmpty();
    }

    void add(T fruit){
        if (this.countFruit + fruit.getCount() > MAXCOUNT)
            System.out.println("Превышено количество фруктов;");
        if (this.weightFruit + fruit.getWeightCountFruit() > MAXWEIGHT)
            System.out.println("Превышена масса фруктов;");
        else {
            this.countFruit += fruit.getCount();
            this.weightFruit += fruit.getWeightCountFruit();
        }
    }

    Integer getCountFruit(){
        return countFruit;
    }

    Double getWeight(){
        return weightFruit;
    }
    void isEmpty(){
        this.countFruit = 0;
        this.weightFruit = 0.0;
    }

    boolean compare(Box<?> o){
        if (this.weightFruit.equals(o.getWeight())) return true;
        else return false;
    }

    void isFill(Box<T> o){
        if (isFullCount(this.countFruit, o.getCountFruit()))
            System.err.println("Превышен максимально допустимое количество фруктов.");
        if (isFullWeight(this.weightFruit, o.getWeight()))
            System.err.println("Превышен максимально допустимый вес.");
        else {
            this.countFruit += o.getCountFruit();
            this.weightFruit += o.getWeight();
            System.out.println("Наполнение из ящика прошло успешно.");
            o.isEmpty();
        }
    }
    boolean isFullCount(int a, int b){
        return ((a + b) > MAXCOUNT) ? true : false;
    }
    boolean isFullWeight(double a, double b){
        return ((a + b) > MAXWEIGHT) ? true : false;
    }
}
