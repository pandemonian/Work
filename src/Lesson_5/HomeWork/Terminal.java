package Lesson_5.HomeWork;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public interface Terminal {

    double checkMoneyBalance();
    void getCash(double amount);
    void putCash(double amount);
    void createClient();
    void deleteClient();
    void createClientCard();
    void deleteClientCard();
}
