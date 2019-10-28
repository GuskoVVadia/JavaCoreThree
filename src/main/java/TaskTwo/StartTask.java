package TaskTwo;

public class StartTask {
    public static void main(String[] args) {

        MFU mfu = new MFU();
        mfu.launch();
        mfu.setQueue(new Worker("печать", 8),
                new Worker("скан", 10),
                new Worker("печать", 12),
                new Worker("скан", 10));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mfu.stop();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mfu.launch();

        mfu.setQueue(new Worker("печать", 2),
                new Worker("скан", 10),
                new Worker("скан", 10),
                new Worker("печать", 25));
        mfu.setQueue(new Worker("печать", 2),
                new Worker("печать", 25),
                new Worker("печать", 25),
                new Worker("печать", 25));

        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mfu.stop();
    }
}
