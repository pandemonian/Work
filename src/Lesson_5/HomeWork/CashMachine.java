package Lesson_5.HomeWork;

import Lesson_5.HomeWork.Exceptions.MoneyRatioException;
import Lesson_5.HomeWork.Exceptions.WrongPinException;

import java.util.ArrayList;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class CashMachine implements Terminal {

    private Card currentCard;
    private int wrongCountEnteredPin;
    ArrayList<Client> databaseClients

    //finish
    void feedCard(Card card) {
        currentCard = card;
    }

    //finish
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
            } else break;
        }
    }

    //finish
    private boolean isPinCorrect(int pin) {
        return currentCard.isPinCorrect(pin);
    }

    //finish
    public void checkMoneyBalance() {
        System.out.println("Ваш баланс составляет: " + currentCard.getMoneyBalance() + " руб.");
    }

    //finish
    @Override
    public void getCash() {
        int amount;

        while (true) {
            System.out.println("Укажите сумму для снятия");

            if (((amount = Run.getInputDgt()) % 100) != 0) {
                try {
                    throw new MoneyRatioException();
                } catch (MoneyRatioException e) {
                    System.out.println("Сумма не кратна 100");
                    continue;
                }
            }
            if (amount > 30000) {
                try {
                    throw new MoneyRatioException();
                } catch (MoneyRatioException e) {
                    System.out.println("Нельзя снять более 30000 рублей за раз");
                }
            } else {
                currentCard.setMoneyBalance(currentCard.getMoneyBalance() - amount);
                break;
            }
        }
    }

    //finish
    @Override
    public void putCash() {
        int amount;

        while (true) {
            System.out.println("Укажите сумму к зачислению");

            if (((amount = Run.getInputDgt()) % 100) != 0) {
                try {
                    throw new MoneyRatioException();
                } catch (MoneyRatioException e) {
                    System.out.println("Сумма не кратна 100");
                    continue;
                }
            }
            if (amount > 50000) {
                try {
                    throw new MoneyRatioException();
                } catch (MoneyRatioException e) {
                    System.out.println("Нельзя положить более 40000 рублей за раз");
                }
            } else {
                currentCard.setMoneyBalance(currentCard.getMoneyBalance() + amount);
                break;
            }
        }
    }


    public boolean isCardBlocked() {
        return wrongCountEnteredPin == 3;
    }

    //finish
    private int getWrongCountEnteredPin() {
        return wrongCountEnteredPin;
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
