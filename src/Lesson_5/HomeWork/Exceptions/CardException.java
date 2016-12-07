package Lesson_5.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 07.12.16.
 */
public class CardException extends Exception {

    public void getDuplicateCardMsg() {
        System.out.println("Ошибка! Карта с таким номером уже существует!");
    }

    public void getNonexistentCardNumberMsg() {
        System.out.println("Карты с таким номером у клиента не существует!");
    }

    public void getClientHasNoCardMsg(){
        System.out.println("У клиента нет ни одной банковской карты");
    }
}
