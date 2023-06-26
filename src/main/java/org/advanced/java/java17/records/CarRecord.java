package org.advanced.java.java17.records;

/**
 * Records are essentially immutable data carriers.
 * We get constructor, toString, equals, hashcode and getters out of the box.
 *
 * @param regNumber component
 * @param owner component
 */
public record CarRecord(String regNumber, String owner) implements I {

//    private int age;                  // Cant have an instance variable. Must be declared as a component above.
    public static final String currentYear = "23";         // Static fields are allowed

    // Canonical custom constructor
//    public CarRecord(String regNumber, String owner) {
//        if (regNumber.length() <= 4) {
//            throw new IllegalArgumentException();
//        }
//        this.regNumber = regNumber;
//        this.owner = owner;
//    }

    // Compact custom constructor
    public CarRecord {
        if (regNumber.length() <= 4) {
            throw new IllegalArgumentException();
        }
    }

    // Non-canonical constructor (delegates to the compact constructor)
    public CarRecord() {
        this("     ", "");
    }

    public boolean insNewCar() {
        return regNumber().substring(0, 2).equals(currentYear);
    }

    public static CarRecord createBlankRecord() {
        return new CarRecord("     ", "");
    }

    @Override
    public String owner() {
        return owner.toUpperCase();
    }
}

interface I {}

//class X extends CarRecord {}