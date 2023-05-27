package org.avdvanced.java.collections;

public class Book {
    private String title;           // String implements Comparable
    private Double price;           // Double implements Comparable (the primitive does not)

    public Book(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
