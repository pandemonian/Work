package Lesson_6.HomeWork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gubanov Pavel on 08.12.16.
 */
public class Run {
    static Random random = new Random();


    public static void main(String[] args) {

        System.out.println();
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

        if (randomDigit == 1)  return LeagueName.ПРЕМЬЕР_ЛИГА
        if (randomDigit == 2)  return LeagueName.ПЕРВАЯ_ЛИГА;
        else  return LeagueName.ВТОРАЯ_ЛИГА;
    }

    static int getRandomPoints() {
        //минимизируем ситуацию получениея низкого рейтинга большим количеством игроков
        int chance = random.nextInt(11);

        if (chance > 3) return random.nextInt(6) + 5;
        else return random.nextInt(4) + 2;
    }

    static iniLeague(String nickName, CountryName countryName, LeagueName leagueName, int rating) {
        //создаём премьер-лигу(16 комманд по 11 человек)
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 11 ; j++) {

            }

        }
    }

    void initChampionship(Championship champ) {

        LeagueName l1 = LeagueName.ПРЕМЬЕР_ЛИГА;
        LeagueName l2 = LeagueName.ПЕРВАЯ_ЛИГА;
        LeagueName l3 = LeagueName.ВТОРАЯ_ЛИГА;

        champ.getAllLeagues().add(new League(l1, initPlayers(l1)));
        champ.getAllLeagues().add(new League(l2, initPlayers(l2)));
        champ.getAllLeagues().add(new League(l3, initPlayers(l3)));
    }

    List<SoccerPlayer> initPlayers(LeagueName league) {

        List<SoccerPlayer> temp = new ArrayList<>();

            for (int team = 0; team < 16; team++) {
                for (int player = 0; player < 11; player++) {

                    temp.add(new Player(getRandomFio(), getRandomPoints(), league, getRandomCountryName()));
                }
            }
        return temp;
    }


}



