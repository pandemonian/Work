package Lesson_6.HomeWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubanov Pavel on 10.12.16.
 */
public class League {

    private LeagueName leagueName;
    private List<SoccerPlayer> soccerPlayers;

    League(LeagueName leagueName, List<SoccerPlayer> soccerPlayers) {
        this.leagueName = leagueName;
        this.soccerPlayers = soccerPlayers;
    }

    public LeagueName getLeagueName() {
        return leagueName;
    }

    public List<SoccerPlayer> getSoccerPlayers() {
        return soccerPlayers;
    }

}
