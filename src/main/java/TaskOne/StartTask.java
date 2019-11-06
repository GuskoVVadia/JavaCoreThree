package TaskOne;

public class StartTask {

    static final String ADDRESS = "localhost";
    static final int PORT = 8189;

    public static void main(String[] args) {

        new ServerClass(PORT).start();
        new ClientClass(ADDRESS, PORT).start();

    }
}
