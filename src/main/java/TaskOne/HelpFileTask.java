package TaskOne;

import java.io.*;
import java.util.Scanner;

public class HelpFileTask {
    private int sizeB;
    private File file;

    public HelpFileTask(int sizeB, String nameFile){
        this.sizeB = sizeB;
        file = new File(nameFile);
    }

    public void start(){
        if (flag()){
            createFile();
            readFile();
        }
    }

    private void createFile(){
            if (file.exists()) file.delete();
            String string = "";

            try (RandomAccessFile fos = new RandomAccessFile(file, "rw");)
            {
                for (int i = 0; i < 10; i++) {
                    string += i;
                }
                fos.seek(0);

                while (file.length() < sizeB) {
                    fos.write(string.getBytes());
                }

                fos.setLength(sizeB - 3);
                fos.write("end".getBytes());

            }catch (IOException e){
                e.printStackTrace();
            }

            System.out.println(file.getName() + " создан.");
        }

     private boolean flag(){
        try (Scanner in = new Scanner(System.in)) {
            System.err.println("Будет создан файл " + file.getName() + " в папке проекта.");
            System.err.println("Если таковой имеется - он может быть удалён.");
            System.err.println("Введите 'yes' или 'y', если подтверждаете операцию.");
            System.err.println("Введите no, если не готовы.");
            while (true) {
                String localString = in.nextLine();
                if (localString.equalsIgnoreCase("y") || localString.equalsIgnoreCase("yes")){
                    return true;
                }
                if (localString.equalsIgnoreCase("no")) return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void readFile(){
        System.out.println("Открываю для чтения файл: " + file.getName() + " методом readAllBytes.");

        try(FileInputStream fis = new FileInputStream(file)) {
            byte[] b = fis.readAllBytes();
            System.out.write(b);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
