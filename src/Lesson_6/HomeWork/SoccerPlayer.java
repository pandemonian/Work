package Lesson_6.HomeWork;

/**
 * Created by Gubanov Pavel on 08.12.16.
 */
public interface SoccerPlayer extends Comparable {

    String getNickName();
    Integer getPoints();
    void setPoints(int points);
    LeagueName getLeagueName();
    CountryName getCountryName();

    @Override
    int compareTo(Object o);
}
