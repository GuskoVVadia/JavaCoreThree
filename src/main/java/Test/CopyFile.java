package Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        int a;
        byte[] b = new byte[204800];
        FileInputStream fin = null;
        FileOutputStream fout = null;
        int check = 0;

        try{
            fin = new FileInputStream("d:\\JavaFiles\\JavaBooks\\Брюс Эккель - Философия Java - 2015.pdf");
            fout = new FileOutputStream("test.pdf");
            do{
                a = fin.read(b);
                if (a != -1) fout.write(b);
                check++;
            } while (a != -1);

            System.out.println(check + " блоков.");
            System.out.println("Копирование завершено: " + (System.currentTimeMillis() - time) + " ms.");

        }catch (IOException e){
            System.out.println("Ошибка ввода-вывода");
        }finally {
            try{
                if (fin != null) fin.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            try {
                if (fout != null) fout.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}