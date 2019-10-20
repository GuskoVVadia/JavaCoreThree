package TaskThree;

public enum Fruits {
    APPLE(1.0), ORANGE( 1.5);
    private double weight;

    Fruits(double weight){
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
