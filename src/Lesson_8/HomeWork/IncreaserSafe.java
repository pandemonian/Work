package Lesson_8.HomeWork;

import java.util.Random;

class IncreaserSafe extends Thread {
    private int incCount;
    private final Card card;

    IncreaserSafe(Card card) {
        this.card = card;
    }

    private void putMoney() {
        synchronized (card) {
            incCount = new Random().nextInt(11);
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
    public void run() {
        for (int i = 0; i < 100; i++) {
            go();
        }
    }
}
