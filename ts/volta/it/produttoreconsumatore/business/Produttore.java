package ts.volta.it.produttoreconsumatore.business;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import ts.volta.it.chatgpt.business.NominatoreDiProdotti;
import ts.volta.it.produttoreconsumatore.bean.Prodotto;

public class Produttore extends Thread {
    
    private static int SLEEP_TIME = 550;

    private static int MAX_QUEUE_SIZE = 10;
    private static int MAX_PRODUCTS = 30;

    private String name;
    private Semaphore mutex;
    private LinkedList<Prodotto> queue;
    private NominatoreDiProdotti nominatore;
    private int nproducts = 0;

    public Produttore(String name, Semaphore mutex, LinkedList<Prodotto> queue) {
        this.name = name;
        this.mutex = mutex;
        this.queue = queue;
        this.nominatore = new NominatoreDiProdotti();
    }

    public void run() {
        boolean exit = false;
        while(!exit) {

            try {
                
                Prodotto p = new Prodotto(nominatore.generaNome());
                System.out.printf("%s ha generato il prodotto '%s'\n", name, p.toString());

                mutex.acquire();
                if(this.queue.size() < MAX_QUEUE_SIZE) {
                    queue.add(p);
                    nproducts++;
                }
                mutex.release();
                exit = nproducts > MAX_PRODUCTS;
                if(exit) {
                    System.out.printf("%s ha esaurito la produzione\n", name);
                }
                Thread.sleep(SLEEP_TIME);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

    }


}
