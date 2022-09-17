package com.example.oop.solid.isp;

public class ISP {
    // 무선충전
    public interface WirelessChargable {
        void wirelessCharge();
    }
    // AR
    public interface ARable {
        void ar();
    }
    // 생체인식
    public interface Biometricsable {
        void biometrics();
    }

    public class SmartPhone {
        // 통화
        public void call(String number) {
            System.out.println(number + " 통화 연결");
        }
        // 문자
        public void message(String number, String text) {
            System.out.println(number + ": " + text);
        }
    }

    public class S20 extends SmartPhone implements WirelessChargable, ARable, Biometricsable {
        @Override
        public void wirelessCharge() {
            System.out.println("무선충전 기능");
        }

        @Override
        public void ar() {
            System.out.println("AR 기능");
        }

        @Override
        public void biometrics() {
            System.out.println("생체인식 기능");
        }
    }

    public class S2 extends SmartPhone {
        @Override
        public void message(String number, String text) {
            System.out.println("In S2");
            super.message(number, text);
        }
    }
}
