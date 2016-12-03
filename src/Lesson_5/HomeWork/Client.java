package Lesson_5.HomeWork;

import Lesson_5.HomeWork.Exceptions.DuplicateCardException;

import java.util.ArrayList;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class Client {

    private String nameFirstLast;
    private String passportId;
    private ArrayList<Card> clientCards;

    public Client(String nameFirstLast, String passportId) {
        clientCards = new ArrayList<>();
        this.nameFirstLast = nameFirstLast;
        this.passportId = passportId;
    }





    void removeCard(String number) {
        if (clientCards.size() == 0) {
            System.out.println("У клиента нет ни одной карты!");
            return;
        }
        for (int i = 0; i < clientCards.size(); i++) {
            if (clientCards.get(i).getNumber().equals(number))  clientCards.remove(i);

        }

    }

    //взаимодействие с устройством/местом обслуживания
    void doInteraction(Terminal servicePlace) {

    }

}
