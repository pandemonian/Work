package Lesson_6.ClassWork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gubanov Pavel on 01.12.16.
 */
public class Run {

    static List<Pet> list1 = new ArrayList();
    static List<Pet> list2 = new LinkedList();

    static {
        list1.add(new Dog());
        list1.add(new Cat());

        list2.add(new Dog());
        list2.add(new Cat());
    }

    static <T> void genericPrint(List<T> list) {
        for (T it: list) {
            System.out.println(it);  //тут переменная-итератор не видит метод call()
        }
    }

    static void simplePrint(List<Pet> list) {
        for (Pet it: list) {
            it.call();
        }
    }


    public static void main(String[] args) {

        genericPrint(list2);
        genericPrint(list2);

        System.out.println("-----------");

        simplePrint(list1);
        simplePrint(list2);




    }
}
