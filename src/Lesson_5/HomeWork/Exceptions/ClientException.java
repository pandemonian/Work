package Lesson_5.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 07.12.16.
 */
public class ClientException extends Exception {

    public void getDuplicateClientMsg() {
        System.out.println("Ошибка! Такой клиент уже зарегистрирован в системе!");
    }

    public void getEmptyDataBaseMsg() {
        System.out.println("В системе нет ни одного клиента!");
    }

    public void getNonexistentPassportMsg() {
        System.out.println("В системе нет ни одного пользователя с такими паспортными данными!");
    }

    public void getLastClientMsg() {
        System.out.println("Это последний пользователь в системе, создайте нового прежде, чем удалить текущего.");
    }}
