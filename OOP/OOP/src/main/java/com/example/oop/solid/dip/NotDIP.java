package com.example.oop.solid.dip;

public class NotDIP {
    class SamsungPay {
        String payment() {
            return "samsung";
        }
    }

    class KakaoPay {
        String payment() {
            return "kakao";
        }
    }

    public class PayService {
        private SamsungPay samsungPay;
        private KakaoPay kakaoPay;

        public void setSamsungPay(final SamsungPay samsungPay) {
            this.samsungPay = samsungPay;
        }

        public void setKakaoPay(final KakaoPay kakaoPay) {
            this.kakaoPay = kakaoPay;
        }

        public String SSpayment() {
            return samsungPay.payment();
        }

        public String KKPayment() {
            return kakaoPay.payment();
        }
    }
}
