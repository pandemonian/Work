package Lesson_5.HomeWork;

import Lesson_5.HomeWork.Exceptions.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class CashMachine implements Terminal {
    static String regexpPassport = "[0-9]{4}\\s[0-9]{6}";
    static String regexpFio = "[а-яА-Я]{2,}\\s[а-яА-Я]{2,}\\s[а-яА-Я]{2,}";
    static String regexpPin = "[0-9]{4}";
    static String regexpValid = "((01)|(02)|(03)|(04)|(05)|(06)|(07)|(08)|(09)|(10)|(11)|(12))" +
            "(\\/)((16)|(17)|(18)|(19)|(20))";

    private Client currentClient;
    private Card currentCard;
    private int wrongCountEnteredPin;
    private ArrayList<Client> databaseClients;

    CashMachine(Client client) {
        databaseClients = new ArrayList<>();
        databaseClients.add(client);
    }


    //finish
    void workWith(Client client) {
        currentClient = client;
    }

    //finish
    void feedCard(Card card) {
        currentCard = card;
    }

    //finish
    void inputPin(String Pin)  {
        while (true) {
            if (!isPinCorrect(Pin)) {
                wrongCountEnteredPin++;
                try {
                    throw new WrongPinException();
                } catch (WrongPinException e) {

                    if (wrongCountEnteredPin == 3) {
                        System.out.println("Вы 3 раза не правильно ввели PIN-код, ваша карта блокируется" +
                                " на 3 секунды!");
                        //блокировка
                        wrongCountEnteredPin = 0;
                    }

                    if (wrongCountEnteredPin > 0) {
                        System.out.println("Вы " + getWrongCountEnteredPin()
                                + "-й раз ввели неправильный PIN-код");
                    }

                    System.out.println("Введите PIN-код ещё раз");

                    Pin = Run.getInputStr();
                    if (isPinCorrect(Pin)) break;
                }
            } else break;
        }
    }

    //вспомогательный метод для inputPin
    private boolean isPinCorrect(String pin) {
        return currentCard.isPinCorrect(pin);
    }

    //finish
    public int getMoneyBalance() {
        return currentCard.getMoneyBalance();
    }

    //finish
    @Override
    public int getCash() {
        int amount;

        while (true) {
            System.out.println("Укажите сумму для снятия:");
            amount = Run.getInputDgt();

            if (amount > getMoneyBalance()) {
                try {
                    throw new LowMoneyBalanceException();
                } catch (LowMoneyBalanceException e) {
                    e.getMsg();
                }
                continue;
            }
            if ((amount % 100) != 0) {
                try {
                    throw new MoneyRatioException();
                } catch (MoneyRatioException e) {
                    System.out.println("Сумма не кратна 100");
                    continue;
                }
            }
            if (amount > 30000) {
                try {
                    throw new MoneyRatioException();
                } catch (MoneyRatioException e) {
                    System.out.println("Нельзя снять более 30000 рублей за раз");
                }
            } else {
                currentCard.setMoneyBalance(currentCard.getMoneyBalance() - amount);
                return amount;
            }
        }
    }

    //finish
    @Override
    public void putCash() {
        int amount;

        while (true) {
            System.out.println("Укажите сумму к зачислению");
            if (((amount = Run.getInputDgt()) % 100) != 0) {
                try {
                    throw new MoneyRatioException();
                } catch (MoneyRatioException e) {
                    System.out.println("Сумма не кратна 100");
                    continue;
                }
            }
            if (amount > 50000) {
                try {
                    throw new MoneyRatioException();
                } catch (MoneyRatioException e) {
                    System.out.println("Нельзя положить более 40000 рублей за раз");
                }
            } else {
                currentCard.setMoneyBalance(currentCard.getMoneyBalance() + amount);
            }
        }
    }

    @Override
    public void createClient() {
        String fio;
        String passport;

        System.out.println("Укажите паспортные данные");

        while (true) {
            passport = Run.getInputStr();
            if (isMatches(regexpPassport, passport)) {
                if (isClientAlreadyExist(passport)) {
                    try {
                        throw new DuplicateClientException();
                    } catch (DuplicateClientException e) {
                        e.getMsg();
                        continue;
                    }
                }
                break;
            }
            System.out.println("Номер(6 цифр) и серия(4 цифры) паспорта указываются раздельно!");
        }

        System.out.println("Укажите фамилию имя отчество");

        while (true) {
            fio = Run.getInputStr();
            if (isMatches(regexpFio, fio)) {
                fio = fio.trim();
                fio = fio.toUpperCase();
                break;
            }
            System.out.println("Укажите фамилию имя отчество в правильной форме!");
        }

        databaseClients.add(new Client(fio, passport));
    }

    //вспомогательный метод для createClient
    private boolean isClientAlreadyExist(String passport) {
        for (Client client: databaseClients) {
            if (client.getPassportId().equals(passport))  return true;
        }
        return false;
    }

    @Override
    public void deleteClient() {
        String passport;
        String fio;

        if (databaseClients.size() == 0) {
            try {
                throw new NonexistentException();
            } catch (NonexistentException e) {
                e.getNullDataBaseMsg();
                return;
            }
        }

        System.out.println("Укажите паспортные данные клиента, которого хотите удалить из системы:");

        while (true) {
            passport = Run.getInputStr();
            if (!isMatches(regexpPassport, passport)) {
                System.out.println("Номер(6 цифр) и серия(4 цифры) паспорта указываются раздельно!");
                continue;
            }
            break;
        }

        for (int i = 0; i < databaseClients.size(); i++) {
            if (databaseClients.get(i).getPassportId().equals(passport)) {
                System.out.println(databaseClients.get(i).getNameFirstLast() + " удалён из системы!");
                databaseClients.remove(i);
                return;
            }
        }

        try {
            throw new NonexistentException();
        } catch (NonexistentException e) {
            e.getNonexistentMsg();
        }
    }

    //finish
    public void createCard() {
        String passport;

        System.out.println("Укажите паспортные данные клиента, для которого хотите завести карту:");

        passport = inputPassportData();

        /*while (true) {
            passport = Run.getInputStr();
            if (!isMatches(regexpPassport, passport)) {
                System.out.println("Номер(6 цифр) и серия(4 цифры) паспорта указываются раздельно!");
                continue;
            }
            break;
        }*/

        for (Client allClients: databaseClients) {
            if (allClients.getPassportId().equals(passport)) {

                currentClient = allClients;
                String number;
                String holder;
                String valid;
                String pin;

                while (true) {
                    number = generatorCardNumber();
                    if (!isCardNumberExist(number))  break;
                }

                holder = currentClient.getNameFirstLast();

                while (true) {
                    valid = Run.getInputStr();
                    if (isMatches(regexpValid, valid))  break;
                }

                while (true) {
                    pin = Run.getInputStr();
                    if (isMatches(regexpPin, pin))  break;
                }

                currentClient.addClientCards(new Card(number, holder, valid, pin));
            }
        }

        try {
            throw new NonexistentException();
        } catch (NonexistentException e) {
            e.getNonexistentMsg();
        }










        /*if (isCardAlreadyExist(card)) {
            try {
                throw new DuplicateCardException();
            } catch (DuplicateCardException e) {
                e.getMsg();
            }
        }
        currentClient.getClientCards().add(card);*/
    }

    //вспомогательный метод для createCard
    private boolean isCardAlreadyExist(Card card) {
        String cardNumber = card.getNumber();

        for (Client eachClient: databaseClients) {
            for (Card allExistingCards: eachClient.getClientCards()) {
                if (allExistingCards.getNumber().equals(cardNumber)) return true;
            }
        }
        return false;
    }

    @Override
    public void deleteCard() {

    }

    //finish
    private int getWrongCountEnteredPin() {
        return wrongCountEnteredPin;
    }

    private boolean isMatches(String regex, String string) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        return m.matches();
    }

    String generatorCardNumber() {
        Random random = new Random();
        String number = "";
        for (int i = 0; i < 16; i++) {
            number += random.nextInt(10);
        }
        return number;
    }

    boolean isCardNumberExist(String number) {
        for (Client allClients: databaseClients) {
            for (Card kitClientCards: allClients.getClientCards()) {
                if (kitClientCards.getNumber().equals(number))  return true;
            }
        }
        return false;
    }

    private String inputPassportData() {
        String passport;
        while (true) {
            passport = Run.getInputStr();
            if (!isMatches(regexpPassport, passport)) {
                System.out.println("Номер(6 цифр) и серия(4 цифры) паспорта указываются раздельно!");
                continue;
            }
            return passport;
        }
    }

    //testing
    boolean searchClientViaPassport(String passport, Client client) {
        for (Client allClients: databaseClients) {
            if (allClients.getPassportId().equals(passport)) {
                client = allClients;
                return true;
            }
        }
        return false;
    }




}
