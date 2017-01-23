package Lesson_8.HomeWork;

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
    private static boolean isExit;

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
        System.out.println("9 - Запустить инкремент-декримент потоки. Задача № 1 (unsafe)");
        System.out.println("10 - Запустить инкремент-декримент потоки. Задача № 1 (safe)");
        System.out.println("11 - Запустить инкремент-декримент потоки. Задача № 2 (safe via ReentrantLock)");
        System.out.println("12 - Запустить инкремент-декримент потоки. Задача № 3 (safe via Decorator)");
        System.out.println("13 - Выйти\n");
    }

    private static void showHelloInfo() {
        System.out.println("Добро пожаловать, Вас приветствует ПАО \"Объебанк\"");
        System.out.println("Объединённый Единый Банк\n");
    }

    private static void showInputCardInfo() {
        System.out.println("Вставьте пожалуйста карту(укажите её номер):");
        System.out.println("Нажмите\"Enter\" для использования карты по-умолчанию при первоначальной" +
                " загрузке банкомата");
        System.out.println("Либо нажмите\"exit\" для выхода\n");
    }

    private static void startTask1Lesson8Safe(Card card) {
        for (int i = 0; i < 100; i++) {
            Increaser increaser = new Increaser(card);
            increaser.start();

            Decreaser decreaser = new Decreaser(card);
            decreaser.start();

            try {
                increaser.join();
                decreaser.join();
            } catch (InterruptedException e) {
                e.getMessage();
                e.printStackTrace();
            }
        }
    }

    private static void startTask1Lesson8Unsafe(Card card) {
        for (int i = 0; i < 100; i++) {
            IncreaserUnsafe increaser = new IncreaserUnsafe(card);
            increaser.start();

            DecreaserUnsafe decreaser = new DecreaserUnsafe(card);
            decreaser.start();

            try {
                increaser.join();
                decreaser.join();
            } catch (InterruptedException e) {
                e.getMessage();
                e.printStackTrace();
            }
        }
    }

    private static void startTask2Lesson8(Card card) {
        Synchro synchro = new Synchro();

        SequentialIncreaser increaser = new SequentialIncreaser(synchro, card);
        Thread increaseThread = new Thread(increaser);

        SequentialDecreaser decreaser = new SequentialDecreaser(synchro, card);
        Thread decreaseThread = new Thread(decreaser);

        increaseThread.start();
        decreaseThread.start();

        try {
            increaseThread.join();
            decreaseThread.join();
        } catch (InterruptedException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    private static void startTask3Lesson8(Card card) {
        Object lock = new Object();


        //DeacreaserInterface dec1 = new DecreaserUnsafe(card);
        DecreaserUnsafe dec1 = new DecreaserUnsafe(card);

        new Thread(dec1).start();

        SafeDeacreaserDecorator dec2 = new SafeDeacreaserDecorator(dec1, lock);
        new Thread(dec2).start();







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

            while (!isExit) {

                showMenu();
                inputStr = getInputStr();
                System.out.println("");

                switch (inputStr) {
                    case "1":
                        bankomat.getMoneyBalance();
                        break;

                    case "2":
                        bankomat.getCash();
                        break;

                    case "3":
                        bankomat.putCash();
                        break;

                    case "4":
                        bankomat.createClient();
                        break;

                    case "5":
                        bankomat.deleteClient();
                        break;

                    case "6":
                        bankomat.createCard();
                        break;

                    case "7":
                        bankomat.deleteCard();
                        break;

                    case "8":
                        bankomat.helpInfo();
                        break;

                    case "9":
                        startTask1Lesson8Unsafe(clientCard1);
                        break;

                    case "10":
                        startTask1Lesson8Safe(clientCard1);
                        break;

                    case "11":
                        startTask2Lesson8(clientCard1);
                        break;

                    case "12":
                        startTask3Lesson8(clientCard1);
                        break;

                    case "13":
                        isExit = true;
                        break;

                    default:
                        break;
                }
            }
        }

        FileOutputStream fileOutputStream = new FileOutputStream("Serialize.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(bankomat);
        objectOutputStream.flush();
        objectOutputStream.close();
    }
}
