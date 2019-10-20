package TaskThree;

public class StartTaskThree {
    public static void main(String[] args) {

        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Apple> appleBox3 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();

        appleBox1.add(new Apple(35));
        appleBox2.add(new Apple(35));
        appleBox3.add(new Apple(2));
        orangeBox1.add(new Orange(35));
        orangeBox2.add(new Orange(16));

        double weight = appleBox1.getWeight();
        boolean checkApple = appleBox1.compare(appleBox2);
        boolean checkAO = appleBox1.compare(orangeBox1);
        appleBox2.isFill(appleBox3);
        orangeBox1.isFill(orangeBox2);

    }
}
