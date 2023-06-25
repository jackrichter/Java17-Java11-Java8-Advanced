package org.advanced.java.security;

import java.util.ArrayList;

public class ImmutableClassExample {
    public static void main(String[] args) {
        ArrayList<String> employees = new ArrayList<>();
        employees.add("Ann");
        employees.add("Liam");
        Department dept = Department.createNewInstance("Argos", "Athlone", 2, employees);
        System.out.println("Created: " + dept);

        String name = dept.getName();
        String address = dept.getAddress();
        int numEmployees = dept.getNumEmployees();
        employees = dept.getEmployees();
        System.out.println("Retrieved: " + name + " " + address + " " + numEmployees + " " + employees);

        // Can what we got back be changed?
        name = "Boots";             // Strings ar immutable so a new Object is created in the background => Ok
        address = "Galway";
        numEmployees = 3;           // Simple primitive, i.e., this is just a copy
        employees.add("Tom");       // We have here a reference, so the object is changed, and Tom is added to employees. Danger!

        System.out.println("Any change?: " + dept);
    }
}

final class Department {            // Cannot be subbed-classed
    private final String name;      // Strings are immutable
    private final String address;
    private final int numEmployees;
    private final ArrayList<String> employees;      // mutable

    private Department(String name, String address, int numEmployees, ArrayList<String> employees) {       // Cannot subclass a class with private constructor
        this.name = name;
        this.address = address;
        this.numEmployees = numEmployees;
        this.employees = new ArrayList<String>(employees);        // Defensive copying. Secure!
//        this.employees = employees;         // Breaking encapsulation!
    }

    // Factory method
    public static Department createNewInstance(String name, String address, int numEmployees, ArrayList<String> employees) {
        return new Department(name, address, numEmployees, employees);
    }

    // Get methods only

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public ArrayList<String> getEmployees() {
        return new ArrayList<String>(employees);        // Defencive copying. Secure!
//        return employees;                             // Breaking encapsulation!
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", numEmployees=" + numEmployees +
                ", employees=" + employees +
                '}';
    }
}
