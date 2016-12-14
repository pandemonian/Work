package Lesson_6.HomeWork;

import java.util.List;

/**
 * Created by Gubanov Pavel on 10.12.16.
 */
class League {

    private LeagueName leagueName;
    private List<SoccerPlayer> soccerTeam;

    League(LeagueName leagueName, List<SoccerPlayer> soccerTeam) {
        this.leagueName = leagueName;
        this.soccerTeam = soccerTeam;
    }

    List<SoccerPlayer> getSoccerTeam() {
        return soccerTeam;
    }

}
