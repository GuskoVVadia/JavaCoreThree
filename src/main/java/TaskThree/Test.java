package TaskThree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    char[] charsChapters;
    File file;

    public Test(int chapter, String name){
        this.charsChapters = new char[chapter];
        file = new File(name);
    }
    void readFile(){
        int a;
        try(
        FileReader fis = new FileReader(file)
        ){
            while ((a = fis.read(charsChapters)) != -1){
                System.out.println(charsChapters);
            }
        }catch (IOException e){
        }
    }
}
