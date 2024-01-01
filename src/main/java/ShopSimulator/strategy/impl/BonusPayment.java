package ShopSimulator.strategy.impl;

import ShopSimulator.BonusCard;
import ShopSimulator.Customer;
import ShopSimulator.strategy.PaymentStrategy;

public class BonusPayment implements PaymentStrategy {

    public boolean pay(double amount, Customer customer) {
        BonusCard bonusCard = customer.getBonusCard();
        if (bonusCard.getPoints() >= amount) {
            bonusCard.redeemBonusPoints(amount);
            System.out.println("Оплата бонусами прошла успешно! ");
            return true;
        } else {
            System.out.println("Не хватает баллов.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "бонусами";
    }
}
