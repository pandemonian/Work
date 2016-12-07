package Lesson_5.HomeWork.Exceptions.backup;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class BlockAccountException extends Exception {

    public void getCardBlockedMsg() {
        System.out.println("Вы 3 раза не правильно ввели PIN-код, ваша карта блокируется");
    }
}
