package TaskTwo;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MFU {
    private boolean stateMFU;
    private ConcurrentLinkedQueue<Worker> listScan;
    private ConcurrentLinkedQueue<Worker> listPrint;

    MFU(){
        listScan = new ConcurrentLinkedQueue<>();
        listPrint = new ConcurrentLinkedQueue<>();
    }
    synchronized void launch(){
        stateMFU = true;
        System.out.println("МФУ запускается.");
        launchPrintingMFU();
        launchScanningMFU();
    }
    private synchronized boolean isLaunchMFU(){
        return stateMFU;
    }
    synchronized void stop(){
        stateMFU = false;
        System.out.println("МФУ останавливается. Готовлюсь к выключению.");
        synchronized (listPrint){
            listPrint.notifyAll();
        }
        synchronized (listScan) {
            listScan.notify();
        }
    }
    private synchronized void launchPrintingMFU(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Worker noNameManager;

                System.out.println("Прогрев печатного устройства.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (isLaunchMFU()) {
                    noNameManager = listPrint.poll();

                    if (noNameManager == null) {
                        try {
                            synchronized (listPrint) {
                                listPrint.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {

                        for (int i = 1; i < noNameManager.getChapters(); i++) {

                            System.out.println("Печатаю " + i + " страницу");
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (!isLaunchMFU()) break;
                        }
                        System.out.println(noNameManager.getChapters() + " страниц распечатано.");
                        if (!isLaunchMFU()) break;
                    }
                }
            }
        }).start();
    }

    private synchronized void launchScanningMFU(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Worker noNameManager;

                System.out.println("Запуск контролера.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("К сканированию готов");

                while (isLaunchMFU()){
                    noNameManager = listScan.poll();

                    if (noNameManager == null){
                        try {
                            synchronized (listScan) {
                                listScan.wait();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {

                        for (int i = 1; i < noNameManager.getChapters(); i++) {
                            System.out.println("Сканирую " + i + " страницу");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (!isLaunchMFU()) break;
                        }
                        System.out.println(noNameManager.getChapters() + " страниц отсканировано.");
                        if (!isLaunchMFU()) break;
                    }
                }
            }
        }).start();
    }

    synchronized void setQueue(Worker ...workers){
        for (Worker o: workers){
            if(o.needMFU().equals("печать")){
                synchronized (listPrint) {
                    listPrint.offer(o);
                    listPrint.notify();
                }
            }
            if(o.needMFU().equals("скан")){
                synchronized (listScan) {
                    listScan.offer(o);
                    listScan.notify();
                }
            }
        }
    }
}
