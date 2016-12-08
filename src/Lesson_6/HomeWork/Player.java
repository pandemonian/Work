package Lesson_6.HomeWork;

/**
 * Created by Gubanov Pavel on 08.12.16.
 */
class Player implements SoccerPlayer {

    private String nickname;
    private Integer points;
    private League league;
    private Country country;

    Player(String nickname, Integer points, League league, Country country) {
        this.nickname = nickname;
        this.points = points;
        this.league = league;
        this.country = country;
    }

    @Override
    public String getNickName() {
        return nickname;
    }

    @Override
    public Integer getPoints() {
        return points;
    }

    @Override
    public League getLeague() {
        return league;
    }

    @Override
    public Country getCountry() {
        return country;
    }
}
