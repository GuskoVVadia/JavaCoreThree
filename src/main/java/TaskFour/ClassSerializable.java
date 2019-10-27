package TaskFour;

import java.io.Serializable;

class ClassSerializable implements Serializable {
    private int i;

    ClassSerializable(int i) {
        this.i = i;
    }

    void show(){
        System.out.println("i = " + i);
    }

    @Override
    public String toString() {
        return "ClassSerializable{" +
                "i=" + i +
                '}';
    }
}
