package com.example.oop.solid.isp;

public class NotISP {
    abstract public class SmartPhone {
        // 통화
        public void call(String number) {
            System.out.println(number + " 통화 연결");
        }
        // 문자
        public void message(String number, String text) {
            System.out.println(number + ": " + text);
        }
        // 무선충전
        public void wirelessCharge() {
            System.out.println("무선 충전");
        }
        // AR
        public void ar() {
            System.out.println("AR 기능");
        }
        // 생체인식
        abstract public void biometrics();
    }

    public class S20 extends SmartPhone {
        @Override
        public void biometrics() {
            System.out.println("S20 생체인식 기능");
        }
    }

    public class S2 extends SmartPhone {

        @Override
        public void wirelessCharge() {
            System.out.println("지원 불가능한 기기");
        }

        @Override
        public void ar() {
            System.out.println("지원 불가능한 기기");
        }

        @Override
        public void biometrics() {
            System.out.println("지원 불가능한 기기");
        }
    }
}
