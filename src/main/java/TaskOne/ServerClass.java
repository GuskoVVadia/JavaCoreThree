package TaskOne;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass extends Thread{
    {
        System.out.println("run server. ");
    }

    Socket socket = null;
    ServerSocket serverSocket = null;
    String requestLink;
    String[] array;
    private int port;
    private static Logger logger = LogManager.getLogger("ServLogs");

    public ServerClass(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try{
            serverSocket = new ServerSocket(port);

            logger.info("Сервер запущен. Ожидение подключения.");
            socket = serverSocket.accept();

            logger.info("Клиент подключён.");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


            while (true) {
                requestLink = dis.readUTF();
                if (requestLink.startsWith("/")){
                    array = requestLink.split(" ", 2);
                    if (array[0].equals("/bye")) {
                        dos.writeUTF("/disconnect");
                        logger.info("server closing");
                        break;
                    }
                }
                else {
                    dos.writeUTF("ECHO: " + requestLink);
                    logger.info("client say: " + requestLink);
                }
            }


        } catch (IOException e) {
            logger.info(e);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                logger.info(e);
            }
            try {
                socket.close();
            } catch (IOException e) {
                logger.info(e);
            }

        }
    }
}
