package TaskOne;

public class StartTask {
    public static void main(String[] args) {

        HelpFileTask helpFileTask = new HelpFileTask(500, "test50MB.txt");
        helpFileTask.start();

    }
}
