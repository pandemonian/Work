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
    private static String input;
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

    private static String getFio() {
        String fio;
        while (true) {
            fio = getInputStr();
            if (fio.matches("[а-яА-Я]{3,}\\s[а-яА-Я]{3,}\\s[а-яА-Я]{3,}"))  return fio;
            System.out.println("Введите правильные: фамилию, имя, отчество");
        }
    }

    private static CountryName getRandomCountryName() {
        int randomDigit = random.nextInt(3) + 1;

        if (randomDigit == 1)  return CountryName.Россия;
        if (randomDigit == 2)  return CountryName.Украина;
        else  return CountryName.Беларусь;
    }

    private static CountryName getCountryName() {
        CountryName countryName;
        while (true) {
            input = getInputStr().trim().toLowerCase();

            if (input.equals("россия")) {
                countryName = CountryName.Россия;
                return countryName;
            } if (input.equals("украина")) {
                countryName = CountryName.Украина;
                return countryName;
            } if (input.equals("беларусь")) {
                countryName = CountryName.Беларусь;
                return countryName;
            }
            System.out.println("Введите правильное название страны:(Россия, Украина, Беларусь)!");
        }
    }

    private static int getRandomPoints() {
        //минимизируем ситуацию получениея низкого рейтинга большим количеством игроков
        int chance = random.nextInt(11);

        if (chance > 3) return random.nextInt(6) + 5;
        else return random.nextInt(4) + 2;
    }

    private static int getPoints() {
        int points;
        while (true) {

            try {
                points = Integer.parseInt(getInputStr());
            } catch (NumberFormatException e) {
                System.out.println("Введите число!!!");
                continue;
            }

            if ((points > 1) && (points < 11))  return points;
            System.out.println("Рейтинг игрока должен быть не менее 2 и не более 10 !!!");
        }
    }

    private static LeagueName getLeagueName() {
        LeagueName leagueName;
        while (true) {
            input = getInputStr().trim().toLowerCase();

            if (input.equals("премьер лига")) {
                leagueName = LeagueName.ПРЕМЬЕР_ЛИГА;
                return leagueName;
            } if (input.equals("первая лига")) {
                leagueName = LeagueName.ПЕРВАЯ_ЛИГА;
                return leagueName;
            } if (input.equals("вторая лига")) {
                leagueName = LeagueName.ВТОРАЯ_ЛИГА;
                return leagueName;
            }
            System.out.println("Введите правильное название лиги(премьер лига, вторая лига и т.д.)!");
        }
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

        //для удобства, будем считать, что в девизионе 8 команд вместо 16-ти
            for (int team = 0; team < 8; team++) {
                for (int player = 0; player < 11; player++) {
                    leaguePlayers.add(new Player(getRandomFio(), getRandomPoints(), league, getRandomCountryName()));
                }
            }
        return leaguePlayers;
    }

    private static void addPlayer() {
        String fio;
        LeagueName leagueName;
        CountryName countryName;
        int points;

        System.out.println("Введите ФИО игрока:");
        fio = getFio();

        System.out.println("Введите название лиги:");
        leagueName = getLeagueName();

        System.out.println("Введите название страны происхождения игрока по которой будет происходить сортировка:");
        countryName = getCountryName();

        System.out.println("Укажите рейтинг игрока(от 2 до 10 включительно)");
        points = getPoints();

        leagueManager.addPlayer(new Player(fio, points, leagueName, countryName));
    }

    private static void removePlayer() {
        System.out.println("Укажите имя игрока, которого хотите удалить:");
        String name = getInputStr();
        leagueManager.removePlayer(leagueManager.getPlayer(name));
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

    private static boolean isPlayerWithNameExist(String name) {

        for (League league: russianChamp.getAllLeagues()) {
            for (SoccerPlayer player: league.getSoccerTeam()) {
                if (player.getNickName().equals(name))  return true;
            }
        }
        return false;
    }

    private static void addPoints() {
        System.out.println("Введите ФИО игрока кому нужно добавить очки");
        String name = getInputStr();

        if (!isPlayerWithNameExist(name)) {
            try {
                throw new PlayerNotFoundException();
            } catch (PlayerNotFoundException e) {
                e.getNoNicknamePlayerMsg();
                return;
            }
        }

        System.out.println("Введите количество очков, которое хотите добавить игроку");
        int points = Integer.parseInt(getInputStr());

        leagueManager.addPoints(name, points);
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 - Вывести всех игроков");
        System.out.println("2 - Вывести игроков определённой страны");
        System.out.println("3 - Вывести игроков определённой лиги");
        System.out.println("4 - Добавить очки определённому игроку");
        System.out.println("5 - Добавить игрока");
        System.out.println("6 - Удалить игрока");
        System.out.println("7 - Выход");
    }

    public static void main(String[] args) {

        russianChamp = new Championship();
        leagueManager = new Manager("Астрелин Артём Каримович");

        initChampionship();
        leagueManager.manage(russianChamp);

        while (true) {
            showMenu();
            String input = getInputStr();

            if (input.equals("1"))  printAllPlayers();
            if (input.equals("2"))  printAllPlayersViaCountry();
            if (input.equals("3"))  printAllPlayersViaLeague();
            if (input.equals("4"))  addPoints();
            if (input.equals("5"))  addPlayer();
            if (input.equals("6"))  removePlayer();
            if (input.equals("7"))  break;
        }
    }
}



