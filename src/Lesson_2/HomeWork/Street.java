package Lesson_2.HomeWork;

import java.util.Random;

/**
 * Created by pablo on 07.11.16.
 */
public class Street {

    static int[][][] streetPotapova;      // улица
    static Random random = new Random();  // экземпляр для генерации случайных чисел
    static int month;                     // количество пройденных месяцев
    static int day;                       // количество пройденных дней
    static int hour;                      // количество пройденных часов
    static int currentBuilding;           // текущий выбранный дом(методом getRandomApartment)
    static int curentApartment;           // текущая выбранная квартира(методом getRandomApartment)

    /**
     * Локальная перменная housingProperty[i], используемая в большинстве методов, отвечают за
     * определённое свойство каждой квартиры.
     * i=0  кол-во жителей проживающих в квартире
     * i=1  кол-во человек в квартире на текущий момент времени
     * i=2  ежемесячный семейный доход семьи в квартире
     * i=3  сумма у семьи в наличии
     * i=4  ежемесячная квартплата
     * i=5  время(час) ухода на работу
     * i=6  время(час) возвращения домой
     *
     *
     * P.S. Жена предложила реализовать модель кошелев - проекта. Семьи молодые => смертности пока нет
     * Заказ еды доставляется строго раз в месяц, т.к. семьи небогатые )))
     */

    //создаём дома с квартирами
    static void createHouses() {

        //создаём случайное количество домов на улице(5-8)
        streetPotapova = new int[random.nextInt(4) + 5][][];

        //создаём случайное количество квартир в доме(80-180)
        for (int i = 0; i < streetPotapova.length; i++) {
            streetPotapova[i] = new int[random.nextInt(101) + 80][];
        }
    }

    //создаём характеристики квартиры(кол-во жителей в квартире, семейный бюджет, квартплату и т.д.)
    static void createApartmentProperty() {
        for (int i = 0; i < streetPotapova.length; i++) {
            for (int j = 0; j < streetPotapova[i].length;  j++) {

                //задаём кол-во характеристик
                streetPotapova[i][j] = new int[7];

                //задаём кол-во жителей проживающих в квартире
                streetPotapova[i][j][0] = random.nextInt(6);

                //задаём кол-во человек в квартире на текущий момент времени
                //по-умолчанию(при создании квартиры) столько же, сколько и проживает
                streetPotapova[i][j][1] = streetPotapova[i][j][0];

                // ежемесячный семейный доход -- streetPotapova[i][j][2]
                // рассчитывается на основании кол-ва жильцов
                //  0 жильцов  --> нулевой доход
                // > 2 жильцов --> от 20 т.р. и выше
                // 1,2 жильца  --> от 10 т.р. и выше
                if (streetPotapova[i][j][0] == 0) {
                    streetPotapova[i][j][2] = 0;
                } else if (streetPotapova[i][j][0] > 2) {
                    streetPotapova[i][j][2] = random.nextInt(25000) + 20000;
                } else {
                    streetPotapova[i][j][2] = random.nextInt(15000) + 10000;
                }

                // сумма в наличии(на момент создания)
                streetPotapova[i][j][3] = streetPotapova[i][j][2];

                //квартплата
                streetPotapova[i][j][4] = random.nextInt(6000) + 5000;

                //время(час) ухода на работу
                streetPotapova[i][j][5] = random.nextInt(1) + 7;

                //время(час) возвращения домой
                streetPotapova[i][j][6] = random.nextInt(2) + 18;
            }
        }
    }

    //вывод пройденного времени
    static void calculatePassTime(int day, int hour) {

        System.out.println("-------------");
        System.out.println(" Прошло:");
        System.out.println("   месяца: "+ month);
        System.out.println("   дней: "+ day);
        System.out.println("   часов: " + hour);
        System.out.println("-------------");
    }

    //уход на работу, возвращение домой.
    static void workingJob() {
        int [] housingProperty = getRandomHousing();
        if (housingProperty[1] == 0)  return; //если никого нет, никто не выходит на работу

        if (housingProperty[0] > 0) {

            if (hour == housingProperty[5]) {
                housingProperty[1] -= 1;
                System.out.println("Житель проживающий по адресу: ул. Потапова д. " +
                        (currentBuilding + 1) + ", кв. " + (curentApartment + 1) + " ушёл на работу");
            } else if (hour == housingProperty[6]) {
                housingProperty[1] += 1;
                System.out.println("Житель проживающий по адресу: ул. Потапова д. " +
                        (currentBuilding + 1) + ", кв. " + (curentApartment + 1) + " вернулся с работы");
            }
        }
    }

    static void gettingSalary() {
        int [] housingProperty = getRandomHousing();

        if (housingProperty[0] == 0)    return;
        else {
            housingProperty[3] += housingProperty[2];
            System.out.println("Жилец проживающий по адресу: ул. Потапова д. " +
                    (currentBuilding + 1) + ", кв. " + (curentApartment + 1) + " сегодня получил " +
                    "заработанную плату.");
        }
    }

    //оплата коммунальных услуг
    static void payingRent() {
        int [] housingProperty = getRandomHousing();
        if (day == 25) {
            if (housingProperty[3] + 6000 > housingProperty[4]) {
                System.out.println("Квартиросъёмщик проживающий по адресу: ул. Потапова д. " +
                        (currentBuilding + 1) + ", кв. " + (curentApartment + 1) + " оплатил счета");
            } else {
                System.out.println("За квартиросъёмщиком проживающему по адресу: ул. Потапова д. " +
                        (currentBuilding + 1) + ", кв. " + (curentApartment + 1) + " числится долг");
            }
        }
    }

    //смерть человека
    static void deathHuman() {
        int[] housingProperty = getRandomHousing();
        while (true) {
            if (housingProperty[0] == 0) {
                housingProperty = getRandomHousing();
                continue;
            } else {
                housingProperty[0] -= 1;
                System.out.println("Жильцам по адресу: ул. Потапова д. " +
                        (currentBuilding + 1) + ", кв. " + (curentApartment + 1) +
                        " приносим соболезнования в связи с утратой!");
                break;
            }
        }
    }

    //рождение человека
    static void bornHuman() {
        int[] housingProperty = getRandomHousing();
        if (housingProperty[0] >= 2) {
            housingProperty[0] += 1;
            System.out.println("Жильцов по адресу: ул. Потапова д. " +
                    (currentBuilding + 1) + ", кв. " + (curentApartment + 1) +
                    " поздравляем с пополнением!!!");
        }
    }

    //выселение жителя
    static void outHouse() {
        int[] housingProperty = getRandomHousing();
        if (housingProperty[0] == 0) {
            System.out.println("По адресу: ул. Потапова д. " + (currentBuilding + 1) +
                    ", кв. " + (curentApartment + 1) + " никто не проживает - выселять некого!");
        } else {
            housingProperty[0] -= 1;
            System.out.println("Из квартиры № " + (curentApartment + 1) + " дома № " +
                    (currentBuilding + 1) + " ул. Потапова выселили жильца!");
        }
    }

    //вселение жителя
    static void inHouse() {
        int[] housingProperty = getRandomHousing();
        housingProperty[0] += 1;
        System.out.println("В квартиру № " + (curentApartment + 1) + " дома № " +
                (currentBuilding + 1) + " ул. Потапова вселили нового жильца!");
    }

    //выбор случайной квартиры в случайном доме
    static int[] getRandomHousing() {
        currentBuilding = random.nextInt(streetPotapova.length);
        curentApartment = random.nextInt(streetPotapova[currentBuilding].length);

        return streetPotapova[currentBuilding][curentApartment];
    }

    //заказ пиццы на дом, проверка суммы у заказчика(есть ли на сам заказ и на коммуналку)
    static void pizzaRequest() {
       /*
       * housingProperty -  случайная квартира
       * housingProperty[2] - сумма денег в наличии у текущей квартиры
       * housingProperty[3] - стоимость ежемесячной квартплаты у текущей квартиры
       */
        int[] housingProperty = getRandomHousing();

        //случайная цена пиццы(от 1000 до 2500)
        int pizzaCost = random.nextInt(1500) + 5000;

        // есть ли кто-то дома, есть ли у жильцов деньги
        if (housingProperty[1] == 0) {
            return;
            //System.out.println("На данный момент в квартире нет никого, либо никто не проживает.");
        } else if (housingProperty[2] > pizzaCost) {
            housingProperty[2] -= pizzaCost;
            System.out.println("Заказ пиццы доставлен по адресу: ул. Потапова д. " + (currentBuilding + 1) +
                    ", кв. " + (curentApartment + 1));
        } else {
            System.out.println("У клиента не хватает средств для оформления заказа.");
        }
    }

    static void generalMethod() {

        //весьма мала вероятность, что именно 25 числа месяца выпадет
        //рандомное число отвечающее за вывод метода
        //явно вставил его здесь для наглядности
        if (day == 25)  payingRent();

        int chance = random.nextInt(100);

        if ((chance >= 0) && (chance <= 8))    pizzaRequest();
        else if ((chance >= 9) && (chance <= 89 ))    workingJob();
        else if ((chance >= 90) && (chance <= 92 ))    gettingSalary();
        else if ((chance == 93) || (chance == 94))    inHouse();
        else if ((chance == 95) || (chance == 96))    outHouse();
        else if ((chance == 97) || (chance == 98))    bornHuman();
        else if (chance == 99)    deathHuman();
    }

    public static void main(String[] args) {

        createHouses();
        createApartmentProperty();

        //проход по случайным квартирам за два месяца
        for (month = 1; month <= 2; month++) {
            for (day = 1; day <= random.nextInt(2) + 30; day++) {
                for (hour = 1; hour <= 24; hour++) {

                    generalMethod();

                    if (random.nextInt(50) == 24)
                        calculatePassTime(day, hour);
                }
            }
        }
    }
}
