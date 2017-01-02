package Lesson_7.HomeWork;

/**
 * Created by Gubanov Pavel on 02.01.17.
 */
abstract class FightingUnit {

    private int health = 120;
    private int damage = 50;
    private String squadName;
    private String name;

    FightingUnit(String name, String squadName) {
        this.name = name;
        this.squadName = squadName;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return getClass().getSimpleName();
    }

    public int attack() {
        return damage;
    }

    public void takeDamage(int damage) {
        //минимальное здоровье - нуль, но никак не отрицательное!!!!
        if ((damage < health) || (damage == health))  health -= damage;
        else health -= health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String toString() {
        return "имя: " + name + ", класс: " + getClass().getSimpleName() + ", отряд: " + squadName;
    }

    public abstract Warrior clone();
}
