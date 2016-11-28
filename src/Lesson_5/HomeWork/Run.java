package Lesson_5.HomeWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class Run {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String inputStr;
    private static double inputDgt;

    static String getInputStr() {
        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
        }
    }

    static void toDigit(String input) {
        inputDgt = Double.parseDouble(input);
    }

    public static void main(String[] args) {
        Client client = new Client();
        Card clientCard = new Card("1598865432784500", "Ivan Ivanov", "10/19", "5502");
        CashMachine cashMachine = new CashMachine();

        //вставляем карту в банкомат
        cashMachine.feedCard(clientCard);



        System.out.println("Вставьте карту:");

        //выбор карточки, получить currentCard

        System.out.println("Введите PIN код:");

        //проверка пин-кода(3 раза неверно - блокировка)

        System.out.println("Выберите операцию:");
        System.out.println("1 - Проверить состояние счёта");
        System.out.println("2 - Снять наличные");
        System.out.println("3 - Внести наличные");
        System.out.println("4 - Завести нового клиента");
        System.out.println("5 - Удалить существующего клиента");
        System.out.println("6 - Создать карту для клиента");
        System.out.println("7 - Удалить карту");
        System.out.println("8 - Выйти");

        if ((inputStr = getInputStr()).equals("1")) {
            System.out.println("Текущий баланс: " + cashMachine.checkMoneyBalance() + " рублей");
        }
        if ((inputStr = getInputStr()).equals("2")) {
            toDigit(getInputStr());        //переменной присваивается значение double
            cashMachine.getCash(inputDgt);
            System.out.println("С вашего счёта снято: " + inputDgt + "рублей");
            System.out.println("Остаток по счёту: " + cashMachine.checkMoneyBalance() + " рублей");
        }
        if ((inputStr = getInputStr()).equals("3")) {
            
        }
        if ((inputStr = getInputStr()).equals("4"));
        if ((inputStr = getInputStr()).equals("5"));
        if ((inputStr = getInputStr()).equals("6"));
        if ((inputStr = getInputStr()).equals("7"));
        if ((inputStr = getInputStr()).equals("8"))  return;


    }
}
