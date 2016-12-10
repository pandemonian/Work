package Lesson_6.HomeWork;

import java.util.List;

/**
 * Created by Gubanov Pavel on 08.12.16.
 */
public class Manager implements LeagueManager {

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
        return null;
    }

    @Override
    public List<SoccerPlayer> getPlayers(LeagueName leagueName) {
        return null;
    }

    @Override
    public List<SoccerPlayer> getPlayers(CountryName countryName) {
        return null;
    }

    @Override
    public void addPoints(String name, int points) {

    }
}
