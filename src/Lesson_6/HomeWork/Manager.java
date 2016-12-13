package Lesson_6.HomeWork;

import java.util.ArrayList;
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

    }

    @Override
    public void manage(Championship champ) {
        currentChampionship = champ;
    }
}
