package Lesson_5.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class DuplicateClientException extends ClientException {

    public void getMsg() {
        System.out.println("Ошибка! Такой клиент уже зарегистрирован в системе!");
    }

}
