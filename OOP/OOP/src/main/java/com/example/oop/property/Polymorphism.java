package com.example.oop.property;

class Test {
    void test() {
        System.out.println("No parameters");
    }

    void test(int param) {
        System.out.println("int " + param);
    }

    void test(String param) {
        System.out.println("String " + param);
    }

    void test(int param1, int param2) {
        System.out.println("two " + param1 + ", " + param2);
    }
}

public class Polymorphism {
    public static void main(String[] args) {

        Test obj = new Test();

        obj.test();
        obj.test(1);
        obj.test("one");
        obj.test(10, 20);
    }
}
