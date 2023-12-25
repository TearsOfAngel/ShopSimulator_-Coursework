package ShopSimulator;

public class BonusPayment implements PaymentStrategy{

    public void pay(double amount) {
        System.out.println("Оплата бонусами: " + amount);
    }
}
