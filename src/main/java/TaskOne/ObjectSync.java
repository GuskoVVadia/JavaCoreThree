package TaskOne;

public class ObjectSync {
    private int flag; //1, 2, 3.

    public ObjectSync() {
        flag = 1;
    }

    synchronized void writeA(){
        while(flag != 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("A");
        flag = 2;
        notifyAll();
    }

    synchronized void writeB(){
        while(flag != 2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("B");
        flag = 3;
        notifyAll();
    }

    synchronized void writeC(){
        while(flag != 3){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("C");
        flag = 1;
        notifyAll();
    }

}
