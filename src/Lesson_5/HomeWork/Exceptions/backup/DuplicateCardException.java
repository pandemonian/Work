package Lesson_5.HomeWork.Exceptions.backup;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class DuplicateCardException extends CardException {

    public void getDuplicateCardMsg() {
        System.out.println("Ошибка! Карта с таким номером уже существует!");
    }

}
