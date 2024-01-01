package ShopSimulator.strategy.impl;

import ShopSimulator.Customer;
import ShopSimulator.strategy.PaymentStrategy;

public class CashPayment implements PaymentStrategy {

    public boolean pay(double amount, Customer customer) {
        double walletBalance = customer.getWalletBalance();
        if (walletBalance >= amount) {
            customer.reduceWalletBalance(amount);
            System.out.println("Оплата наличными прошла успешно!");
            return true;
        } else {
            System.out.println("Недостаточно наличных средств для оплаты!");
            return false;
        }
    }

    @Override
    public String toString() {
        return "наличными";
    }
}
