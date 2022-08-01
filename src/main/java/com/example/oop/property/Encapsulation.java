package com.example.oop.property;

public class Encapsulation {

}

class publicA {
    public void run() {
        B b = new B();
        b.publicMethod();
    }
}

class protectedA extends B {
    public void run() {
        protectedMethod();
    }
}

class defaultA {
    void defaultMethod() {
    }
}

//class privateA extends B {
//    public void run() {
//        B b = new B();
//        b.privateMethod();
//        privateMethod();
//    }
//}

class B {
    public void publicMethod() {
        System.out.println("public 메소드 접근");
    }
    void defaultMethod() {
        System.out.println("default 메소드 접근");
    }
    protected void protectedMethod() {
        System.out.println("protected 메소드 접근");
    }
    private void privateMethod() {
        System.out.println("private 메소드 접근");
    }
}