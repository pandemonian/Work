package Lesson_5.HomeWork;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public interface Terminal {

    int getMoneyBalance();
    int getCash();
    int putCash();
    String createClient();
    void deleteClient();
    void createClientCard();
    void deleteClientCard();
}
