package ShopSimulator.strategy.impl;

import ShopSimulator.Customer;
import ShopSimulator.strategy.PaymentStrategy;

public class CashPayment implements PaymentStrategy {

    public void pay(double amount, Customer customer) {
        double walletBalance = customer.getWalletBalance();
        if (walletBalance >= amount) {
            customer.setWalletBalance(walletBalance - amount);
            System.out.println("Оплата наличными прошла успешно.");
        } else {
            System.out.println("Недостаточно наличных средств для оплаты!");
        }
    }

    @Override
    public String toString() {
        return "наличными";
    }
}
