package ShopSimulator.strategy;

import ShopSimulator.Customer;

public interface PaymentStrategy {
    boolean pay(double amount, Customer customer);
}
