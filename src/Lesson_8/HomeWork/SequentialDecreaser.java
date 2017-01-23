package Lesson_8.HomeWork;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Класс используемый для последовательного вывода списания баланса
 * несмотря на приоритет нити инкремента.
 */

class SequentialDecreaser implements Runnable {
    private Synchro synchro;
    private int decCount;
    private ReentrantLock lock;
    private Condition condition;
    private Card card;

    SequentialDecreaser(Synchro synchro, Card card) {
        this.synchro = synchro;
        this.lock = synchro.getLock();
        this.condition = synchro.getCondition();
        this.card = card;
    }

    private boolean getMoney() {
        decCount =  new Random().nextInt(11);
        if (card.getMoneyBalance() >= decCount) {
            card.setMoneyBalance(card.getMoneyBalance() - decCount);
            return true;
        } else {
            return false;
        }
    }

    private void showGetMoneyLog() {
        System.out.println("Уменьшаем баланс счёта на \"" + decCount +
                "\". Текущий баланс составляет: " + card.getMoneyBalance());
    }

    private void showErrorGetMoneyLog() {
        System.out.println("Сумма для снятия равная \"" + decCount +
                "\" больше текущего баланса -> итерация пропускается");
    }

    private void go() {
        try {
            lock.lock();
            if (synchro.isIncrementThreadCurrent()) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (getMoney()) {
                showGetMoneyLog();
            } else {
                showErrorGetMoneyLog();
            }

        } finally {
            synchro.setIncrementThreadCurrent(true);
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
