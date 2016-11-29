package Lesson_5.HomeWork;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class Card {
    private double moneyBalance;
    private String number;
    private String holder;
    private String valid;
    private int pin;


    public Card(String number, String holder, String valid, int pin) {
        this.number = number;
        this.holder = holder;
        this.valid = valid;
        this.pin = pin;
    }

    public double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(double moneyBalance) {
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

    public boolean isPinEqualsInputPin(int inputPin) {
        return inputPin == pin;
    }
}
