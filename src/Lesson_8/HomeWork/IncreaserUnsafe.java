package Lesson_8.HomeWork;

import java.util.Random;

/**
 * Created by Gubanov Pavel on 19.01.17.
 */
class IncreaserUnsafe extends Thread {
    private int incCount = new Random().nextInt(11);
    private final Card card;

    IncreaserUnsafe(Card card) {
        this.card = card;
    }

    private void putMoney() {
            card.setMoneyBalance(card.getMoneyBalance() + incCount);
    }

    private void showPutMoneyLog() {
            System.out.println("Увеличиваем баланс счёта на \"" + incCount +
                    "\". Текущий баланс составляет: " + card.getMoneyBalance());
    }

    private void go() {
        putMoney();
        showPutMoneyLog();
    }

    @Override
    public void run() {
        go();

    }
}
