package Lesson_5.HomeWork.Exceptions;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class WrongPinException extends Exception {

    private int pin;
    public WrongPinException(String message, int pin) {
        super(message);
        this.pin = pin;
    }
}
