package Lesson_5.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 06.12.16.
 */
public class NonexistentCardNumberException extends CardException {

    public void getNonexistentCardNumberMsg() {
        System.out.println("Карты с таким номером у клиента не существует!");
    }

}
