package Lesson_3.HomeWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 12.11.16.
 */
public class Shop {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] iceCreamForm = {"стаканчик", "конус"};
    static String[] iceCreamTaste = {"шоколадный", "клубничный", "банановый", "вишнёвый"};
    static Map<String, Integer> drink = new HashMap<>();  //{"кофе", "чай", "кола"};
    static Map<String, Integer> iceCreamTopping = new HashMap<>();

    static {
        drink.put("кофе", 55);
        drink.put("чай", 15);
        drink.put("кола", 25);
        iceCreamTopping.put("орехи", 50);
        iceCreamTopping.put("шоколад", 40);
        iceCreamTopping.put("фрукты", 25);
        iceCreamTopping.put("сироп", 30);
    }

    static String getInput() {

        String s = "";
        try {
            s = br.readLine();
        } catch (IOException e) {
            System.out.println("Ошибка ввода вывод, попробуйте ещё раз!");
            getInput();
        }
        return s;
    }


    public static void main(String[] args) {

        System.out.println("Выберите номер соответствующий типу мороженого:");
        System.out.println("1 - обычное мороженое");
        System.out.println("2 - мороженое \"Сюрприз\"");
        System.out.println("3 - мороженое \"Экстра\"");

        while (true) {
            String input = getInput();

            if (input.equals("1")) {
                //обычное мороженое

                String form = "";
                String taste = "";

                while (true) {

                    System.out.println("Выберите номер соответствующий ёмкости мороженого:");
                    System.out.println("1 - стаканчик");
                    System.out.println("2 - конус");

                    input = getInput();

                    if (input.equals("1")) {
                        form = Shop.iceCreamForm[0];
                        break;

                    } if (input.equals("2")) {
                        form = Shop.iceCreamForm[1];
                        break;
                    }
                }

                while (true) {

                    System.out.println("Выберите номер соответствующий вкусу мороженого:");
                    System.out.println("1 - шоколадный");
                    System.out.println("2 - клубничный");
                    System.out.println("3 - банановый");
                    System.out.println("4 - вишнёвый");

                    input = getInput();

                    if (input.equals("1")) {
                        taste = Shop.iceCreamTaste[0];
                        break;
                    } if (input.equals("2")) {
                        taste = Shop.iceCreamTaste[1];
                        break;
                    } if (input.equals("3")) {
                        taste = Shop.iceCreamTaste[2];
                        break;
                    } if (input.equals("4")) {
                        taste = Shop.iceCreamTaste[3];
                        break;
                    }
                }

                IceCream iceCream = new IceCream(form, taste, 100);
                iceCream.printTotalInfo();

                break;
            }
            if (input.equals("2")) {
                //мороженое Сюрприз

                IceCream surprise = new Surprise("any", "any", 100);
                surprise.printTotalInfo();

                break;
            }
            if (input.equals("3")) {
                /* мороженое Экстра
                * для простоты не будем реализовывать интерактив с консольным вводом
                * как в случае с обычным мороженым, дабы не загромождать код.
                * setDrink(i) - добавляет покупателю напиток
                *          i=1  кофе
                *          i=2  чай
                *          i=3  кола
                */
                Extra extra = new Extra("конус", "вишнёвый", 100);
                extra.setDrink(2);
                extra.printTotalInfo();

                break;
            } else {
                System.out.println("Введите число от 1-го до 3-ёх включителььно");
            }
        }
    }
}
