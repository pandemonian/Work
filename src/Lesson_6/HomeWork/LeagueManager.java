package Lesson_6.HomeWork;

import java.util.List;

/**
 * Created by Gubanov Pavel on 08.12.16.
 */
public interface LeagueManager {

    void addPlayer(SoccerPlayer player);
    void removePlayer(SoccerPlayer player);
    SoccerPlayer getPlayer(String name);
    List<SoccerPlayer> getAllPlayers();
    List<SoccerPlayer> getPlayers(League league);
    List<SoccerPlayer> getPlayers(Country country);
    void addPoints(String name, int points);
}
