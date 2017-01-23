package Lesson_8.HomeWork;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Класс используемый для последовательного вывода пополнения баланса
 * несмотря на приоритет нити инкремента.
 */

class SequentialIncreaser implements Runnable {
    private Synchro synchro;
    private int incCount;
    private ReentrantLock lock;
    private Condition condition;
    private Card card;

    SequentialIncreaser(Synchro synchro, Card card) {
        this.synchro = synchro;
        this.lock = synchro.getLock();
        this.condition = synchro.getCondition();
        this.card = card;
    }

    private void putMoney() {
        incCount =  new Random().nextInt(11);
        card.setMoneyBalance(card.getMoneyBalance() + incCount);
    }

    private void showPutMoneyLog() {
        System.out.println("Увеличиваем баланс счёта на \"" + incCount +
                "\". Текущий баланс составляет: " + card.getMoneyBalance());
    }

    private void go() {
        try {
            lock.lock();
            if (!synchro.isIncrementThreadCurrent()) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            putMoney();
            showPutMoneyLog();
        } finally {
            synchro.setIncrementThreadCurrent(false);
            condition.signal();
            lock.unlock();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            go();
        }
    }
}
