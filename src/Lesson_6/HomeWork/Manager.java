package Lesson_6.HomeWork;

import java.util.List;

/**
 * Created by Gubanov Pavel on 08.12.16.
 */
public class Manager implements LeagueManager {

    private String nickname;
    private League league;
    private Country country;

    Manager(String nickname, League league, Country country) {
        this.nickname = nickname;
        this.league = league;
        this.country = country;
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
    public List<SoccerPlayer> getPlayers(League league) {
        return null;
    }

    @Override
    public List<SoccerPlayer> getPlayers(Country country) {
        return null;
    }

    @Override
    public void addPoints(String name, int points) {

    }
}
