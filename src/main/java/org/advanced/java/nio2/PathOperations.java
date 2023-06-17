package org.advanced.java.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;

// current directory: C:\Users\skennedy\Documents\NetbeansProjects\Udemy
public class PathOperations {
    public static void main(String[] args) {
//        pathInfo(Paths.get("D:\\Udemy4\\Java-17-Java-11-Advanced-Java-8\\workspace\\Java17Java11AndJava8\\pom.xml"));
        pathInfo(Path.of("abc\\def\\ghi\\jkl"));
    }
    public static void pathInfo(Path path){
        System.out.println("toString: "+path);      // D:\Udemy4\Java-17-Java-11-Advanced-Java-8\workspace\Java17Java11AndJava8\pom.xml
        System.out.println("getNameCount: " + path.getNameCount()); // 5
        for(int i=0; i<path.getNameCount(); i++){
            // getName(0): Users   => root is NOT a name element (see Path.of("/").getName(0); on line 22)
            System.out.println("getName("+i+"): "+path.getName(i));
        }
        System.out.println("getFileName: " + path.getFileName());   // pom.xml
        System.out.println("getParent: " + path.getParent());       // D:\Udemy4\Java-17-Java-11-Advanced-Java-8\workspace\Java17Java11AndJava8
        System.out.println("getRoot: " + path.getRoot());           // D:\
//        System.out.println("exception: " + Path.of("/").getName(0));      // java.lang.IllegalArgumentException

        System.out.println("subpath(0,3): "+path.subpath(0, 3));    // Udemy4\Java-17-Java-11-Advanced-Java-8\workspace
        System.out.println("subpath(1,4): "+path.subpath(1, 4));    // Java-17-Java-11-Advanced-Java-8\workspace\Java17Java11AndJava8
        System.out.println("subpath(2,3): "+path.subpath(2, 3));    // workspace
        
        System.out.println("isAbsolute: "+path.isAbsolute());       // (first call)true, (second call)false
    }
}
