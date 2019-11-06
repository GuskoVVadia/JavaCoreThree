package TaskOne;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientClass extends Thread{
    {
        System.out.println("run client.");
    }

    AtomicBoolean stateServ = new AtomicBoolean(true);
    Socket socket = null;
    String requestLink;
    private String address;
    private int port;
    Scanner scanner;

    public ClientClass(String addressIP, int port) {
        this.address = addressIP;
        this.port = port;
    }

    @Override
    public void run() {
        try{
            socket = new Socket(address, port);
            System.out.println("Клиент запущен.");

            System.out.println("Формирую потоки.");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (stateServ.get()) {
                        try {
                            String incommingMassage = dis.readUTF();
                            if (incommingMassage.equals("/disconnect")) {
                                stateServ.getAndSet(false);
                                break;
                            }
                            System.out.println(incommingMassage);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            while (stateServ.get()) {

                requestLink = scanner.nextLine();
                dos.writeUTF(requestLink);

            }


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
