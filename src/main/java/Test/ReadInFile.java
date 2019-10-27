package Test;

import java.io.*;

public class ReadInFile {
    public static void main(String[] args) {

        int fileWeight = 50;
        File file = new File("text.txt");
        if (file.exists()) file.delete();
        String string = null;
        try (
                FileWriter fos = new FileWriter(file, true);
        ){
            while (file.length() < fileWeight) {
                string = "";
                for (int i = 0; i < 10; i++) {
                    string += i;
                }

                fos.write(string);
                fos.flush();
            }
            fos.write("end");


        }catch (IOException e){
            e.printStackTrace();
        }
        }
    }
