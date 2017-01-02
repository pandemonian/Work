package Lesson_7.HomeWork;

/**
 * Created by Gubanov Pavel on 02.01.17.
 */
public class Spearer extends FightingUnit {

    public Spearer(String name, String squadName) {
        super(name, squadName);
    }

    @Override
    public Warrior clone() {
        return null;
    }
}
