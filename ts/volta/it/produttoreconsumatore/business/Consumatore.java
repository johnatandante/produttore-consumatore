package ts.volta.it.produttoreconsumatore.business;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import ts.volta.it.produttoreconsumatore.bean.Prodotto;

public class Consumatore extends Thread {
   
    private static int SLEEP_TIME = 350;

    private String name;
    private Semaphore mutex;
    private LinkedList<Prodotto> queue;
    
    public Consumatore(String name, Semaphore mutex, LinkedList<Prodotto> queue) {
        this.name = name;
        this.mutex = mutex;
        this.queue = queue;
    }

    public void run() {
        boolean exit = false;
        while(!exit) {
            try {
                Prodotto p = null;
                mutex.acquire();
                if(!queue.isEmpty()) {
                    p = queue.pop();
                    System.out.printf("%s ha consumato il prodotto '%s'\n", name, p.toString());
                }
                mutex.release();
                if(p == null) {
                    System.out.printf("%s ha trovato la coda vuota, quindi dorme di più'\n", name);
                    Thread.sleep(SLEEP_TIME * 10);
                }
                else Thread.sleep(SLEEP_TIME);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }

    }

}
