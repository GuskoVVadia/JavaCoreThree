package TaskFour;

import java.io.Serializable;
import java.util.ArrayList;

class SomeClass {
    private int id;
    private String name;

    public SomeClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SomeClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return id + name.hashCode();
    }
}
