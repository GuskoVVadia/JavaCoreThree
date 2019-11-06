package TaskThree;

public class EnumarationArray {

    public boolean checkFourAndOne(int[] array){
        for (int a: array){
            if ((a == 1) || (a == 4)) return true;
        }
        return false;
    }
}
