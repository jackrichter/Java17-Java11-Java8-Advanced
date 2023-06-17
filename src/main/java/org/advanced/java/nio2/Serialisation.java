package org.advanced.java.nio2;

import java.io.*;

public class Serialisation {
    public static void main(String[] args) {
        // Serialise a Book
        try (var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("book.ser")))) {
            Book book = new Book();
            book.setMedium("Print");
            book.setAuthor("Sean Kennedy");
            System.out.println("Before: " + book);
            out.writeObject(book);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialise a Book
        try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("book.ser")))) {
            Book book = (Book) in.readObject();
            System.out.println("After: " + book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class InfoMedium {
    private String theMedium;

    // constructor IS called when deserialising as this class IS NOT Serializable
    InfoMedium(){ // IS called when de-serialising as this class IS NOT Serializable
        theMedium="Unknown";
    }
    public void setMedium(String aMedium){
        theMedium = aMedium;
    }
    public String getMedium(){
        return theMedium;
    }
}

class Book extends InfoMedium implements Serializable {
    private BookMarker p = new BookMarker();
    private String theAuthor;

    // constructor IS NOT called when deserialising as this class IS Serializable
    Book () {
        theAuthor = "Unknown";
    }
    public void setAuthor(String aAuthor){
        theAuthor = aAuthor;
    }
    public String getAuthor(){
        return theAuthor;
    }
    @Override
    public String toString() {
        return getMedium() + "; " + getAuthor() ;
    }
}

class BookMarker implements Serializable{
    private Image l = new Image();
//    private transient Image l = new Image(); // mark as 'transient'. It's a possible fix if class is not Serializable
}

class Image implements Serializable{}
