package ShopSimulator.strategy;

import ShopSimulator.Customer;

public interface PaymentStrategy {
    void pay(double amount, Customer customer);
}
