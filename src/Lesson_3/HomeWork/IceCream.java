package Lesson_3.HomeWork;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12.11.16.
 */
public class IceCream {

    String form;
    String taste;
    List<String> topping = new  ArrayList<>();
    int basePrice;

    IceCream(String form, String taste, int basePrice) {
        this.form = form;
        this.taste = taste;
        this.basePrice = basePrice;
    }

    String getForm() {
        return form;
    }

    String getTaste() {
        return taste;
    }

    void getToppingName() {

        try {
            System.out.println(topping.get(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("К данному мороженному не добавлялся топпинг.");
        }
    }

    int getToppingPrice() {
        return 0;
    }

    int getBasePrice() {
        return basePrice;
    }

    int getTotalPrice() {
        return getBasePrice() + getToppingPrice();
    }

    void printTotalInfo() {
        System.out.println("Вы выбрали \"обычное мороженое\"");
        System.out.println("  Ёмкость: " + form);
        System.out.println("  Вкус: " + taste);
        System.out.print("  Добавка: ");
        this.getToppingName();
        System.out.println("  Стоимость добавки: " + getToppingPrice());
        System.out.println("  Итоговая стоимость: " + getTotalPrice());
    }
}
