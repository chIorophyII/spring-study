package com.example.oop.solid.oop;

public class PP {
    // 주스 잔여 개수
    static int Orange_juice = 10;
    static int Apple_juice = 20;

    public static void main(String[] args) {

        int customer_changes = 1000;
        String customer_has = null;

        // 오렌지 주스가 먹고싶군요
        String want_juice = "Orange juice";

        if(want_juice.equals("Orange juice")) {
            if(Orange_possible(customer_changes)) {
                int changes = getOrangeJuice();
                System.out.println("오렌지 주스가 정상적으로 구매되었습니다");
                customer_has = want_juice;
                customer_changes -= changes;
            }
            else {
                System.out.println("오렌지 주스를 구매하실 수 없습니다");
            }
        }

        else if(want_juice.equals("Apple juilce")) {
            if(Apple_possible(customer_changes)) {
                int changes = getAppleJuice();
                System.out.println("사과 주스가 정상적으로 구매되었습니다");
                customer_has = want_juice;
                customer_changes -= changes;
            }
            else {
                System.out.println("사과 주스를 구매하실 수 없습니다");
            }

        } else {

            System.out.println("없는 물품입니다");
        }

        System.out.println("잔액 : " + customer_changes + "\t갖고있는 음료 : " + customer_has);
    }


    // 오렌지주스 구매 가능?
    static boolean Orange_possible(int pay) {

        if (Orange_juice > 0) {

            if (pay >= 500) {
                return true;
            }
        }

        return false;
    }

    // 오렌지 주스 꺼내기
    static int getOrangeJuice() {
        Orange_juice--;
        return 500;
    }

    // 사과주스 구매 가능?
    static boolean Apple_possible(int pay) {

        if (Apple_juice > 0) {

            if (pay >= 300) {
                return true;
            }

        }
        return false;
    }

    // 사과주스 꺼내기
    static int getAppleJuice() {
        Apple_juice--;
        return 300;
    }
}
