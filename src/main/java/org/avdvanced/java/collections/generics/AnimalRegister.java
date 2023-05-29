package org.avdvanced.java.collections.generics;

import org.avdvanced.java.collections.Cat;

class Register<T, U, V>{
    private T type;
    private U name;
    private V age;
    
    Register(T type, U name, V age){
        this.type = type;
        this.name = name;
        this.age  = age;
    }
    public T getType(){ 
        return type;
    }
    public U getName(){ 
        return name;
    }
    public V getAge(){ 
        return age;
    }
}
public class AnimalRegister {
    public static void main(String[] args) {
        new Register(new DogLocal(), "Shep", 3);
        new Register(new Cat(), "Whiskers", 2);
    }
}



