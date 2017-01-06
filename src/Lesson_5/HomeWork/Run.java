package Lesson_5.HomeWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

/**
 * Created by Gubanov Pavel on 28.11.16.
 */
public class Run {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String inputStr;

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

    private static void showMenu() {

        System.out.println("");
        System.out.println("Выберите операцию:");
        System.out.println("1 - Проверить состояние счёта");
        System.out.println("2 - Снять наличные");
        System.out.println("3 - Внести наличные");
        System.out.println("4 - Завести нового клиента");
        System.out.println("5 - Удалить существующего клиента");
        System.out.println("6 - Создать карту для клиента");
        System.out.println("7 - Удалить карту");
        System.out.println("8 - Информация о текущем клиенте и его карте");
        System.out.println("9 - Выйти");
        System.out.println("");
    }

    private static void showHelloInfo() {
        System.out.println("Добро пожаловать, Вас приветствует ПАО \"Объебанк\"");
        System.out.println("Объединённый Единый Банк");
        System.out.println("");
        System.out.println("Введите PIN-код:");
    }

    private static void showInputCardInfo() {
        System.out.println("Вставьте пожалуйста карту(укажите её номер):");
        System.out.println("Нажмите\"Enter\" для использования карты по-умолчанию при первоначальной" +
                " загрузке банкомата");
        System.out.println("Либо нажмите\"exit\" для выхода");
        System.out.println("");
    }

    public static void main(String[] args) throws Exception {

        Client client1 = new Client("Ivan Ivanov", "1234 456789");
        Card clientCard1 = new Card("0123456789012345", "Ivan Ivanov", "10/19", "5502");
        client1.addClientCards(clientCard1);
        CashMachine bankomat = new CashMachine(client1);

        bankomat.workWith(client1);
        bankomat.feedCard(clientCard1);

        showHelloInfo();
        bankomat.inputPin();


        while (true) {

            showInputCardInfo();
            inputStr = getInputStr();

            if (inputStr.equals("exit"))  break;
            if (inputStr.length() == 16)  bankomat.feedCard(bankomat.chooseCard(inputStr));

            while (true) {

                showMenu();
                inputStr = getInputStr();
                System.out.println("");

                if (inputStr.equals("1")) bankomat.getMoneyBalance();
                if (inputStr.equals("2")) bankomat.getCash();
                if (inputStr.equals("3")) bankomat.putCash();
                if (inputStr.equals("4")) bankomat.createClient();
                if (inputStr.equals("5")) bankomat.deleteClient();
                if (inputStr.equals("6")) bankomat.createCard();
                if (inputStr.equals("7")) bankomat.deleteCard();
                if (inputStr.equals("8")) bankomat.helpInfo();
                if (inputStr.equals("9")) break;
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream("Serialize.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(bankomat);
        objectOutputStream.flush();
        objectOutputStream.close();
    }
}