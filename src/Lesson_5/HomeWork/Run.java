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
    private static String outFio;

    static String getInputStr() {
        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
            return "";
        }
    }

    static int getInputDgt() {
        while (true) {
            try {
                return Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено не число!");
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода!");
            }
        }
    }

    public static void main(String[] args) throws Exception  {

        Client client1 = new Client("Ivan Ivanov", "1234 456789");
        Card clientCard1 = new Card("1598865432784500", "Ivan Ivanov", "10/19", 5502);
        Card clientCard2 = new Card("4578976278476460", "Ivan Ivanov", "09/19", 5881);
        CashMachine bankomat = new CashMachine(client1);

        bankomat.workWith(client1);
        bankomat.feedCard(clientCard1);

        System.out.println("Введите PIN код:");
        bankomat.inputPin(getInputDgt());

        System.out.println("Выберите операцию:");
        System.out.println("1 - Проверить состояние счёта");
        System.out.println("2 - Снять наличные");
        System.out.println("3 - Внести наличные");
        System.out.println("4 - Завести нового клиента");
        System.out.println("5 - Удалить существующего клиента");
        System.out.println("6 - Создать карту для клиента");
        System.out.println("7 - Удалить карту");
        System.out.println("8 - Выйти");

        while (true) {
            inputStr = getInputStr();

            if (inputStr.equals("1")) {
                System.out.println("Баланс по вашей карте составляет: " + bankomat.getMoneyBalance() + " руб.");
            }
            if (inputStr.equals("2")) {
                System.out.println("С вашей карты обналичено " + bankomat.getCash() + " рублей");
            }
            if (inputStr.equals("3")) {
                System.out.println("На вашу карты зачислено " + bankomat.putCash() + " рублей");
            }
            /*if (inputStr.equals("4")) {
                System.out.println("В системе создан новый клиент: " + bankomat.getCash() + " рублей");*/
            }


            if (inputStr.equals("8")) System.exit(0);

        }




    }
}
