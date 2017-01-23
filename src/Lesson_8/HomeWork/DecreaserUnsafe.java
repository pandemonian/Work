package Lesson_8.HomeWork;

import java.util.Random;

/**
 * Created by Gubanov Pavel on 19.01.17.
 */
class DecreaserUnsafe extends Thread implements DeacreaserInterface {
    private int decCount = new Random().nextInt(11);
    private final Card card;

    DecreaserUnsafe(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public boolean getMoney() {
        if (card.getMoneyBalance() >= decCount) {
            card.setMoneyBalance(card.getMoneyBalance() - decCount);
            return true;
        } else {
            return false;
        }
    }

    public void showGetMoneyLog() {
        System.out.println("Уменьшаем баланс счёта на \"" + decCount +
                    "\". Текущий баланс составляет: " + card.getMoneyBalance());
    }

    public void showErrorGetMoneyLog() {
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
        //go();

        System.out.println("unsafe");
    }
}
