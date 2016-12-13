package Lesson_6.HomeWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Gubanov Pavel on 08.12.16.
 */
public class Run {
    static private Random random = new Random();
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
    
    static String getRandomFio() {
        String[] firstName = {"Алексей", "Андрей", "Анатолий", "Фёдор", "Павел", "Олег", "Николай", "Виктор",
                "Михаил", "Евгений", "Владимир", "Лев", "Василий", "Илья", "Григорий", "Ярослав", "Георгий", "Дмитрий",
                "Борис", "Александр"};

        String[] lastName = {"Любимов", "Петров", "Кущенко", "Глыба", "Филин", "Поломеев", "Заводченко", "Красик",
                "Удовиченко", "Псалтырский", "Кирийчук", "Молотьба", "Лютый", "Паршев", "Игнатов", "Гулич", "Дубчак",
                "Галеня", "Лобунов"};

        String[] patronymicName = {"Олегович", "Васильевич", "Ильич", "Ярославочи", "Дмитриевич", "Львович", "Борисович",
                "Георгиевич", "Алексеевич", "Михайлович", "Владимирович", "Никитович", "Евгеньевич", "Григорьевич",
                "Александрович", "Анатольевич", "Фёдорович", "Павлович"};

        String name = firstName[random.nextInt(firstName.length)];
        String surname = lastName[random.nextInt(lastName.length)];
        String patronymic = patronymicName[random.nextInt(patronymicName.length)];

        StringBuilder result = new StringBuilder(surname);
        result.append(" ");
        result.append(name);
        result.append(" ");
        result.append(patronymic);

        return result.toString();
    }

    static CountryName getRandomCountryName() {
        int randomDigit = random.nextInt(3) + 1;

        if (randomDigit == 1)  return CountryName.Россия;
        if (randomDigit == 2)  return CountryName.Украина;
        else  return CountryName.Беларусь;
    }

    static LeagueName getRandomLeagueName() {
        int randomDigit = random.nextInt(3) + 1;

        if (randomDigit == 1)  return LeagueName.ПРЕМЬЕР_ЛИГА;
        if (randomDigit == 2)  return LeagueName.ПЕРВАЯ_ЛИГА;
        else  return LeagueName.ВТОРАЯ_ЛИГА;
    }

    static int getRandomPoints() {
        //минимизируем ситуацию получениея низкого рейтинга большим количеством игроков
        int chance = random.nextInt(11);

        if (chance > 3) return random.nextInt(6) + 5;
        else return random.nextInt(4) + 2;
    }

    void initChampionship(Championship champ) {

        LeagueName l1 = LeagueName.ПРЕМЬЕР_ЛИГА;
        LeagueName l2 = LeagueName.ПЕРВАЯ_ЛИГА;
        LeagueName l3 = LeagueName.ВТОРАЯ_ЛИГА;

        champ.getAllLeagues().add(new League(l1, initLeague(l1)));
        champ.getAllLeagues().add(new League(l2, initLeague(l2)));
        champ.getAllLeagues().add(new League(l3, initLeague(l3)));
    }

    List<SoccerPlayer> initLeague(LeagueName league) {

        List<SoccerPlayer> leaguePlayers = new ArrayList<>();

            for (int team = 0; team < 6; team++) {
                for (int player = 0; player < 11; player++) {
                    leaguePlayers.add(new Player(getRandomFio(), getRandomPoints(), league, getRandomCountryName()));
                }
            }
        return leaguePlayers;
    }

    static boolean isInputIsLeagueName() {
        inputStr = getInputStr();
        return (inputStr.equals(LeagueName.ПРЕМЬЕР_ЛИГА) || inputStr.equals(LeagueName.ПЕРВАЯ_ЛИГА)
                || inputStr.equals(LeagueName.ВТОРАЯ_ЛИГА));
    }

    static void printOut(List<SoccerPlayer> soccerPlayers) {

        Collections.sort(soccerPlayers);

        for (SoccerPlayer player: soccerPlayers) {
            System.out.println(player.getNickName() + ", страна: " + player.getCountryName() + ", лига: "
                    + player.getLeagueName() + ", рейтинг: " + player.getPoints());
        }
    }

    public static void main(String[] args) {

        Run run = new Run();
        Championship russianChamp = new Championship();
        LeagueManager leagueManager = new Manager("Астрелин Артём Каримович");

        run.initChampionship(russianChamp);
        leagueManager.manage(russianChamp);

        printOut(leagueManager.getAllPlayers());
        System.out.println("============================================================");
        printOut(leagueManager.getPlayers(LeagueName.ПРЕМЬЕР_ЛИГА));
        System.out.println("============================================================");
        printOut(leagueManager.getPlayers(CountryName.Беларусь));


        //System.out.println(isInputIsLeagueName());
    }


}



