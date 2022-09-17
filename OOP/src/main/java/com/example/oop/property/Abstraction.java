package com.example.oop.property;

abstract class Car {
    public abstract void move();
}

class SuperCar extends Car{
    public void move() {
        System.out.println("빠르게 달린다");
    }
}

class ElectricCar extends Car{
    public void move() {
        System.out.println("전기로 달린다");
    }
}

public class Abstraction {
    public static void main(String[] args) {
        SuperCar sc = new SuperCar();
        ElectricCar ec = new ElectricCar();

        sc.move();
        ec.move();
    }
}
