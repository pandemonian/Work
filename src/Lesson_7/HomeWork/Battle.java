package Lesson_7.HomeWork;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Battle {

    void striking(Squad crew1, Squad crew2) {

        int iTeam1; //неинформативные имена. почему package local?
        int iTeam2;

        //если у обеих отрядов есть живые, то выбираем их рандомно
        if (crew1.hasAliveWarriors() && crew2.hasAliveWarriors()) {

            while (true) {
                iTeam1 = Squad.random.nextInt(crew1.team.size()); //почему выбор бойцов реализован в битве, а рандом в отряде?
                if (crew1.team.get(iTeam1).isAlive()) break; //операция пишется на отдельной строке от условия
            }

            while (true) {
                iTeam2 = Squad.random.nextInt(crew2.team.size()); //дублирование кода
                if (crew2.team.get(iTeam2).isAlive()) break;
            }

            crew1.team.get(iTeam1).attackingUnit(crew2.team.get(iTeam2));

            Gui.setLog(crew1.team.get(iTeam1).getClass().getSimpleName(), " ", crew1.team.get(iTeam1).getName(),
                    " из отряда \"", crew1.getName(), "\" нанёс ", crew2.team.get(iTeam2).getClass().getSimpleName(),
                    "`у ", crew2.team.get(iTeam2).getName(), " из отряда \"", crew2.getName(), "\" ",
                    String.valueOf(crew1.team.get(iTeam1).getDamage()), " единиц урона!");
        }
    }

    //возвращает true если только в одном из отрядов не осталось живых бойцов.
    boolean isAnyLoose(Squad crew1, Squad crew2) {
        return (!crew1.hasAliveWarriors()) ^ (!crew2.hasAliveWarriors()); //тут просто A OR B. к чему эти извращения?
    }

    void winnerIs(Squad crew1, Squad crew2) {
        if (crew1.hasAliveWarriors())
            Gui.setLog("Отряд \"", crew1.getName(), "\" победил, ", "уничтожив отряд \"", crew2.getName(), "\"");

        else  Gui.setLog("Отряд \"", crew2.getName(), "\" победил, ", "уничтожив отряд \"", crew1.getName(), "\""); //дублирование
    }
}
