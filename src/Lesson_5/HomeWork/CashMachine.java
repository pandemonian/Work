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

    public double checkMoneyBalance() {
        return currentCard.getMoneyBalance();
        
    }

    public void inputPin(int Pin) throws WrongPinException {

        while (true) {

            if (this.isPinCorrect(Pin)) break;
            else {
                if (this.getWrongCountEnteredPin() != 3) {

                    throw new WrongPinException(this);

                }
                if (this.getWrongCountEnteredPin() == 3) {
                    System.out.println("Блокировка на 3 секунды!!!");
                }
            }
        }
    }

    public boolean isPinCorrect(int pin) {
        if (currentCard.isPinEqualsInputPin(pin))  return true;
        else {
            wrongCountEnteredPin++;
            return false;
        }
    }

    public boolean isCardBlocked() {
        return wrongCountEnteredPin >= 3;
    }

    public int getWrongCountEnteredPin() {
        return wrongCountEnteredPin;
    }

    public void incrementWrongCountEnteredPin() {
        wrongCountEnteredPin++;
    }

    /*@Override
    public void getCash(double amount) {
        currentCard.isPinEqualsInputPin();
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
