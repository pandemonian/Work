package Lesson_4.HomeWork;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Viking implements Warrior {

    private int health = 120;
    private int damage = 50;
    private String squadName;
    private String name;

    Viking(String name, String squadName) {
        this.name = name;
        this.squadName = squadName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return getClass().getSimpleName();
    }

    @Override
    public int attack() {
        return damage;
    }

    @Override
    public void takeDamage(int damage) {
        //минимальное здоровье - нуль, но никак не отрицательное!!!!
        if ((damage < health) || (damage == health))  health -= damage;
        else health -= health;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String toString() {
        return "имя: " + name + ", класс: " + getClass().getSimpleName() + ", отряд: " + squadName;
    }

    @Override
    public Warrior clone() throws CloneNotSupportedException {
        Viking obj = (Viking)super.clone();
        obj.health = health;
        obj.damage = damage;
        obj.squadName = squadName;
        obj.name = name;

        return obj;
    }
}
