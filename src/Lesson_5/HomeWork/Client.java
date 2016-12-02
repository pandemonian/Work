package Lesson_5.HomeWork;

import Lesson_5.HomeWork.Exceptions.DuplicateCardException;

import java.util.ArrayList;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class Client {

    private String nameFirstLast;
    private String passportId;
    ArrayList<Card> clientCards;

    Client(String nameFirstLast, String passportId) {
        clientCards = new ArrayList<>();
        this.nameFirstLast = nameFirstLast;
        this.passportId = passportId;
    }

    void addCard(Card card) {
        for (int i = 0; i < clientCards.size(); i++) {
            if (clientCards.get(i).getNumber().equals(card.getNumber())) {
                try {
                    throw new DuplicateCardException();
                } catch (DuplicateCardException e) {
                    e.getMsg();
                }
            }
        }

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
