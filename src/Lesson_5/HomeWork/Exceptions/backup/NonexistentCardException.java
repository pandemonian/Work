package Lesson_5.HomeWork.Exceptions.backup;

/**
 * Created by Gubanov Pavel on 06.12.16.
 */
public class NonexistentCardException extends CardException {

    public void getNonexistentCardNumberMsg() {
        System.out.println("Карты с таким номером у клиента не существует!");
    }

    public void getNoCardForClient(){
        System.out.println("У клиента нет ни одной банковской карты");
    }

}
