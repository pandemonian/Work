package Lesson_5.HomeWork;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class Card {
    private int moneyBalance;
    private String number;
    private String holder;
    private String valid;
    private String pin;


    Card(String number, String holder, String valid, String pin) {
        this.number = number;
        this.holder = holder;
        this.valid = valid;
        this.pin = pin;
    }

    int getMoneyBalance() {
        return moneyBalance;
    }

    void setMoneyBalance(int moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public String getNumber() {
        return number;
    }

    public String getHolder() {
        return holder;
    }

    public String getValid() {
        return valid;
    }

    boolean isPinCorrect(String inputPin) {
        return inputPin.equals(pin);
    }
}
