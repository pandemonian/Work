package Lesson_8.HomeWork;

import Lesson_8.HomeWork.Exceptions.BlockException;
import Lesson_8.HomeWork.Exceptions.CardException;
import Lesson_8.HomeWork.Exceptions.ClientException;
import Lesson_8.HomeWork.Exceptions.MoneyException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
class CashMachine extends Thread implements Terminal, Serializable {
    private static String regexpPassport = "[0-9]{4}\\s[0-9]{6}";

    private Client currentClient;
    private Card currentCard;
    private int wrongCountEnteredPin;
    private ArrayList<Client> databaseClients;

    CashMachine(Client client) {
        databaseClients = new ArrayList<>();
        databaseClients.add(client);
    }

    void workWith(Client client) {
        currentClient = client;
    }

    void feedCard(Card card) {
        currentCard = card;
    }

    @Override
    public void getMoneyBalance() {
        inputPin();

        System.out.println("Баланс по вашей карте составляет: " + currentCard.getMoneyBalance() + " руб.");
    }

    @Override
    public void getCash() {
        int amount;

        inputPin();

        while (true) {
            System.out.println("Укажите сумму для снятия:");
            amount = Run.getInputDgt();

            if (amount > currentCard.getMoneyBalance()) {
                try {
                    throw new MoneyException();
                } catch (MoneyException e) {
                    e.getLowMoneyBalanceMsg();
                }
                continue;
            }
            if ((amount % 100) != 0) {
                try {
                    throw new MoneyException();
                } catch (MoneyException e) {
                    System.out.println("Сумма не кратна 100");
                    continue;
                }
            }
            if (amount > 30000) {
                try {
                    throw new MoneyException();
                } catch (MoneyException e) {
                    System.out.println("Нельзя снять более 30000 рублей за раз");
                }
            } else {
                currentCard.setMoneyBalance(currentCard.getMoneyBalance() - amount);
                System.out.println("С вашей карты снято " + amount + " рублей");
                return;
            }
        }
    }

    @Override
    public void putCash() {
        int amount;

        inputPin();

        while (true) {
            System.out.println("Укажите сумму к зачислению");
            if (((amount = Run.getInputDgt()) % 100) != 0) {
                try {
                    throw new MoneyException();
                } catch (MoneyException e) {
                    System.out.println("Сумма не кратна 100");
                    continue;
                }
            }
            if (amount > 50000) {
                try {
                    throw new MoneyException();
                } catch (MoneyException e) {
                    System.out.println("Нельзя положить более 40000 рублей за раз");
                }
            } else {
                currentCard.setMoneyBalance(currentCard.getMoneyBalance() + amount);
                System.out.println("На вашу карту зачислено " + amount + " рублей");
                break;
            }
        }
    }

    @Override
    public void createClient() {
        String regexpFio = "[а-яА-Я]{2,}\\s[а-яА-Я]{2,}\\s[а-яА-Я]{2,}";
        String fio;
        String passport;

        inputPin();

        System.out.println("Укажите паспортные данные");

        while (true) {
            passport = Run.getInputStr();
            if (isMatches(regexpPassport, passport)) {
                if (isClientAlreadyExist(passport)) {
                    try {
                        throw new ClientException();
                    } catch (ClientException e) {
                        e.getDuplicateClientMsg();
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

    @Override
    public void deleteClient() {
        String passport;

        inputPin();

        if (isLastClientAtDatabase()) return;

        System.out.println("Укажите паспортные данные клиента, которого хотите удалить из системы:");

        passport = inputPassportData();

        for (int i = 0; i < databaseClients.size(); i++) {
            if (databaseClients.get(i).getPassportId().equals(passport)) {
                System.out.println(databaseClients.get(i).getNameFirstLast() + " удалён из системы!");
                databaseClients.remove(i);
                return;
            }
        }

        try {
            throw new ClientException();
        } catch (ClientException e) {
            e.getNonexistentPassportMsg();
        }
    }

    @Override
    public void createCard() {
        String regexpValid = "((01)|(02)|(03)|(04)|(05)|(06)|(07)|(08)|(09)|(10)|(11)|(12))" +
                "(\\/)((16)|(17)|(18)|(19)|(20))";
        String regexpPin = "[0-9]{4}";
        String passport;

        inputPin();

        System.out.println("Укажите паспортные данные клиента, для которого хотите завести карту:");

        passport = inputPassportData();

        for (Client allClients : databaseClients) {
            if (allClients.getPassportId().equals(passport)) {

                currentClient = allClients;
                String number;
                String holder;
                String valid;
                String pin;

                while (true) {
                    number = generatorCardNumber();
                    if (!isCardAlreadyExist(number)) break;

                    try {
                        throw new CardException();
                    } catch (CardException e) {
                        e.getDuplicateCardMsg();
                    }
                }

                holder = currentClient.getNameFirstLast();

                while (true) {
                    System.out.println("Введите срок действия карты в формате \"мм/гг\"");
                    valid = Run.getInputStr();
                    if (isMatches(regexpValid, valid)) break;
                }

                while (true) {
                    System.out.println("Введите четырёхзначный пин-код");
                    pin = Run.getInputStr();
                    if (isMatches(regexpPin, pin)) break;
                }

                currentClient.addClientCards(new Card(number, holder, valid, pin));
                System.out.println("В системе создана карта с номером: " + number);
                return;
            }
        }

        try {
            throw new ClientException();
        } catch (ClientException e) {
            e.getNonexistentPassportMsg();
        }
    }

    @Override
    public void deleteCard() {
        String passport;
        String cardNumber;

        inputPin();
        if (isLastCardAtClient())  return;

        System.out.println("Укажите паспортные данные клиента, у которого хотите удалить карту:");
        passport = inputPassportData();

        System.out.println("Укажите номер карты клиента, которую хотите удалить:");
        cardNumber = inputCardNumberData();

        //ищем клиента по указанным паспортным данным
        for (Client client : databaseClients) {
            if (client.getPassportId().equals(passport)) {

                if (isLastCardAtClient()) {
                    System.out.println("последняя карта у клиента");
                    return;
                }

                //ищем карту с указанным номером и если она сущ-ет удаляем её
                for (int i = 0; i < client.getClientCards().size(); i++) {
                    if (client.getClientCards().get(i).getNumber().equals(cardNumber)) {
                        client.getClientCards().remove(i);
                        System.out.println("Карта с номером: " + cardNumber + ", клиента: "
                                + client.getNameFirstLast() + ", удалена из системы");

                        //если удаляем текущую карту
                        if (currentCard.getNumber().equals(cardNumber)) {
                            for (Card card: currentClient.getClientCards()) {
                                currentCard = card;
                                System.out.println("Вы удалили текущую карту, работа продолжится с картой" +
                                        " из вашего набора с номером " + currentCard.getNumber());
                            }
                        }
                        return;
                    }
                }
                try {
                    throw new CardException();
                } catch (CardException e) {
                    e.getNonexistentCardNumberMsg();
                    return;
                }
            }

            try {
                throw new ClientException();
            } catch (ClientException e) {
                e.getNonexistentPassportMsg();
            }
        }
    }

    @Override
    public void helpInfo() {
        inputPin();
        System.out.println("Текущий пользователь: " + currentClient.getNameFirstLast());
        System.out.println("Номер карты: " + currentCard.getNumber());
        System.out.println("Баланс: " + currentCard.getMoneyBalance());
    }

    @Override
    public void run() {
        for (int i = 3; i > 0; i--) {
            System.out.println("Карта разблокируется через " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Карта разблокирована!");
        System.out.println("Введите PIN-код ещё раз");
    }

    private boolean isMatches(String regex, String string) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        return m.matches();
    }

    private boolean isLastClientAtDatabase() {
        int count = 0;

        for (Client client : databaseClients) {
                count++;
        }

        if (count == 1) {
            try {
                throw new ClientException();
            } catch (ClientException e) {
                e.getLastClientMsg();
                return true;
            }
        }
        return false;
    }

    private boolean isLastCardAtClient() {
        if (currentClient.getClientCards().size() == 1) {
            try {
                throw new CardException();
            } catch (CardException e) {
                e.getClientLastCardMsg();
                return true;
            }
        }
        return false;
    }

    //вспомогательный метод для createClient
    private boolean isClientAlreadyExist(String passport) {
        for (Client client : databaseClients) {
            if (client.getPassportId().equals(passport)) return true;
        }
        return false;
    }

    //вспомогательный метод для createCard
    private boolean isCardAlreadyExist(String number) {
        for (Client client : databaseClients) {
            for (Card clientCard : client.getClientCards()) {
                if (clientCard.getNumber().equals(number)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getWrongCountEnteredPin() {
        return wrongCountEnteredPin;
    }

    private String generatorCardNumber() {
        Random random = new Random();
        String number = "";
        for (int i = 0; i < 16; i++) {
            number += random.nextInt(10);
        }
        return number;
    }

    private String inputPassportData() {
        String passport;
        while (true) {
            passport = Run.getInputStr();
            if (!isMatches(regexpPassport, passport)) {
                System.out.println("Серия(4 цифры) и номер(6 цифр) паспорта указываются раздельно!");
                continue;
            }
            return passport;
        }
    }

    private String inputCardNumberData() {
        String regexpCardNumber = "[0-9]{16}";
        String cardNumber;
        while (true) {
            cardNumber = Run.getInputStr();
            cardNumber = cardNumber.replace(" ", "");
            if (!isMatches(regexpCardNumber, cardNumber)) {
                System.out.println("Укажите 16-значный номер карты!");
                continue;
            }
            return cardNumber;
        }
    }

    void inputPin() {
        String Pin;

        System.out.println("Введите PIN-код");

        while (true) {

            if (getWrongCountEnteredPin() != 0) {
                System.out.println("Введите PIN-код");
            }

            Pin = Run.getInputStr();

            if (!isPinCorrect(Pin)) {
                wrongCountEnteredPin++;
                try {
                    throw new CardException();
                } catch (CardException e1) {

                    if (wrongCountEnteredPin == 3) {

                        try {
                            throw new BlockException();
                        } catch (BlockException e2) {
                            e2.getCardBlockedMsg();
                            new CashMachine(databaseClients.get(0)).start();
                            wrongCountEnteredPin = 0;
                        }
                    }

                    if (wrongCountEnteredPin > 0) {
                        e1.getWrongPinMsg(getWrongCountEnteredPin());
                    }

                    if (isPinCorrect(Pin)) break;
                }
            } else break;
        }
    }

    private boolean isPinCorrect(String pin) {
        return currentCard.isPinCorrect(pin);
    }

    Card chooseCard(String cardNumber) {

        if (isLastClientAtDatabase()) return currentCard;

        if (isCardAlreadyExist(cardNumber)) {

            for (Client client : databaseClients) {
                for (Card clientCard : client.getClientCards()) {
                    if (clientCard.getNumber().equals(cardNumber)) {
                        return clientCard;
                    }
                }
            }
        }
        return currentCard;
    }
}