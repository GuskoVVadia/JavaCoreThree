package TaskOne;

public class TaskOneStart {
    public static void main(String[] args) {
        ObjectSync os = new ObjectSync();

         new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                os.writeA();
            }
        }).start();

         new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                os.writeB();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                os.writeC();
            }
        }).start();
    }
}