package Lesson_7.HomeWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Squad implements Cloneable {

    private String name;
    private List<Warrior> team;

    Squad(String name, List<Warrior> team) {
        this.name = name;
        this.team = team;
    }

    String getName() {
        return name;
    }

    Warrior getTeamWarrior(int index) {
        return team.get(index);
    }

    int getTeamSize() {
        return team.size();
    }

    boolean hasAliveWarriors() {
        for (Warrior warrior: team) {
            if (warrior.isAlive()) return true;
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
        obj.team = new ArrayList<>();

        for (int i = 0; i < obj.team.size(); i++) {
            obj.team.set(i ,team.get(i).clone());
        }
        return obj;
    }
}
