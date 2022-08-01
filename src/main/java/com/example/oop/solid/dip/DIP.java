package com.example.oop.solid.dip;

public class DIP {
    public interface Pay {
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
            return "kakay";
        }
    }

    public class PayService {
        private Pay pay;

        public void setPay(final Pay pay) {
            this.pay = pay;
        }

        public String payment() {
            return pay.payment();
        }
    }
}
