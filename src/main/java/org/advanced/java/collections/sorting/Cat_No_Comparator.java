package org.advanced.java.collections.sorting;

public class Cat_No_Comparator {
    private String name;
    private int age;

    public Cat_No_Comparator(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Cat{" + "name=" + name + ", age=" + age + '}';
    }
    
}
