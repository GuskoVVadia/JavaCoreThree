package TaskFour;

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

        public ServerClass(int port) {
            this.port = port;
        }

        @Override
        public void run() {
            try{
                serverSocket = new ServerSocket(port);

                System.err.println("Сервер запущен. Ожидение подключения.");
                socket = serverSocket.accept();

                System.out.println("Клиент подключён. Формирую потоки ввода-вывода.");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


                while (true) {
                    requestLink = dis.readUTF();
                    if (requestLink.startsWith("/")){
                        array = requestLink.split(" ", 2);

                        if (array[0].equalsIgnoreCase("/object")){
                            System.out.println("Готов принять: " + array[1]);
                            ObjectInputStream ois = new ObjectInputStream(dis);
                            ClassSerializable classIn = (ClassSerializable) ois.readObject();
                            dos.writeUTF("/input");
                            classIn.show();
                            System.out.println(classIn);
                        }
                        if (array[0].equals("/end")){
                            System.out.println("Клиент отключается");
                            System.out.println("Сервер отключается");
                            break;
                        }
                    }
                    else System.out.println(requestLink);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }finally {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

