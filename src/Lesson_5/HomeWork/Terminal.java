package Lesson_5.HomeWork;

import javax.smartcardio.*;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
interface Terminal {

    void getMoneyBalance();
    void getCash();
    void putCash();
    void createClient();
    void deleteClient();
    void createCard();
    void deleteCard();
    void helpInfo();
}
