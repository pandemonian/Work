package Lesson_6.HomeWork;

/**
 * Created by Gubanov Pavel on 14.12.16.
 */
class PlayerNotFoundException extends Exception {

    void getNoNicknamePlayerMsg() {
        System.out.println("Игрока с таким именем не существует");
    }
}
