package Lesson_7.HomeWork;

import java.util.Random;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Battle {
    private static Random random = new Random();

    void striking(Squad crew1, Squad crew2) {

        int indexWarriorTeam1;
        int indexWarriorTeam2;

        //если у обеих отрядов есть живые, то выбираем их рандомно
        if ((crew1.hasAliveWarriors()) && (crew2.hasAliveWarriors())) {

            indexWarriorTeam1 = getRandomIndexWarriorTeam(crew1);
            indexWarriorTeam2 = getRandomIndexWarriorTeam(crew2);

            crew1.getTeamWarrior(indexWarriorTeam1).attackingUnit(crew2.getTeamWarrior(indexWarriorTeam2));

            Gui.setLog(crew1.getTeamWarrior(indexWarriorTeam1).getClass().getSimpleName(), " ", crew1.getTeamWarrior(indexWarriorTeam1).getName(),
                    " из отряда \"", crew1.getName(), "\" нанёс ", crew2.getTeamWarrior(indexWarriorTeam2).getClass().getSimpleName(),
                    "`у ", crew2.getTeamWarrior(indexWarriorTeam2).getName(), " из отряда \"", crew2.getName(), "\" ",
                    String.valueOf(crew1.getTeamWarrior(indexWarriorTeam1).getDamage()), " единиц урона!");
        }
    }

    //возвращает true если только в одном из отрядов не осталось живых бойцов.
    boolean isAnyLoose(Squad crew1, Squad crew2) {
        return (!crew1.hasAliveWarriors()) ^ (!crew2.hasAliveWarriors());
    }

    private int getRandomIndexWarriorTeam(Squad crew) {
        while (true) {
            int indexWarriorTeam = random.nextInt(crew.getTeamSize());
            if (crew.getTeamWarrior(indexWarriorTeam).isAlive()) {
                return indexWarriorTeam;
            }
        }
    }

    void winnerIs(Squad crew1, Squad crew2) {
        String winner;
        String looser;

        if (crew1.hasAliveWarriors()) {
            winner = crew1.getName();
            looser = crew2.getName();
        } else {
            winner = crew2.getName();
            looser = crew1.getName();
        }

        Gui.setLog("Отряд \"", winner, "\" победил, ", "уничтожив отряд \"", looser, "\"");
    }
}
