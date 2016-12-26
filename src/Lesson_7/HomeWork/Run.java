package Lesson_7.HomeWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubanov Pavel on 25.11.16.
 */
public class Run { //класс используется для запуска программы и показа окна. в нем не должно быть больше никакой логики

    private static String team1Name; //почему все поля и методы статичные?
    private static String team2Name;
    private static List<Warrior> team1 = new ArrayList<>();
    private static List<Warrior> team2 = new ArrayList<>();
    private static String[] warriorName = {"Adar", "Abner", "Alford", "Bennett", "Ward", "Wild",
            "Irk", "Kellen", "Odin"};

    private static String getRandomNameWarrior() {
        int index = Squad.random.nextInt(warriorName.length);
        return warriorName[index];
    }

    static void initNameTeams() {
        String inputStr;

        inputStr = Gui.getFieldFirstNameTeam();
        if (!inputStr.equals("")) {
            team1Name = inputStr;
            Gui.setLog("Название первого отряда: ", team1Name);
            //Gui.setLog2("123", "dfg");
        }
        else {
            Gui.setLog("Ничего не введено, указано название первого отряда по-умолчанию - ", "England");
            team1Name = "England";
        }

        inputStr = Gui.getFieldSecondNameTeam();
        if (!inputStr.equals("")  &&  !inputStr.equals(team1Name)) {
            team2Name = inputStr;
            Gui.setLog("Название второго отряда: ", team2Name);
        }
        else {
            Gui.setLog("Ничего не введено, либо указано имя первого отряда. Присвоено название второго отряда по-умолчанию", "France");
            team2Name = "France";
        }
    }

    static boolean isTeamsNotEmpty() {
        return ((team1.size() > 0) && (team2.size() > 0));
    }

    static void initNameAndTypeWarriors() {
        List<Warrior> currentTeam = null;
        String currentTeamName = ""; //сюда все равно по умолчанию записывается "Название первой команды"
        String currentTypeWarrior = "";
        String nameWarrior = Gui.getFieldNameWarrior();
        int indexTeam = Gui.getComboBoxTeam();
        int indexTypeWarrior = Gui.getComboBoxTypeWarrior();

        if (indexTeam == 0) {
            currentTeam = team1;
            currentTeamName = team1Name;
        }
        if (indexTeam == 1){
            currentTeam = team2;
            currentTeamName = team2Name;
        } //в таком случае якобы остается вариант, когда currentTeam = null. на самом деле такого варианта не должно быть. если не 1, то 2

        if (nameWarrior.equals(""))  nameWarrior = getRandomNameWarrior();

        if (indexTypeWarrior == 0) { //для множественного выбора лучше использовать switch
            currentTeam.add(new Viking(nameWarrior, currentTeamName));
            currentTypeWarrior = "Viking";
        }
        if (indexTypeWarrior == 1) {
            currentTeam.add(new Archer(nameWarrior, currentTeamName));
            currentTypeWarrior = "Archer";
        }
        if (indexTypeWarrior == 2) {
            currentTeam.add(new Barbarian(nameWarrior, currentTeamName));
            currentTypeWarrior = "Barbarian";
        }


        if (indexTeam == 0) {
            Gui.setFieldFirstTeamWarriorList(nameWarrior, currentTypeWarrior);
        }
        else Gui.setFieldSecondTeamWarriorList(nameWarrior, currentTypeWarrior);
    }

    static void initFight() {
        Squad squad1 = new Squad(team1Name, team1);
        Squad squad2 = new Squad(team2Name, team2);

        Battle greatBattle = new Battle();

        Gui.setLog("Битва началась!!! ", DataHelper.getFormattedStartDate());

        while (true) {
            greatBattle.striking(squad1, squad2);
            DataHelper.skipTime();
            if (greatBattle.isAnyLoose(squad1, squad2)) {
                greatBattle.winnerIs(squad1, squad2);
                break;
            }

            greatBattle.striking(squad2, squad1); //2 раза одно и то же. вынести в отдельный метод
            DataHelper.skipTime();
            if (greatBattle.isAnyLoose(squad1, squad2)) {
                greatBattle.winnerIs(squad1, squad2);
                break;
            }
        }
        Gui.setLog("Бой продолжался: ", DataHelper.getFormattedDiff());
    }


    public static void main(String[] args) {

        new Gui();
    }
}
