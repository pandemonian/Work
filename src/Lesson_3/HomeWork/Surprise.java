package Lesson_3.HomeWork;

import java.util.Map;
import java.util.Random;
/**
 * Created by Admin on 12.11.16.
 */
public class Surprise extends IceCream {

    int toppingPrice;
    Random random = new Random();

    Surprise(String form, String taste, int basePrice) {
        super(form, taste, basePrice);
        this.form = getRandomForm();
        this.taste = getRandomTaste();
        this.topping.add(getRandomToppingName());
        this.topping.add(getRandomToppingName());
    }

    String getRandomForm() {
        return Shop.iceCreamForm[random.nextInt(2)];
    }

    String getRandomTaste() {
        return Shop.iceCreamTaste[random.nextInt(4)];
    }

    String getRandomToppingName() {
        while (true) {
            for (Map.Entry<String, Integer> map : Shop.iceCreamTopping.entrySet()) {

                if (10 == random.nextInt(20))    return map.getKey();
            }
        }
    }

    @Override
    void getToppingName() {
        for (int i = 0; i < topping.size(); i++) {
            System.out.print(topping.get(i) + " ");
        }
        System.out.println("");
    }

    @Override
    int getToppingPrice() {
        int sum = 0;
        String toppingName;

        for (int i = 0; i < topping.size(); i++) {
            toppingName = topping.get(i);
            sum += Shop.iceCreamTopping.get(toppingName);
        }
        return sum;
    }

    @Override
    int getBasePrice() {
        return basePrice;
    }

    @Override
    int getTotalPrice() {
       return getBasePrice() + getToppingPrice();
    }

    @Override
    void printTotalInfo() {
        System.out.println("Вы выбрали мороженое \"Сюрприз\"");
        System.out.println("  Ёмкость: " + form);
        System.out.println("  Вкус: " + taste);
        System.out.print("  Добавки: ");
        getToppingName();
        System.out.println("  Стоимость добавок: " + getToppingPrice());
        System.out.println("  Итоговая стоимость: " + getTotalPrice());
    }
}
