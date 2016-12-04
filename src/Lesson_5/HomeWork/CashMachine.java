package Lesson_5.HomeWork;

import Lesson_5.HomeWork.Exceptions.*;

import java.util.ArrayList;
import java.util.Iterator;
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
    void inputPin(int Pin)  {
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

                    Pin = Run.getInputDgt();
                    if (isPinCorrect(Pin)) break;
                }
            } else break;
        }
    }

        //вспомогательный метод для inputPin
        private boolean isPinCorrect(int pin) {
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
    public int putCash() {
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
                return amount;
            }
        }
    }

    //finish
    void addCard(Card card) {
        if (isCardAlreadyExist(card)) {
            try {
                throw new DuplicateCardException();
            } catch (DuplicateCardException e) {
                e.getMsg();
            }
        }
        currentClient.getClientCards().add(card);
    }

        //вспомогательный метод для addCard
        private boolean isCardAlreadyExist(Card card) {
            String cardNumber = card.getNumber();

            for (Client eachClient: databaseClients) {
                for (Card allExistingCards: eachClient.getClientCards()) {
                    if (allExistingCards.getNumber().equals(cardNumber)) return true;
                }
            }
            return false;
        }

    //finish
    private int getWrongCountEnteredPin() {
        return wrongCountEnteredPin;
    }

    @Override
    public String createClient() {
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
        return fio;
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

        System.out.println("Укажите паспортные данные клиента, которого хотите удалить из системы:");

        passport = Run.getInputStr();
        Iterator<Client> client = databaseClients.iterator();

        while (client.hasNext()) {
            if ((fio = client.next().getPassportId()).equals(passport)) {
                client.remove();
                System.out.println(fio + " удалён из системы!");
            }
        }



    }

    @Override
    public void createClientCard() {

    }

    @Override
    public void deleteClientCard() {

    }

    boolean isMatches(String regex, String string) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        return m.matches();
    }
}
