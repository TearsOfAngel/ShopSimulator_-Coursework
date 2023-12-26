package ShopSimulator.strategy.impl;

import ShopSimulator.BonusCard;
import ShopSimulator.Customer;
import ShopSimulator.strategy.PaymentStrategy;

public class BonusPayment implements PaymentStrategy {

    public void pay(double amount, Customer customer) {
        BonusCard bonusCard = customer.getBonusCard();
        if (bonusCard != null) {
            bonusCard.redeemBonusPoints(amount);
        } else {
            System.out.println("Невозможно произвести оплату бонусами. У вас нет карты!");
        }
    }

    @Override
    public String toString() {
        return "бонусами";
    }
}
