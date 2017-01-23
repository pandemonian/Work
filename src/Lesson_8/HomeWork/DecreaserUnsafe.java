package Lesson_8.HomeWork;

import java.util.Random;

/*
 * Потоконебезопасная версия используемая в дальнейшем для демонстрации работы декоратора
 * и других примеров.
 */

class DecreaserUnsafe extends Thread implements DecoratorInterface {
    private int decCount;
    private final Card card;

    DecreaserUnsafe(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    private boolean getMoney() {
        decCount = new Random().nextInt(11);
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

    @Override
    public void go() {
        if (getMoney()) {
            showGetMoneyLog();
        } else {
            showErrorGetMoneyLog();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            go();
        }
    }
}
