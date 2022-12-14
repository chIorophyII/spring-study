package com.example.oop.oop;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class OOP {
    public static void main(String[] args) {

        Customer customer = new Customer(1000);
        Vending_Machine vm = new Vending_Machine(10, 3);

        // 오렌지 주스가 먹고 싶다
        String want_juice = "Orange juice";

        int pay = vm.buy(want_juice, customer.getChanges());

        // 구매 실패시
        if(pay == 0) {
            customer.resetting_juice(pay, null);
        }
        // 구매 성공시
        else {
            customer.resetting_juice(pay, want_juice);
        }
        System.out.println(customer);
    }
}

// 고객
@Getter
class Customer {

    private int changes;
    private String hasJuice = null;

    public Customer(int changes) {
        this.changes = changes;
    }

    // 잔액 설정
    public void resetting_juice(int changes , String hasJuice) {
        this.changes -= changes;
        this.hasJuice = hasJuice;
    }

    public String toString() {
        return "잔액 : " + changes + "\t갖고있는 음료 : " + hasJuice;
    }
}

// 자판기
@AllArgsConstructor
class Vending_Machine {
    // 자판기에 남아있는 주스 개수
    private int Orange_juice; // 오렌지 주스 가격 : 500
    private int Apple_juice; // 사과 주스 가격 : 300

    // 오렌지 주스 판매가 가능한지 검사
    private boolean Orange_possible(int pay) {
        return Orange_juice > 0 && pay >= 500;
    }

    // 사과 주스 판매가 가능한지 검사
    private boolean Apple_possible(int pay) {
        return Apple_juice > 0 && pay >= 300;
    }

    public int buy(String kind, int pay) {
        if(kind.equals("Orange juice")) {
            if(Orange_possible(pay)) {
                Orange_juice--;
                System.out.println("오렌지 주스가 정상적으로 구매되었습니다");
                return 500;
            }
            System.out.println("오렌지 주스를 구매하실 수 없습니다");
            return 0;
        }
        else if(kind.equals("Apple juice")) {
            if(Apple_possible(pay)) {
                Apple_juice--;
                System.out.println("사과 주스가 정상적으로 구매되었습니다");
                return 300;
            }
            System.out.println("사과 주스를 구매하실 수 없습니다");
            return 0;
        }
        System.out.println("없는 물품입니다");
        return 0;
    }
}
