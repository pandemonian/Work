package Lesson_2.HomeWork;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Gubanov Pavel on 07.11.16.
 */
public class Profile {
    static InputStream img1 = Profile.class.getResourceAsStream("ava1.txt");
    static InputStream img2 = Profile.class.getResourceAsStream("ava2.txt");
    static InputStream img3 = Profile.class.getResourceAsStream("ava3.txt");

    static BufferedReader br1 = new BufferedReader(new InputStreamReader(img1));
    static BufferedReader br2 = new BufferedReader(new InputStreamReader(img2));
    static BufferedReader br3 = new BufferedReader(new InputStreamReader(img3));

    static String[][] dbProfile = new String[5][11];
    static String input;      //глобальная переменная для ввода с консоли данных
    static int currentId;     //глобальная переменная - хранит уникальный логин

    static String regexpEmail = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_" +
            "`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
            "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0" +
            "-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[" +
            "0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\" +
            "x0b\\x0c\\x0e-\\x7f])+)\\])";

    static String regexpLogin = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";

    static String regexpPassport = "[0-9]{2}\\s?[0-9]{2}\\s[0-9]{6}";

    static String regexpDate = "(0[1-9]|[12][0-9]|3[01])[- ..](0[1-9]|1[012])[- ..](19|20)\\d\\d";

    static String regexpSite = "https?://([a-z1-9]+.)?[a-z1-9\\-]+(\\.[a-z]+){1,}/?";

    static String regexpPhone = "\\+?\\d+([\\(\\s\\-]?\\d+[\\)\\s\\-]?[\\d\\s\\-]+)?";

    // используем диапазон символов Unicode чтобы парсился разнорегистровый ввод в киррилице
    static String regexFio = "[\\u0410-\\u044F]+[-]?[\\u0410-\\u044F]+[\\s][\\u0410-\\u04" +
            "4F]+[\\s][\\u0410-\\u044F]+";

    //считывание данных с консоли
    static String getInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            input = br.readLine();
        } catch (IOException e) {
            System.out.println("Что-то пошло не так. Попытайтесь ещё раз!");
            getInput();
        }
        return input;
    }

    //общий метод проверки ввода с консоли с регулярным выражением
    static boolean isMatches(String regex, String string) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        return m.matches();
    }

    static void createLogin() {
        System.out.println("Придумайте новый логин: ");
        String login = getInput();
        if (isMatches(regexpLogin, login)) {
            dbProfile[currentId][0] = login.toLowerCase();
        } else {
            System.out.println("Неверный формат, введите другой логин");
            createLogin();
        }
    }

    static void searchLogin() {

        for (int i = 0; i < dbProfile.length; i++) {
            if (input.equals(dbProfile[i][0])) {
                currentId = i;
                System.out.println("Вы зашли под логином \"" + dbProfile[i][0] + "\"");
                break;
            }

            if (i == dbProfile.length - 1) {
                System.out.println("Такого профиля ещё не существует, создайте новый: ");
                System.out.println("Нажмите \"Y\", чтобы создать новый профиль или " +
                        "любую другую клавишу, чтобы отказаться");

                String answer = getInput();

                if (answer.equals("Y") || answer.equals("y")) {
                    createProfile();
                } else break;
            }
        }
    }

    static void createFio() {
        System.out.println("Введите Фамилию Имя Отчество: ");
        String fio = getInput();
        if (isMatches(regexFio, fio)) {
            fio = fio.toLowerCase(); // если ввод разнорегистровый --> сводим всё к нижнему
            char[] chAr = fio.toCharArray();
            chAr[0] = Character.toUpperCase(chAr[0]); //первый символ в ФИО всегда UpperCase

            for (int i = 0; i < chAr.length - 1; i++) {
                if ((chAr[i] == ' ') || (chAr[i] == '-')) {
                    chAr[i + 1] = Character.toUpperCase(chAr[i + 1]);
                }
            }
            fio = String.valueOf(chAr);
            dbProfile[currentId][1] = fio;
        } else {
            System.out.println("Введены некорректные инициалы!");
            createFio();
        }
    }

    static void createEmail() {
        System.out.println("Введите адрес электронной почты: ");
        String email = getInput();
        if (isMatches(regexpEmail, email)) {
            dbProfile[currentId][2] = email;
        } else {
            System.out.println("Указано неверное название почтового ящика!");
            createEmail();
        }
    }

    static void createBirthdate() {
        System.out.println("Укажите дату рождения: ");
        String birthdate = getInput();
        if (isMatches(regexpDate, birthdate)) {
            dbProfile[currentId][3] = birthdate;
        } else {
            System.out.println("Неверный формат, введите дату в правильной форме");
            createBirthdate();
        }

    }

    static void createResidency() {
        System.out.println("Укажите место проживания: ");
        String residency = getInput().trim();
        dbProfile[currentId][4] = residency;
    }

    static void createPassport() {
        String passport;

        System.out.println("Введите серию и номер паспорта: ");
        while (true) {
            passport = getInput();

            if (isMatches(regexpPassport, passport)) {
                dbProfile[currentId][5] = passport;
                break;
            } else {
                System.out.println("Введите корректные данные серии и номера паспорта!");
            }
        }

        System.out.println("Укажите дату выдачи паспорта: ");
        while (true) {
            passport = getInput();

            if (isMatches(regexpDate, passport)) {
                dbProfile[currentId][5] += " " + passport;
                break;
            } else {
                System.out.println("Введите правильные данные о выдаче паспорта!");
            }

        }

        System.out.println("Укажите кем выдан паспорт: ");
        String[] arr = {"оуфмс","уфмс","фмс","ровд","овд","увд","тп","мо"};
        passport = getInput();
        dbProfile[currentId][5] += " " + passport;

        for (int i = 0; i < arr.length; i++) {
            if (dbProfile[currentId][5].contains(arr[i])) {
                dbProfile[currentId][5] = dbProfile[currentId][5].replace(arr[i], arr[i].toUpperCase());
            }
        }
    }
    
    static void printPassport() {
        String number = "";
        String data = "";
        String place = "";

        String s = dbProfile[currentId][5];
        if (!s.equals("")) {
            number = (s.charAt(2) == ' ') ? s.substring(0, 12) : s.substring(0, 11);
            data = (s.charAt(2) == ' ') ? s.substring(13, 23) : s.substring(12, 22);
            place = (s.charAt(2) == ' ') ? s.substring(24, s.length()) : s.substring(23, s.length());
        }
        System.out.println("Паспортные данные: ");
        System.out.println("  серия и номер: " + number);
        System.out.println("    дата выдачи: " + data);
        System.out.println("   место выдачи: " + place);
    }

    static void createPhone() {
        System.out.println("Введите номер сотового телефона: ");
        String phone = getInput();
        if (isMatches(regexpPhone, phone)) {
            dbProfile[currentId][6] = phone;
        } else {
            System.out.println("Укажите правильный номер телефона!");
            createPhone();
        }
    }

    static void createWebsite() {
        System.out.println("Введите адрес своего сайта: ");
        String website = getInput();
        if (isMatches(regexpSite, website)) {
            dbProfile[currentId][7] = website;
        } else {
            System.out.println("Проверье адрес ресурса!");
            createWebsite();
        }
    }

    static void createJobplace() {
        System.out.println("Укажите место работы и должность: ");
        String jobplace = getInput().trim();
        dbProfile[currentId][8] = jobplace;
    }

    static void createAbouself() {
        System.out.println("Укажите информацию о себе: ");
        String aboutself = getInput().trim();
        dbProfile[currentId][9] = aboutself;
    }

    static void createAvatar() {
        System.out.println("Выберите номер аватара: ");
        String avatar = getInput();

        if (avatar.equals("1") || avatar.equals("2") || avatar.equals("3")) {
            dbProfile[currentId][10] = avatar;
        } else {
            System.out.println("Введите цифру от нуля до двух включительно!");
            createAvatar();
        }
    }

    static void showAvatar() {
        String s;

        try {

            if (dbProfile[currentId][10].equals("1")) {
                while ((s = br1.readLine()) != null) {
                    System.out.println(s);
                }
            } else if (dbProfile[currentId][10].equals("2")) {
                while ((s = br2.readLine()) != null) {
                    System.out.println(s);
                }
            } else {
                while ((s = br3.readLine()) != null) {
                    System.out.println(s);
                }
            }
        } catch(IOException e){
            System.out.println("Не удалось найти файл либо другая ошибка ввода вывода");
            System.exit(666);
        }
    }

    static void createProfile() {

        //проверка занятости currentId
        for (int i = 0; i < dbProfile.length; i++) {

            //если поле логина неинициализировано или пустое, то создадим 
            //новый профиль, если "база данных" заполнена -> вызываем delete()
            if ((dbProfile[i][0] == null) || (dbProfile[i][0].equals(""))) {
                currentId = i;
            } else if (i == dbProfile.length - 1) {
                System.out.println("База данных заполнена. Выберите(\"select\") учётную запись для" +
                        "удаления и заново создайте профиль.");
                return;

            } else {
                i++;
            }
        }

        createLogin();
        createFio();
        createEmail();
        createBirthdate();
        createResidency();
        createPassport();
        createPhone();
        createWebsite();
        createJobplace();
        createAbouself();
        createAvatar();
    }

    static void createDefaultProfile() {
        currentId = 0;
        dbProfile[0][0] = "tester";
        dbProfile[0][1] = "Иванов Иван Иванович";
        dbProfile[0][2] = "ivanov@gmail.com";
        dbProfile[0][3] = "01.01.1985";
        dbProfile[0][4] = "г.Самара";
        dbProfile[0][5] = "12 95 456821 01.05.1999 ФМС Советского района г.Самары";
        dbProfile[0][6] = "+7 958 389 05 18";
        dbProfile[0][7] = "http://vk.com/id00000001";
        dbProfile[0][8] = "ПАО \"Ландшафт\", дизайнер";
        dbProfile[0][9] = "Студент-заочник, любитель рыбалки и активного отдыха";
        dbProfile[0][10] = "1";
    }

    static void modifyProfile() {

        while (true) {
            System.out.println("Выберите цифру для редактирования у текущего пользователя: ");

            System.out.println("1 - логина");

            System.out.println("2 - ФИО");

            System.out.println("3 - e-mail");

            System.out.println("4 - даты рождения");

            System.out.println("5 - места жительства");

            System.out.println("6 - паспортнх данных");

            System.out.println("7 - номера сотового телефона");

            System.out.println("8 - веб-страницы");

            System.out.println("9 - места работы и должности");

            System.out.println("10 - информации о себе");

            System.out.println("11 - аватара");

            System.out.println("Для выхода введите \"exit\"");

               input = getInput();

            if (input.equals("exit")) {
                break;
            } else if (input.equals("1")) {
                createLogin();

            } else if (input.equals("2")) {
                createFio();

            } else if (input.equals("3")) {
                createEmail();

            } else if (input.equals("4")) {
                createBirthdate();

            } else if (input.equals("5")) {
                createResidency();

            } else if (input.equals("6")) {
                createPassport();

            } else if (input.equals("7")) {
                createPhone();

            } else if (input.equals("8")) {
                createWebsite();

            } else if (input.equals("9")) {
                createJobplace();

            } else if (input.equals("10")) {
                createAbouself();

            } else if (input.equals("11")) {
                createAvatar();
            }
        }
    }

    static void showProfile()  {
        System.out.println("Логин: " + dbProfile[currentId][0]);
        System.out.println("ФИО: " + dbProfile[currentId][1]);
        System.out.println("e-mail: " + dbProfile[currentId][2]);
        System.out.println("Дата рождения: " + dbProfile[currentId][3]);
        System.out.println("Место жительства: " + dbProfile[currentId][4]);
        printPassport();                      //выводим dbProfile[currentId][5])
        System.out.println("Номер сотового: " + dbProfile[currentId][6]);
        System.out.println("Веб-сайт: " + dbProfile[currentId][7]);
        System.out.println("Место работы: " + dbProfile[currentId][8]);
        System.out.println("Информация о себе: " + dbProfile[currentId][9]);
        System.out.println("Аватар: ");
        showAvatar();
    }

    static void deleteProfile() {
        for (int i = 0; i < dbProfile[currentId].length; i++) {
                dbProfile[currentId][i] = "";
        }
    }

    static void selectAccount() {
        System.out.println("Укажите логин учётной записи под которой хотите зайти: ");

        input = getInput();
        searchLogin();
    }

    public static void main(String[] args) {

        /*
        * Сначала программа создаём тестовый профиль, иначе первое условие задачи(выбор учётки в
        * зависомости от логина) будет всегда "false" и будет всегда создавать выполнять
        * создание профиля, т.к. при старте данных нет.
        * Функционал доработан, добавились новые функции:
        * select - переключение между учётными записями.
        * exit - выход из программы
        */

        createPassport();
        createDefaultProfile();

        System.out.println("Введите логин: ");
        input = getInput();

        searchLogin();

        while (true) {
            System.out.println("----------------------------");
            System.out.println("Выберите нужное действие:");
            System.out.println("show/modify/select/delete/exit");
            System.out.println("----------------------------");
            input = getInput();
            if (input.equals("show")) {
                showProfile();
            } else if (input.equals("delete")) {
                deleteProfile();
            } else if (input.equals("modify")) {
                modifyProfile();
            } else if (input.equals("select")) {
                selectAccount();
            } else if (input.equals("exit")) {
                break;
            }
        }
    }
}
