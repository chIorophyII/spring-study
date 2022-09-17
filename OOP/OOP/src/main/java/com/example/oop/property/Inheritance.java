package com.example.oop.property;

class Student {
    public final String name;

    public Student(String name) {
        this.name = name;
    }

    public void study() {
        System.out.println(name + "가 공부합니다.");
    }
}

class Girl extends Student {
    public Girl(String name) {
        super(name);
    }
    // 재정의
    public void study() {
        System.out.println(name + "가 열심히 공부합니다.");
    }

    public void walk() {
        System.out.println(name + "가 걷습니다.");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        final Girl girl = new Girl("영희");

        girl.study();
        girl.walk();
    }
}
