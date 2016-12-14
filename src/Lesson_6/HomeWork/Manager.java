package Lesson_6.HomeWork;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Gubanov Pavel on 08.12.16.
 */
public class Manager implements LeagueManager {

    private Championship currentChampionship;
    private String nickname;

    Manager(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void addPlayer(SoccerPlayer player) {

    }

    @Override
    public void removePlayer(SoccerPlayer player) {

    }

    @Override
    public SoccerPlayer getPlayer(String name) {
        return null;
    }

    @Override
    public List<SoccerPlayer> getAllPlayers() {

        List<SoccerPlayer> allPlayer = new ArrayList<>();
        for (League league: currentChampionship.getAllLeagues()) {
            allPlayer.addAll(league.getSoccerPlayers());
        }
        return allPlayer;
    }

    @Override
    public List<SoccerPlayer> getPlayers(LeagueName leagueName) {

        List<SoccerPlayer> allPlayer = new ArrayList<>();
        for (League league: currentChampionship.getAllLeagues()) {
            for (SoccerPlayer player: league.getSoccerPlayers()) {
                if (player.getLeagueName().equals(leagueName))  allPlayer.add(player);
            }
        }
        return allPlayer;
    }

    @Override
    public List<SoccerPlayer> getPlayers(CountryName countryName) {

        List<SoccerPlayer> allPlayer = new ArrayList<>();
        for (League league: currentChampionship.getAllLeagues()) {
            for (SoccerPlayer player: league.getSoccerPlayers()) {
                if (player.getCountryName().equals(countryName))  allPlayer.add(player);
            }
        }
        return allPlayer;
    }

    @Override
    public void addPoints(String name, int points) {

        /*Iterator<League> league = currentChampionship.getAllLeagues().iterator();
        Iterator<SoccerPlayer> player = league.next().getSoccerPlayers().iterator();

        while (league.hasNext()) {
            league.next();

            while (player.hasNext()) {

                SoccerPlayer soccerPlayer = player.next();
                if (soccerPlayer.getNickName().equals(name)) {
                    if (soccerPlayer.getPoints() + points > 10) {
                        System.out.println("Текущий рейтинг у игрока" + soccerPlayer.getPoints() + ", вы хотите " +
                                "добавить " + points + " очков. Но рейтинг не может быть более 10 баллов, поэтому" +
                                "присваевается максимальное значение равное 10!");
                        soccerPlayer.setPoints(10);
                    }
                    soccerPlayer.setPoints(soccerPlayer.getPoints() + points);
                }
            }
        }*/
        for (League league: currentChampionship.getAllLeagues()) {
            for (int player = 0; player < league.getSoccerPlayers().size(); player++) {
                if (name.equals(league.getSoccerPlayers().get(player).getNickName())) {
                    league.getSoccerPlayers().get(player).setPoints(points
                            + league.getSoccerPlayers().get(player).getPoints());
                }
            }
        }
    }

    @Override
    public void manage(Championship champ) {
        currentChampionship = champ;
    }
}
