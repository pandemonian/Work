package Lesson_7.HomeWork;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Viking extends AbstractWarrior implements Warrior {

    private static final int VIKING_HEALTH = 120;
    private static final int VIKING_DAMAGE = 50;

    Viking(String name, String squadName) {
        super(name, squadName, VIKING_HEALTH, VIKING_DAMAGE);
    }
}
