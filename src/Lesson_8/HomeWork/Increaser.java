package Lesson_8.HomeWork;

import java.util.Random;
/**
 * Created by Gubanov Pavel on 25.12.16.
 */
public class Increaser extends Thread {
    private int incCount = new Random().nextInt(11);
    private Card card;

    Increaser(Card card) {
        this.card = card;
    }

    private void putMoney() {
        synchronized (card) {
            card.setMoneyBalance(card.getMoneyBalance() + incCount);
        }
    }

    private void showPutMoneyLog() {
        synchronized (card) {
            System.out.println("Увеличиваем баланс счёта на \"" + incCount +
                    "\". Текущий баланс составляет: " + card.getMoneyBalance());
        }
    }

    private void go() {
        putMoney();
        showPutMoneyLog();
    }

    @Override
    public synchronized void start() {
        go();
    }
}
