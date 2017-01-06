package Lesson_7.HomeWork;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Archer extends AbstractWarrior implements Warrior {

    private static final int VIKING_HEALTH = 80;
    private static final int VIKING_DAMAGE = 80;

    Archer(String name, String squadName) {
        super(name, squadName, VIKING_HEALTH, VIKING_DAMAGE);
    }
}