package TaskTwo;

import java.io.*;
import java.util.Vector;

public class FileCreate {
    private int score;
    private int sizeB;
    private String nameFile;
    private Vector<File> fileVector;


    public FileCreate(int score, int sizeB, String nameFile) {
        this.score = score;
        this.sizeB = sizeB;
        this.nameFile = nameFile;
        fileVector = new Vector<>();

    }
    void start(){
        createFiles();
        sequenceFile();
    }
    private void createFiles() {
        System.out.println("Внимание, будет создано " + score + " файла/файлов.");
        for (int i = 0; i < score; i++) {
            String newFile = nameFile + i;
            getFile(newFile);
        }
    }
    private void getFile(String newFile){
        File file = new File(newFile + ".test");

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
        fileVector.add(file);
    }

    private void sequenceFile(){
        int a;
        try {
            Vector<FileInputStream> vfis = new Vector<>();
            for (File o : fileVector) {
                vfis.add(new FileInputStream(o));
            }
            try(
            InputStream is = new SequenceInputStream(vfis.elements());
            FileOutputStream fos = new FileOutputStream("single.test");
            ) {
                while ((a = is.read()) != -1) {
                    fos.write(a);
                }
            }catch (IOException e){
                e.printStackTrace();
            }

            System.out.println("Общий файл готов.");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
