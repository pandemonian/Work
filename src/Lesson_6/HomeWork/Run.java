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

    private static Championship russianChamp;
    private static LeagueManager leagueManager;





    private static String getInputStr() {
        try {
            return br.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода!");
            return "";
        }
    }
    
    private static String getRandomFio() {
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

    private static CountryName getRandomCountryName() {
        int randomDigit = random.nextInt(3) + 1;

        if (randomDigit == 1)  return CountryName.Россия;
        if (randomDigit == 2)  return CountryName.Украина;
        else  return CountryName.Беларусь;
    }

    private static LeagueName getRandomLeagueName() {
        int randomDigit = random.nextInt(3) + 1;

        if (randomDigit == 1)  return LeagueName.ПРЕМЬЕР_ЛИГА;
        if (randomDigit == 2)  return LeagueName.ПЕРВАЯ_ЛИГА;
        else  return LeagueName.ВТОРАЯ_ЛИГА;
    }

    private static int getRandomPoints() {
        //минимизируем ситуацию получениея низкого рейтинга большим количеством игроков
        int chance = random.nextInt(11);

        if (chance > 3) return random.nextInt(6) + 5;
        else return random.nextInt(4) + 2;
    }

    private static void initChampionship() {

        LeagueName l1 = LeagueName.ПРЕМЬЕР_ЛИГА;
        LeagueName l2 = LeagueName.ПЕРВАЯ_ЛИГА;
        LeagueName l3 = LeagueName.ВТОРАЯ_ЛИГА;
        
        

        russianChamp.getAllLeagues().add(new League(l1, initLeague(l1)));
        russianChamp.getAllLeagues().add(new League(l2, initLeague(l2)));
        russianChamp.getAllLeagues().add(new League(l3, initLeague(l3)));
    }

    private static List<SoccerPlayer> initLeague(LeagueName league) {

        List<SoccerPlayer> leaguePlayers = new ArrayList<>();

            for (int team = 0; team < 1; team++) {
                for (int player = 0; player < 2; player++) {
                    leaguePlayers.add(new Player(getRandomFio(), getRandomPoints(), league, getRandomCountryName()));
                }
            }
        return leaguePlayers;
    }

    private static void printAllPlayers() {

        List<SoccerPlayer> soccerPlayers = leagueManager.getAllPlayers();

        Collections.sort(soccerPlayers);

        for (SoccerPlayer player: soccerPlayers) {
            System.out.println(player.getNickName() + ", страна: " + player.getCountryName() + ", лига: "
                    + player.getLeagueName() + ", рейтинг: " + player.getPoints());
        }
    }

    private static void printAllPlayersViaLeague() {
        LeagueName name;
        String input;

        System.out.println("Введите название лиги по которой будет происходить сортировка:");

        while (true) {
            input = getInputStr().trim().toLowerCase();

            if (input.equals("премьер лига")) {
                name = LeagueName.ПРЕМЬЕР_ЛИГА;
                break;
            } if (input.equals("первая лига")) {
                name = LeagueName.ПЕРВАЯ_ЛИГА;
                break;
            } if (input.equals("вторая лига")) {
                name = LeagueName.ВТОРАЯ_ЛИГА;
                break;
            }

            System.out.println("Введите правильное название лиги(премьер лига, вторая лига и т.д.)!");
        }

        List<SoccerPlayer> soccerPlayers = leagueManager.getPlayers(name);

        Collections.sort(soccerPlayers);

        for (SoccerPlayer player: soccerPlayers) {
            System.out.println(player.getNickName() + ", страна: " + player.getCountryName() + ", лига: "
                    + player.getLeagueName() + ", рейтинг: " + player.getPoints());
        }
    }

    private static void printAllPlayersViaCountry() {
        CountryName name;
        String input;

        System.out.println("Введите название страны происхождения игрока по которой будет происходить сортировка:");

        while (true) {
            input = getInputStr().trim().toLowerCase();

            if (input.equals("россия")) {
                name = CountryName.Россия;
                break;
            } if (input.equals("украина")) {
                name = CountryName.Украина;
                break;
            } if (input.equals("беларусь")) {
                name = CountryName.Беларусь;
                break;
            }

            System.out.println("Введите правильное название страны:(Россия, Украина, Беларусь)!");
        }

        List<SoccerPlayer> soccerPlayers = leagueManager.getPlayers(name);

        Collections.sort(soccerPlayers);

        for (SoccerPlayer player: soccerPlayers) {
            System.out.println(player.getNickName() + ", страна: " + player.getCountryName() + ", лига: "
                    + player.getLeagueName() + ", рейтинг: " + player.getPoints());
        }
    }

    static void printAddPoints() {
        System.out.println("Введите ФИО игрока кому нужно добавить очки");
        String name = getInputStr();
        System.out.println("Введите количество очков, которое хотите добавить игроку");
        int points = Integer.parseInt(getInputStr());

        leagueManager.addPoints(name, points);
   }

    public static void main(String[] args) {

        russianChamp = new Championship();
        leagueManager = new Manager("Астрелин Артём Каримович");

        initChampionship();
        leagueManager.manage(russianChamp);

        //printAllPlayersViaLeague();
        //System.out.println("===========");
        //printAllPlayersViaCountry();

        //printAddPoints();

        printAllPlayers();

        while (true) {
            System.out.println("Выберите действие:");
            String input = getInputStr();

            if (input.equals("1")) printAllPlayersViaCountry();
            if (input.equals("2")) printAddPoints();

        }




        /*while (true) {
            if (getInputStr().equals(3)) {
                leagueManager.addPoints();
            }
        }*/
        /*System.out.println("============================================================");
        printOut(leagueManager.getPlayers(LeagueName.ПРЕМЬЕР_ЛИГА));
        System.out.println("============================================================");
        printOut(leagueManager.getPlayers(CountryName.Беларусь));
*/

        //System.out.println(isInputIsLeagueName());
    }


}



