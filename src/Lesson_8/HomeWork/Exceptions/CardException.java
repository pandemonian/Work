package Lesson_8.HomeWork.Exceptions;

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

    public void getClientLastCardMsg(){
        System.out.println("У клиента единственная банковская карта, чтобы удалить эту - создайте новую!");
    }

    public void getWrongPinMsg(int count) {
        System.out.println("Вы " + count + "-й раз ввели неправильный PIN-код");
    }


}
