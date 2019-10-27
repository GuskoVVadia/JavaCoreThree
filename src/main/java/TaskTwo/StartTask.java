package TaskTwo;

public class StartTask {
    public static void main(String[] args) {

        FileCreate fileCreate = new FileCreate(5, 100, "noname");
        fileCreate.start();

    }
}
