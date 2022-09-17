package com.example.oop.solid.dip;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

interface Pay {
    String payment();
}

class SamsungPay implements Pay {
    @Override
    public String payment() {
        return "samsung";
    }
}

class KakaoPay implements Pay {
    @Override
    public String payment() {
        return "kakao";
    }
}

@Service
@AllArgsConstructor
class PayService {
    private Pay pay;

    public String payment() {
        return pay.payment();
    }
}

public class DIP {

    public static void main(String[] args) {
        KakaoPay kakaoPay = new KakaoPay();
        PayService payService = new PayService(kakaoPay);

        String pay = payService.payment();
        System.out.println(pay);
    }
}
