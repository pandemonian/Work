package Lesson_5.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class DuplicateClientException extends ClientException {

    void getMsg() {
        System.out.println("Ошибка! Такой клиент уже существует!");
    }

}
