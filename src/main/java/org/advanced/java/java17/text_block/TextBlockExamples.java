package org.advanced.java.java17.text_block;

public class TextBlockExamples {
    public static void main(String[] args) {
        String sName = "Jack Richter";
        String tbName = """
                Jack Richter""";
        System.out.println(sName.equals(tbName));
        System.out.println(sName == tbName);
        System.out.println(tbName.substring(5));

        String tb3 = """
                abc
                """;
        System.out.println(tb3);

        String sQuote = "Hamlet: \"There is nothing either god or bad, but thinking makes it so\"";
        String tbQuote = """
                Hamlet: "There is nothing either god or bad, but thinking makes it so"
                """;
        System.out.println(sQuote);
        System.out.println(tbQuote);

        String sBookTitle1 = "Java\nMemory\nManagement\n";
        String tbBookTitle1 = """
                Java
                Memory
                Management
                """;                    // new line at the end. The same as above
        System.out.println(sBookTitle1);
        System.out.println(123);
        System.out.println(tbBookTitle1);
        System.out.println(123);

        String sBookTitle2 = "Java\nMemory\nManagement";
        String tbBookTitle2 = """
                Java
                Memory
                Management""";      // No new line at the end
        System.out.println(sBookTitle2);
        System.out.println(123);
        System.out.println(tbBookTitle2);
        System.out.println(123);

        // It is particularly suitable for writing JSON
        String json = """
                {
                    "name": "Jane Doe",
                    "age": 23,
                    "address": "Main Street, New York"
                }""";
        System.out.println(json);
    }
}
