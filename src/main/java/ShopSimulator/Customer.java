package ShopSimulator;

import ShopSimulator.strategy.PaymentStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

    private String name;

    private BonusCard bonusCard;

    private double walletBalance;

    private PaymentStrategy paymentStrategy;

    public Customer(String name, BonusCard bonusCard, double walletBalance) {
        this.name = name;
        this.bonusCard = bonusCard;
        this.walletBalance = walletBalance;
    }

    public void choosePaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        System.out.println("Выбрана оплата: " + paymentStrategy.toString());
    }

    public void makePayment(double amount) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(amount, this);
        } else {
            System.out.println("Не выбрана стратегия оплаты");
        }
    }

    public void showBalanceBonusesAndCash() {
        System.out.printf("Денег в кошельке: %.0f руб.%n", walletBalance);
        System.out.printf("Бонусов на карте: %.0f%n" + "\n", bonusCard.getPoints());
    }
}
