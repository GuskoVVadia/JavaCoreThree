package TaskFour;

public class StartTask {
    public static void main(String[] args) {

        ClassSerializable classSerializable = new ClassSerializable(1);
        new ServerClass(PropertiesConnection.PORT).start();
        new ClientClass(PropertiesConnection.ADDRESS, PropertiesConnection.PORT, classSerializable).start();

    }
}
