package org.advanced.java.nio2;

import java.io.*;

public class CustomSerialisation {
    public static void main(String[] args) {
        // Serialize ImportantBook
        try (var out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("important_book.ser")))) {
            ImportantBook book = new ImportantBook("David Baldacci", "True Blue", "978-1-5098-5972-6", 57);
            System.out.println("Before: " + book);
            out.writeObject(book);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Deserialize ImportantBook
        try (var in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("important_book.ser")))) {
            ImportantBook book = (ImportantBook) in.readObject();
            System.out.println("After: " + book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ImportantBook implements Serializable {
    private static final long serialVersionUID = 1234567L;
    private String author;
    private String title;
    private String isbn;
    private int authorAge;          // effectively 'transient'

    // The ObjectStreamField[] states which fields are to be serialized.
    // Any field left out becomes effectively transient.
    private static final ObjectStreamField[] fieldsToSerialize = {
            new ObjectStreamField("author", String.class),
            new ObjectStreamField("title", String.class),
            new ObjectStreamField("isbn", String.class)
    };

    public ImportantBook(String author, String title, String isbn, int authorAge) {
        this.author = author;
        this.title = title;
        this.isbn = isbn;
        this.authorAge = authorAge;
    }

    @Override
    public String toString() {
        return author + "; " + title + "; " + isbn + "; " + authorAge;
    }

    // Custom writeObject. This custom method is called by out.writeObject(book)
    private void writeObject(ObjectOutputStream oos) throws IOException {
        // If security is a concern, we would encrypt the data first
        ObjectOutputStream.PutField field = oos.putFields();
        field.put("author", author);
        field.put("title", title);
        field.put("isbn", isbn);
        oos.writeFields();
    }

    // Custom readObject. This custom method is called by in.readObject()
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        // If data had been encrypted, we decrypt first
        // If no values found for the fields, they get null value
        ObjectInputStream.GetField fields = ois.readFields();
        author = (String) fields.get("author", null);
        title = (String) fields.get("title", null);
        isbn = (String) fields.get("isbn", null);
    }
}
