package Lesson_5.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class LowMoneyBalanceException extends MoneyException {

    public void getMsg() {
        System.out.println("Недостаточно средств на счёте!");
    }
}
