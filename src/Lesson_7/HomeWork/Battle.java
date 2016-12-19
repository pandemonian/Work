package Lesson_7.HomeWork;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Battle {

    void striking(Squad crew1, Squad crew2) {

        int iTeam1;
        int iTeam2;

        //если у обеих отрядов есть живые, то выбираем их рандомно
        if (crew1.hasAliveWarriors() && crew2.hasAliveWarriors()) {

            while (true) {
                iTeam1 = Squad.random.nextInt(crew1.team.length);
                if (crew1.team[iTeam1].isAlive()) break;
            }

            while (true) {
                iTeam2 = Squad.random.nextInt(crew2.team.length);
                if (crew2.team[iTeam2].isAlive()) break;
            }

            crew1.team[iTeam1].attackingUnit(crew2.team[iTeam2]);

            System.out.println(crew1.team[iTeam1].getClass().getSimpleName() + " "
                    + crew1.team[iTeam1].getName() + " из отряда \"" +
                    crew1 + "\" нанёс " + crew2.team[iTeam2].getClass().getSimpleName() + "`у "
                    + crew2.team[iTeam2].getName() + " из отряда \"" + crew2 + " "
                    + crew1.team[iTeam1].getDamage() + " единиц урона!");
        }
    }

    //возвращает true если только в одном из отрядов не осталось живых бойцов.
    boolean isAnyLoose(Squad crew1, Squad crew2) {
        return (!crew1.hasAliveWarriors()) ^ (!crew2.hasAliveWarriors());
    }

    void winnerIs(Squad crew1, Squad crew2) {
        if (crew1.hasAliveWarriors()) System.out.println("Отряд \"" + crew1.getName() + "\" победил " +
                "уничтожив отряд \"" + crew2.getName() + "\"");

        else System.out.println("Отряд \"" + crew2.getName() + "\" победил " +
                "уничтожив отряд \"" + crew1.getName() + "\"");
    }
}
