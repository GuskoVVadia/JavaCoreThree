package Test;

import java.io.*;
import java.nio.CharBuffer;
import java.util.Scanner;

public class A {
    public static void main(String[] args){

//        String link;
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//       do{
//           link = br.readLine();
//           System.out.println(link);
//        }while(!link.equals("стоп"));

        File file = new File("text.txt");
//        String string = null;
        char[] b = new char[512];
        int a = 0;


        try(
            FileReader fis = new FileReader(file);
            OutputStreamWriter ops = new OutputStreamWriter(System.out);
        ){
            do{
                a = fis.read(b);
//                String string = new String(b);
                ops.write(b);
                if (a == -1) ops.write(b);
            }while (a != -1);



        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
