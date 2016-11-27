package Lesson_3.HomeWork;

import java.util.Map;

/**
 * Created by Admin on 12.11.16.
 */
public class Extra extends IceCream {

    String drink;

    Extra(String form, String taste, int basePrice) {
        super(form, taste, basePrice);
    }

    void setDrink(int index) {
        switch (index) {
            case 1: drink = "кофе";
                break;
            case 2: drink = "чай";
                break;
            case 3: drink = "кола";
                break;
        }
    }
    
    int getDrinkPrice() {
        int temp = 0;

        for (Map.Entry<String, Integer> map : Shop.drink.entrySet()) {
            if (map.getKey().equals(drink))  temp = map.getValue();
        }
        return temp;
    }

    @Override
    int getBasePrice() {
        return basePrice;
    }

    @Override
    int getToppingPrice() {
        return super.getToppingPrice();
    }

    @Override
    int getTotalPrice() {
        return getBasePrice() + getToppingPrice() + getDrinkPrice();
    }

    @Override
    void printTotalInfo() {
        System.out.println("Вы выбрали мороженое \"Экстра\"");
        System.out.println("  Ёмкость: " + form);
        System.out.println("  Вкус: " + taste);
        System.out.print("  Добавка: ");
        this.getToppingName();
        System.out.println("  Стоимость добавки: " + getToppingPrice());
        System.out.println("  Напиток: " + drink);
        System.out.println("  Итоговая стоимость: " + getTotalPrice());
    }
}
