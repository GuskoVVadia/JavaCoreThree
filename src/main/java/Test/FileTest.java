package Test;

import java.io.File;
import java.io.FilenameFilter;

public class FileTest {
    public static void main(String[] args) {
        File file = new File("c://");

        String[] link =  file.list();

        for (int i = 0; i < link.length; i++) {
            System.out.println(link[i] + " ;");
        }
        File path = file.getAbsoluteFile();
        System.out.println(path);

        String[] linkFilter = file.list((dir, name) -> name.endsWith(".sys"));

        for (int i = 0; i < linkFilter.length; i++) {
            System.out.println(linkFilter[i] + " : " + linkFilter[i].length() + " b.");
        }
        System.out.println("exit.");
    }
}
