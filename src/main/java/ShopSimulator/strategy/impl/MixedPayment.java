package ShopSimulator.strategy.impl;

import ShopSimulator.BonusCard;
import ShopSimulator.Customer;
import ShopSimulator.strategy.PaymentStrategy;

public class MixedPayment implements PaymentStrategy {
    @Override
    public boolean pay(double amount, Customer customer) {
        double remainingAmount = amount;

        BonusCard bonusCard = customer.getBonusCard();
        double bonusPoints = bonusCard.getPoints();

        if (bonusPoints > 0) {
            double pointsToRedeem = Math.min(bonusPoints, remainingAmount);
            bonusCard.redeemBonusPoints(pointsToRedeem);
            remainingAmount -= pointsToRedeem;
        }

        if (remainingAmount > 0) {
            double walletBalance = customer.getWalletBalance();
            if (walletBalance >= remainingAmount) {
                customer.setWalletBalance(walletBalance - remainingAmount);
                remainingAmount = 0;
            } else {
                System.out.println("Недостаточно наличных.");
            }
        }

        if (remainingAmount == 0) {
            System.out.println("Оплата прошла успешно!");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "смешанная";
    }
}
