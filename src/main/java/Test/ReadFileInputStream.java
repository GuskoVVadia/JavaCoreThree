package Test;

import java.io.*;

public class ReadFileInputStream {
    public static void main(String[] args){
        long time = System.currentTimeMillis();
        int size;
        File file = new File("text.txt");

        try(FileInputStream fis = new FileInputStream(file)){
            System.out.println("Size file = " + file.length());
            System.out.println("Общее количество доступных байтов: " + (size = fis.available()));
            int n = size / 40;

            System.out.println("Читаем по очереди (read): ");
            for (int i = 0; i < n; i++) {
                System.out.println((char) fis.read());
            }
            System.out.println("Доступно: " + fis.available());

            byte[] b = new byte[n];
            System.out.println("Читаем по очереди (read[]): ");
            if (fis.read(b) != n) System.out.println("Нельзая прочитать " + n + " байтов.");
            System.out.println(new String(b, 0, n));

            System.out.println("Доступно: " + (size = fis.available()));
            System.out.println("Пропустить половину skip.");
            fis.skip(size/2);

            System.out.println("Доступно: " + fis.available());
            System.out.println("exit.");


        }catch (FileNotFoundException e){
            System.out.println("Файл не найден.");
        }catch (IOException e1){
            e1.printStackTrace();
        }


    }
}