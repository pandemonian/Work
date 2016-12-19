package Lesson_7.HomeWork;

import java.util.Random;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Squad implements Cloneable {

    static Random random = new Random();
    private String name;
    Warrior[] team;

    Squad(String name, Warrior[] team) {
        this.name = name;

        this.team = new Warrior[team.length];

        for (int i = 0; i < this.team.length; i++) {
            try {
                this.team[i] = team[i].clone();
            } catch (CloneNotSupportedException e) {
                System.out.println("Клонирование не удалось!!!");
            }
        }
    }

    String getName() {
        return name;
    }

    boolean hasAliveWarriors() {
        for (Warrior warrior: team) {
            if (warrior.isAlive()) return true;  //тут нелья написать наподобие return warrior.isAlive()
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    protected Squad clone() throws CloneNotSupportedException {
        Squad obj = (Squad)super.clone();
        obj.name = name;
        obj.team = new Warrior[team.length];

        for (int i = 0; i < obj.team.length; i++) {
            obj.team[i] = team[i].clone();
        }
        return obj;
    }
}
