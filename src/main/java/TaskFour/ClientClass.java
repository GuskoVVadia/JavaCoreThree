package TaskFour;

import java.io.*;
import java.net.Socket;

public class ClientClass extends Thread{
    {
        System.out.println("run client.");
    }

        private ClassSerializable classOut;
        Socket socket = null;
        String requestLink;
        private String address;
        private int port;

        public ClientClass(String addressIP, int port, ClassSerializable classOut) {
            this.address = addressIP;
            this.port = port;
            this.classOut = classOut;
        }

    @Override
    public void run() {
        try{
            socket = new Socket(address, port);
            System.out.println("Клиент запущен.");

            System.out.println("Формирую потоки.");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF("/object " + classOut.getClass());
            ObjectOutputStream oos = new ObjectOutputStream(dos);
            oos.writeObject(classOut);

            requestLink = dis.readUTF();
            if (requestLink.equals("/input")){
                System.out.println("Сервер принял: " + classOut.getClass());
            }

            System.out.println("Отправка команды на отключение");
            dos.writeUTF("/end");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
