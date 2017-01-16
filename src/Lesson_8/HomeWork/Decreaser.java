package Lesson_8.HomeWork;

import java.util.Random;

/**
 * Created by Gubanov Pavel on 25.12.16.
 */
class Decreaser extends Thread {
    private int decCount = new Random().nextInt(11);
    private final Card card;

    Decreaser(Card card) {
        this.card = card;
    }

    private boolean getMoney() {
        synchronized (card) {
            if (card.getMoneyBalance() >= decCount) {
                card.setMoneyBalance(card.getMoneyBalance() - decCount);
                return true;
            } else {
                return false;
            }
        }
    }

    private void showGetMoneyLog() {
        synchronized (card) {
            System.out.println("Уменьшаем баланс счёта на \"" + decCount +
                    "\". Текущий баланс составляет: " + card.getMoneyBalance());
        }
    }

    private void showErrorGetMoneyLog() {
        synchronized (card) {
            System.out.println("Сумма для снятия равная \"" + decCount +
                    "\" больше текущего баланса -> итерация пропускается");
        }
    }

    private void go() {
        if (getMoney()) {
            showGetMoneyLog();
        } else {
            showErrorGetMoneyLog();
        }
    }

    @Override
    public void run() {
        go();
    }
}
