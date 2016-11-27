package Lesson_4.HomeWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Gubanov Pavel on 25.11.16.
 */
public class Run {

    private static String inputStr;
    private static String team1Name;
    private static String team2Name;
    private static int team1NumWarriors;
    private static int team2NumWarriors;
    private static Warrior[] team1;
    private static Warrior[] team2;
    private static String[] warriorName = {"Adar", "Abner", "Alford", "Bennett", "Ward", "Wild",
            "Irk", "Kellen", "Odin"};

    private static String getInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("Произошла ошиббка ввода-вывода");
            return "";
        }
    }

    private static String getRandomNameWarrior() {
        int index = Squad.random.nextInt(warriorName.length);
        return warriorName[index];
    }

    private static Warrior getRandomWarrior(String nameWarrior, String teamName) {
        int random = Squad.random.nextInt(3) + 1;

        if (random == 1)  return new Viking(nameWarrior, teamName);
        if (random == 2)  return new Archer(nameWarrior, teamName);
        else return new Barbarian(nameWarrior, teamName);
    }

    private static void initNameTeams() {

        System.out.println("Выберите название первого отряда:");

        if (!(inputStr = getInput()).equals(""))  team1Name = inputStr;
        else {
            System.out.println("Ничего не введено, указано название отряда по-умолчанию - \"England\"");
            team1Name = "England";
            System.out.println("---------------------------------------------------------------------");
        }

        System.out.println("Выберите название второго отряда:");

        if (!(inputStr = getInput()).equals(""))  team2Name = inputStr;
        else {
            System.out.println("Ничего не введено, указано название отряда по-умолчанию - \"France\"");
            team2Name = "France";
            System.out.println("---------------------------------------------------------------------");
        }
    }

    private static void initCountWarriorsAtTeams() {
        int inputInt;

        //указываем кол-во бойцов первого отряда
        System.out.println("Укажите численность отряда: " + team1Name);

        try {
            if ((inputInt = Integer.parseInt(getInput())) > 0) team1NumWarriors = inputInt;
            else {
                System.out.println("Численность отряда не может быть отрицательной, либо ровняться нулю." +
                        " будет указана численность отряда по-умолчанию (5 воинов)");
                team1NumWarriors = 5;
            }
        } catch (NumberFormatException e) {
            System.out.println("Введённые данные не являются числом, будет указана численность отряда " +
                    "по-умолчанию (5 воинов)");
            team1NumWarriors = 5;
        }
        System.out.println("----------------------------------------------------------------------------" +
                "-------------------");

        //указываем кол-во бойцов второго отряда
        System.out.println("Укажите численность отряда: " + team2Name);

        try {
            if ((inputInt = Integer.parseInt(getInput())) > 0) team2NumWarriors = inputInt;
            else {
                System.out.println("Численность отряда не может быть отрицательной, либо ровняться нулю." +
                        " будет указана численность отряда по-умолчанию (5 воинов)");
                team2NumWarriors = 5;
            }
        } catch (NumberFormatException e) {
            System.out.println("Введённые данные не являются числом, будет указана численность отряда " +
                    "по-умолчанию (5 воинов)");
            team2NumWarriors = 5;
        }
        System.out.println("----------------------------------------------------------------------------" +
                "-------------------");
    }

    private static void initNameAndTypeWarriors() {
        String name;
        //создаём бойцов первого отряда
        team1 = new Warrior[team1NumWarriors];
        System.out.println("Укажите данные отряда " + team1Name);

        for (int i = 0; i < team1NumWarriors; i++) {
            System.out.println("Укажите имя бойца № " + (i + 1));
            name = getInput();
            if (name.equals("")) {
                name = getRandomNameWarrior();
                System.out.println("Имя бойца не указано, будет взято случайное имя!");
                System.out.println("Имя бойца № " + (i + 1) + " " + name);
                System.out.println("---------------------------------------");
            }

            System.out.println("Укажите тип бойца № " + (i + 1));
            System.out.println("1 - Viking, 2 - Archer, 3 - Barbarian");
            inputStr = getInput();
            if (inputStr.equals("1"))  team1[i] = new Viking(name, team1Name);
            if (inputStr.equals("2"))  team1[i] = new Archer(name, team1Name);
            if (inputStr.equals("3"))  team1[i] = new Barbarian(name, team1Name);
            else {
                System.out.println("Тип бойца не указан, бойцу будет присвоен случайный тип!");
                team1[i] = getRandomWarrior(name, team1Name);
                System.out.println("Тип бойца № " + (i + 1) + " " + team1[i].getType());
            }
            System.out.println("---------------------------------------");
        }

        System.out.println("=====================================");

        //создаём бойцов второго отряда
        team2 = new Warrior[team2NumWarriors];
        System.out.println("Укажите данные отряда " + team2Name);

        for (int i = 0; i < team2NumWarriors; i++) {
            System.out.println("Укажите имя бойца № " + (i + 1));
            name = getInput();
            if (name.equals("")) {
                name = getRandomNameWarrior();
                System.out.println("Имя бойца не указано, будет взято случайное имя!");
                System.out.println("Имя бойца № " + (i + 1) + " " + name);
                System.out.println("---------------------------------------");
            }

            System.out.println("Укажите тип бойца № " + (i + 1));
            System.out.println("1 - Viking, 2 - Archer, 3 - Barbarian");
            inputStr = getInput();
            if (inputStr.equals("1"))  team2[i] = new Viking(name, team2Name);
            if (inputStr.equals("2"))  team2[i] = new Archer(name, team2Name);
            if (inputStr.equals("3"))  team2[i] = new Barbarian(name, team2Name);
            else {
                System.out.println("Тип бойца не указан, бойцу будет присвоен случайный тип!");
                team2[i] = getRandomWarrior(name, team2Name);
                System.out.println("Тип бойца № " + (i + 1) + " " + team2[i].getType());
            }
            System.out.println("---------------------------------------");
        }
    }


    public static void main(String[] args) {

        initNameTeams();
        initCountWarriorsAtTeams();
        initNameAndTypeWarriors();

        Squad squad1 = new Squad(team1Name, team1);
        Squad squad2 = new Squad(team2Name, team2);

        Battle greatBattle = new Battle();

        System.out.println("Битва началась!!! " + DataHelper.getFormattedStartDate());

        while (true) {
            greatBattle.striking(squad1, squad2);
            DataHelper.skipTime();
            if (greatBattle.isAnyLoose(squad1, squad2)) {
                greatBattle.winnerIs(squad1, squad2);
                break;
            }

            greatBattle.striking(squad2, squad1);
            DataHelper.skipTime();
            if (greatBattle.isAnyLoose(squad1, squad2)) {
                greatBattle.winnerIs(squad1, squad2);
                break;
            }
        }
        System.out.println(DataHelper.getFormattedDiff());
    }
}
