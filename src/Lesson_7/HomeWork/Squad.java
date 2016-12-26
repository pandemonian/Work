package Lesson_7.HomeWork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class Squad implements Cloneable {

    static Random random = new Random(); //убрать static
    private String name;
    List<Warrior> team; //нарушение инкапсуляции. другие классы не  должны как угодно менять состав отряда

    Squad(String name, List<Warrior> team) {
        this.name = name;
        this.team = team;
    }

    String getName() {
        return name;
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
    protected Squad clone() throws CloneNotSupportedException { //свой код надо проверять перед сдачей
        Squad obj = (Squad)super.clone();
        obj.name = name;
        obj.team = new ArrayList<>();

        for (int i = 0; i < obj.team.size(); i++) { //obj.team.size() равен нулю
            obj.team.set(i ,team.get(i).clone()); //set в пустой список выдаст ошибку
        }
        return obj;
    }
}
