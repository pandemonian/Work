package Lesson_5.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 05.12.16.
 */
public class NonexistentException extends ClientException {

    public void getNullDataBaseMsg() {
        System.out.println("В системе нет ни одного клиента!");
    }

    public void getNonexistentPassportMsg() {
        System.out.println("В системе нет ни одного пользователя с такими паспортными данными!");
    }
}
