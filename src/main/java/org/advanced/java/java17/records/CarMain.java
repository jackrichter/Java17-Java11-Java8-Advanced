package org.advanced.java.java17.records;

public class CarMain {
    public static void main(String[] args) {
        // 1. Regular class
        Car car = new Car("231G1234", "Joe Blogs");
        System.out.println(car);
        System.out.print("Regular class: " + car.getOwner() + " " + car.getRegNumber());
        System.out.println();

        // 2. Using a record
        CarRecord carRecord = new CarRecord("231G4321", "Marry Blogs");
        System.out.println(carRecord);
        System.out.print("Record: " + carRecord.owner() + " " + carRecord.regNumber());
        System.out.println();

        // 3. Define an instance method; cannot define an instance field
        System.out.println(carRecord.insNewCar());

        // 4. Define a static field
        System.out.println(CarRecord.currentYear);

        // 5. Custom canonical constructor and a compact constructor

        // 6. Define a static method
        CarRecord blankCar = CarRecord.createBlankRecord();
        System.out.println("blank owner: " + blankCar.owner());
        System.out.println("blank reg number: " + blankCar.regNumber());

        // 7. Non-canonical record constructor must delegate to another constructor
        // 8. Override an accessor method (owner())
        // 9. Cannot define a subtype based on a record because a record is final by default
        // 10. Can implement an interface
        // 11. Cannot extend from another type because a record already extends from class Record in the background
    }
}
