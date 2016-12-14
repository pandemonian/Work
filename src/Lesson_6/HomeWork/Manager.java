package Lesson_6.HomeWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubanov Pavel on 08.12.16.
 */
class Manager implements LeagueManager {

    private Championship currentChampionship;
    private League currentLeague;
    private String nickname;

    Manager(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void addPlayer(SoccerPlayer player) {

        if (player.getLeagueName().equals(LeagueName.ПРЕМЬЕР_ЛИГА)) {
            currentLeague = currentChampionship.getAllLeagues().get(0);
        }
        if (player.getLeagueName().equals(LeagueName.ПЕРВАЯ_ЛИГА)) {
            currentLeague = currentChampionship.getAllLeagues().get(1);
        }
        if (player.getLeagueName().equals(LeagueName.ВТОРАЯ_ЛИГА)) {
            currentLeague = currentChampionship.getAllLeagues().get(2);
        }
        currentLeague.getSoccerTeam().add(player);
    }

    @Override
    public void removePlayer(SoccerPlayer player) {

        for (League league: currentChampionship.getAllLeagues()) {
            for (int i = 0; i < league.getSoccerTeam().size(); i++) {
                if (player == league.getSoccerTeam().get(i))  league.getSoccerTeam().remove(i);
            }
        }
    }

    @Override
    public SoccerPlayer getPlayer(String name) {

        for (League league: currentChampionship.getAllLeagues()) {
            for (int player = 0; player < league.getSoccerTeam().size(); player++) {
                if (name.equals(league.getSoccerTeam().get(player).getNickName())) {
                    return league.getSoccerTeam().get(player);
                }
            }
        }
        try {
            throw new PlayerNotFoundException();
        } catch (PlayerNotFoundException e) {
            e.getNoNicknamePlayerMsg();
            return null;
        }

    }

    @Override
    public List<SoccerPlayer> getAllPlayers() {

        List<SoccerPlayer> allPlayer = new ArrayList<>();
        for (League league: currentChampionship.getAllLeagues()) {
            allPlayer.addAll(league.getSoccerTeam());
        }
        return allPlayer;
    }

    @Override
    public List<SoccerPlayer> getPlayers(LeagueName leagueName) {

        List<SoccerPlayer> allPlayer = new ArrayList<>();
        for (League league: currentChampionship.getAllLeagues()) {
            for (SoccerPlayer player: league.getSoccerTeam()) {
                if (player.getLeagueName().equals(leagueName))  allPlayer.add(player);
            }
        }
        return allPlayer;
    }

    @Override
    public List<SoccerPlayer> getPlayers(CountryName countryName) {

        List<SoccerPlayer> allPlayer = new ArrayList<>();
        for (League league: currentChampionship.getAllLeagues()) {
            for (SoccerPlayer player: league.getSoccerTeam()) {
                if (player.getCountryName().equals(countryName))  allPlayer.add(player);
            }
        }
        return allPlayer;
    }

    @Override
    public void addPoints(String name, int points) {

        for (League league : currentChampionship.getAllLeagues()) {
            for (int player = 0; player < league.getSoccerTeam().size(); player++) {
                if (name.equals(league.getSoccerTeam().get(player).getNickName())) {
                    SoccerPlayer tempPlayer = league.getSoccerTeam().get(player);

                    if (tempPlayer.getPoints() + points > 10) {
                        System.out.println("Текущий рейтинг у игрока равен " + tempPlayer.getPoints() + ", вы хотите " +
                                "добавить " + points + " очков. Но рейтинг не может быть более 10 баллов, поэтому " +
                                "игроку присваевается максимальное значение равное 10!");
                        tempPlayer.setPoints(10);
                    } else  tempPlayer.setPoints(points + tempPlayer.getPoints());
                }
            }
        }
    }

    @Override
    public void manage(Championship champ) {
        currentChampionship = champ;
    }
}
