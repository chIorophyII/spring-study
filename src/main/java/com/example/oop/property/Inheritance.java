package com.example.oop.property;

import lombok.AllArgsConstructor;
import java.util.Date;


@AllArgsConstructor
abstract class Computer {
    private final String OS;

    public void startUp() {
        System.out.println(OS + " - started at " + new Date().toString());
    }

    public void shutDown() {
        System.out.println(OS + " - shutdown at " + new Date().toString());
    }

    abstract public void run();
}

class Asus extends Computer {
    public Asus(String os) {
        super(os);
    }

    @Override
    public void run() {
        System.out.println("ASUS 작업 수행");
    }
}

class Dell extends Computer {
    public Dell(String os) {
        super(os);
    }

    @Override
    public void startUp() {
        super.startUp();
        System.out.println("시스템 안정화 수행");
    }

    @Override
    public void shutDown() {
        System.out.println("시스템 프로세스 정리 수행");
        super.shutDown();
    }

    @Override
    public void run() {
        System.out.println("DELL 작업 수행");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Dell dell = new Dell("Windows 10 Pro");
        Asus asus = new Asus("Ubuntu 21.04");

        dell.startUp();
        dell.run();
        dell.shutDown();

        System.out.println();

        asus.startUp();
        asus.run();
        asus.shutDown();
    }
}