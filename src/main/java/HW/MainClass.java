package HW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class MainClass {
    public static final int CARS_COUNT = 4;
    private static Phaser phaserTracer = new Phaser(1);

    public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40)); //создание трассы

        Car[] cars = new Car[CARS_COUNT];       //создание массива участников
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), phaserTracer);
        }

        for (int i = 0; i < cars.length; i++) { //старт участников
            new Thread(cars[i]).start();
        }

        try {
            Thread.sleep(3000);
            phaserTracer.arriveAndAwaitAdvance();   //готов

            Thread.sleep(3000);
            phaserTracer.arriveAndAwaitAdvance();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

            phaserTracer.arriveAndDeregister();

            while (!phaserTracer.isTerminated());
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


class Car implements Runnable {
    private static int CARS_COUNT;
    private static AtomicInteger numberParticipant = new AtomicInteger(0);

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private Phaser phaserLocal;
    private int numberInFin;

    public void setNumberInFin(int numberInFin) {
        this.numberInFin = numberInFin;
    }
    public int getNumberInFin(){
        return numberInFin;
    }

    public Phaser getPhaserCar(){return phaserLocal;}
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, Phaser phaser) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.phaserLocal = phaser;

    }
    @Override
    public void run() {
        try {
            phaserLocal.register();
            System.out.println(this.name + " готовится");
            phaserLocal.arriveAndAwaitAdvance();

            Thread.sleep(500 + (int)(Math.random() * 800));

            System.out.println(this.name + " готов");
            phaserLocal.arriveAndAwaitAdvance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (numberParticipant.getAndIncrement() == 1)
            System.out.println("Победа за машиной : " + name);

        phaserLocal.arriveAndDeregister();

    }
}

abstract class Stage {
    protected int length;
    protected String description;
    protected Semaphore semaphore;
    public String getDescription() {
        return description;
    }
    public abstract void go(Car c);
}

class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
        this.semaphore = new Semaphore(MainClass.CARS_COUNT, true);
    }
    @Override
    public void go(Car c) {
        try {
            this.semaphore.acquire();
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            this.semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = new Semaphore(MainClass.CARS_COUNT / 2, true);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                c.getPhaserCar().arriveAndAwaitAdvance();
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);

                c.getPhaserCar().arriveAndAwaitAdvance();
                this.semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                this.semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}