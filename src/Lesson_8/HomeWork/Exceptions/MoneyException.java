package Lesson_8.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 07.12.16.
 */
public class MoneyException extends CardException {

    public void getLowMoneyBalanceMsg() {
        System.out.println("Недостаточно средств на счёте!");
    }
}
