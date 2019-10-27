package Test;

import java.io.Serializable;

public class TestClassSerializable implements Serializable {
    private int i;
    TestClassSerializable(int i){
        this.i = i;
    }
    void show(){
        System.out.println("i = " + i);
    }

    @Override
    public String toString() {
        return "TestClassSerializable{" +
                "i=" + i +
                '}';
    }
}
