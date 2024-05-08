package ts.volta.it.produttoreconsumatore;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import ts.volta.it.produttoreconsumatore.bean.Prodotto;
import ts.volta.it.produttoreconsumatore.business.Produttore;
import ts.volta.it.produttoreconsumatore.business.Consumatore;

public class Main {

    public static void main(String[] args) {

        LinkedList<Prodotto> queue = new LinkedList<>();
        Semaphore s = new Semaphore(1);

        new Produttore("gianni", s, queue).start();
        new Produttore("pinotto", s, queue).start();
        new Produttore("federico", s, queue).start();
        new Produttore("dante", s, queue).start();
        new Consumatore("andrea", s, queue).start();
        new Consumatore("andrea zolli", s, queue).start();
        new Consumatore("pino scotto", s, queue).start();
        new Consumatore("sergio", s, queue).start();

    }
}