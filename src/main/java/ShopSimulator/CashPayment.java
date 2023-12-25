package ShopSimulator;

public class CashPayment implements PaymentStrategy{

    public void pay(double amount) {
        System.out.println("Оплата наличными: " + amount);
    }
}
