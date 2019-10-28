package TaskOne;

public class ObjectSync {
    private int flag; //1, 2, 3.

    public ObjectSync() {
        flag = 1;
    }

    synchronized void writeA(){
        while(getFlag() != 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("A");
        setFlag(2);
        notifyAll();
    }

    synchronized void writeB(){
        while(getFlag() != 2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("B");
        setFlag(3);
        notifyAll();
    }

    synchronized void writeC(){
        while(getFlag() != 3){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("C");
        setFlag(1);
        notifyAll();
    }

    private synchronized int getFlag() {
        return flag;
    }

    private synchronized void setFlag(int flag) {
        this.flag = flag;
    }
}
