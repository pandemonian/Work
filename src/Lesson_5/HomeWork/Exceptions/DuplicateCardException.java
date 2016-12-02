package Lesson_5.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class DuplicateCardException extends CardException {

    public void getMsg() {
        System.out.println("Ошибка! Карта с таким номером уже у клиент существует!");
    }

}
