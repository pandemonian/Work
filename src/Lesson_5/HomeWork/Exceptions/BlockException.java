package Lesson_5.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 07.12.16.
 */
public class BlockException extends CardException {

    public void getCardBlockedMsg() {
        System.out.println("Вы 3 раза не правильно ввели PIN-код, ваша карта блокируется");
    }
}
