package Lesson_5.HomeWork;

import javax.smartcardio.*;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
interface Terminal {

    int getMoneyBalance();
    int getCash();
    void putCash();
    void createClient();
    void deleteClient();
    void createCard();
    void deleteCard();
}
