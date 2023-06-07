package org.advanced.java.io;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class ReadingWritingExamples {
    public static void main(String[] args) {
//        copyTextFile(false);
//      copyTextFile(true);
//        copyBinaryFile(false);
        copyBinaryFile(true);
    }
    public static void copyTextFile(boolean buffering) {
        // Absolute Path
//        File src = new File(
//                "D:\\Udemy4\\Java-17-Java-11-Advanced-Java-8\\workspace\\Java17Java11AndJava8\\src\\main\\java\\org\\advanced\\java\\io\\ReadingWritingExamples.java");
//        File dest = new File(
//                "D:\\Udemy4\\Java-17-Java-11-Advanced-Java-8\\workspace\\Java17Java11AndJava8\\src\\main\\java\\org\\advanced\\java\\io\\ReadingWritingExamples2.java");

        // Alternative with Relative Path
        File src = new File("src/main/java/org/advanced/java/io/ReadingWritingExamples.java");
        File dest = new File("src/temp/CopyOfReadingWritingExamples.java");

        // in-built close() with try-with-resources
        try (var reader = new BufferedReader(new FileReader(src));
                var writer = new BufferedWriter(new FileWriter(dest))) {

            if (buffering) {
                String str = null;

                // readLine() is specific to BufferedReader
                while ((str = reader.readLine()) != null) {
                    writer.write(str);
                    writer.newLine();           // readLine() strips out the end of line character
                }
            } else {
                // no buffering i.e. read one byte at a time; an int is used because
                // Java uses -1 to signal the end of the stream
                int b;
                while ((b = reader.read()) != -1) {
                    writer.write(b);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void copyBinaryFile(boolean buffering){
        // 1. Using / instead of \
        // 2. Using relative path instead of absolute path
        System.out.println("Working Directory = " + System.getProperty("user.dir"));// C:\Users\skennedy\Documents\NetbeansProjects\Udemy   
        File src = new File ("./target/classes/org/advanced/java/io/ReadingWritingExamples.class");
        File dest = new File ("src/temp/ReadingWritingExamples2.class");

        // in-built close() with try-with-resourcse
        try(var in = new BufferedInputStream(new FileInputStream(src));
            var out = new BufferedOutputStream(new FileOutputStream(dest))){

            if(buffering){
                var buffer = new byte[1024];
                int numBytesRead=0;
                while((numBytesRead = in.read(buffer)) > 0 ){
                    // As the file is unlikely to be an exact multiple of 1024 bytes, 'numBytesRead' helps
                    // us to write out exactly the number of extra bytes above that multiple
                    // e.g. 2058 = 1024 + 1024 + 10
                    // write(byte[], int, int) is in FileOutputStream and BufferedOutputStream
                    out.write(buffer, 0, numBytesRead);
                    out.flush();
                }
            }else {
                // no buffering i.e. read one byte at a time; an int is used because
                // Java uses -1 to signal the end of the stream
                int b;
                while((b = in.read()) != -1){
                    out.write(b);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
