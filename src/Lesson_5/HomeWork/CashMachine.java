package Lesson_5.HomeWork;

import Lesson_5.HomeWork.Exceptions.WrongPinException;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class CashMachine implements Terminal {

    private Card currentCard;
    private int wrongCountEnteredPin;

    void feedCard(Card card) {
        currentCard = card;
    }

    void inputPin(int Pin)  {
        while (true) {
            if (!isPinCorrect(Pin)) {

                wrongCountEnteredPin++;

                try {
                    throw new WrongPinException();
                } catch (WrongPinException e) {

                    if (wrongCountEnteredPin == 3) {
                        System.out.println("Вы 3 раза не правильно ввели PIN-код, ваша карта блокируется" +
                                " на 3 секунды!");
                        //блокировка
                        wrongCountEnteredPin = 0;
                    }

                    if (wrongCountEnteredPin > 0) {
                        System.out.println("Вы " + getWrongCountEnteredPin()
                                + "-й раз ввели неправильный PIN-код");
                    }

                    System.out.println("Введите PIN-код ещё раз");

                    Pin = Run.getInputDgt();
                    if (isPinCorrect(Pin)) break;
                }
            }
        }
    }

    private boolean isPinCorrect(int pin) {
        return currentCard.isPinCorrect(pin);
    }

    public double checkMoneyBalance() {
        return currentCard.getMoneyBalance();
    }

    public boolean isCardBlocked() {
        return wrongCountEnteredPin == 3;
    }

    private int getWrongCountEnteredPin() {
        return wrongCountEnteredPin;
    }


    /*@Override
    public void getCash(double amount) {
        currentCard.isPinCorrect();
        if ((amount % 100) != 0) // ошибка
        if (currentCard.getMoneyBalance() < amount)  // throw ошибка
        currentCard.setMoneyBalance(currentCard.getMoneyBalance() - amount);
    }*/

    @Override
    public void getCash(double amount) {

    }

    @Override
    public void putCash(double amount) {
        if ((amount % 100) != 0) // ошибка
        if (amount > 50000)  // нельзя сразу много
        currentCard.setMoneyBalance(currentCard.getMoneyBalance() + amount);
    }

    @Override
    public void createClient() {

    }

    @Override
    public void deleteClient() {

    }

    @Override
    public void createClientCard() {

    }

    @Override
    public void deleteClientCard() {

    }
}
