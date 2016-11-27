package Lesson_3.ClassWork;

/**
 * Created by Admin on 19.11.16.
 */
public abstract class Car {

    String marka;
    String gosnomer;

    @Override
    public String toString() {

        return "Marka - " + this.marka + "; Gosnomer - " + this.gosnomer;
    }

    public static void main(String[] args) {

        CarParking parkovka = new CarParking();
        parkovka.array[0] = new PassengerCar("Ford", "H362NV");
        parkovka.array[1] = new PassengerCar("Suzuki", "G578JE");
        parkovka.array[2] = new TruckCar("Man", "D856FV");
        parkovka.array[3] = new TruckCar("Kamaz", "G946MF");

        for (int i = 0; i < parkovka.array.length; i++) {
            if (parkovka.array[i] instanceof PassengerCar) {
                CarParking.arenda += 1000;
            } else if (parkovka.array[i] instanceof TruckCar) {
                CarParking.arenda += 1500;
            }
            System.out.println(parkovka.array[i]);
        }
        System.out.println("Общая аренда - " + CarParking.arenda + " рублей");
    }
}
