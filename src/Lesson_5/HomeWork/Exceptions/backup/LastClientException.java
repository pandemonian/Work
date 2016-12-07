package Lesson_5.HomeWork.Exceptions.backup;

/**
 * Created by Gubanov Pavel on 07.12.16.
 */
public class LastClientException extends ClientException {

    public void getLastClientMsg() {
        System.out.println("Это последний пользователь в системе, создайте нового прежде, чем удалить текущего.");
    }
}
