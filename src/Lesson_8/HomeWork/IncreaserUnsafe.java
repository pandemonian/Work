package Lesson_8.HomeWork;

import java.util.Random;

/*
 * Потоконебезопасная версия используемая в дальнейшем для демонстрации работы декоратора
 * и других примеров.
 */

class IncreaserUnsafe extends Thread implements DecoratorInterface {
    private int incCount;
    private final Card card;

    IncreaserUnsafe(Card card) {
        this.card = card;
    }

    private void putMoney() {
        incCount = new Random().nextInt(11);
        card.setMoneyBalance(card.getMoneyBalance() + incCount);
    }

    private void showPutMoneyLog() {
            System.out.println("Увеличиваем баланс счёта на \"" + incCount +
                    "\". Текущий баланс составляет: " + card.getMoneyBalance());
    }

    public void go() {
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
