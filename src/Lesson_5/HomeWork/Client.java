package Lesson_5.HomeWork;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
class Client implements Serializable{

    private String nameFirstLast;
    private String passportId;
    private ArrayList<Card> clientCards;

    Client(String nameFirstLast, String passportId) {
        clientCards = new ArrayList<>();
        this.nameFirstLast = nameFirstLast;
        this.passportId = passportId;
    }

    String getPassportId() {
        return passportId;
    }

    String getNameFirstLast() {
        return nameFirstLast;
    }

    ArrayList<Card> getClientCards() {
        return clientCards;
    }

    void addClientCards(Card card) {
        clientCards.add(card);
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
