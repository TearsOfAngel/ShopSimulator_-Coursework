package ShopSimulator;

import ShopSimulator.strategy.PaymentStrategy;
import ShopSimulator.strategy.impl.BonusPayment;
import ShopSimulator.strategy.impl.CashPayment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в магазин!");

        Shop shop = new Shop();
        List<Product> productList = shop.getProducts();
        ShoppingCart shoppingCart = new ShoppingCart();

        Customer customer = new Customer("Юрий", new BonusCard(320), 690.0);


        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить товар в корзину");
            System.out.println("2. Взвесить товар");
            System.out.println("3. Показать содержимое корзины");
            System.out.println("4. Убрать товар из корзины");
            System.out.println("5. Выбрать стратегию оплаты");
            System.out.println("6. Оплатить покупку");
            System.out.println("7. Выйти из магазина");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Выберите товар для добавления в корзину: ");
                    System.out.println(System.lineSeparator());
                    shop.displayAllItemsInStore();
                    int product = scanner.nextInt() - 1;
                    if (shop.getProducts().get(product) instanceof WeighedProduct) {
                        System.out.println("\nКакое количество единиц товара необходимо?");
                        int amountOfProducts = scanner.nextInt();
                        shoppingCart.addProductToPacket((WeighedProduct) productList.get(product), amountOfProducts);
                    } else {
                        shoppingCart.addItem(productList.get(product));
                    }
                }
                case 2 -> {
                    System.out.print("Выберите товар, который хотите взвесить:" + "\n");
                    shoppingCart.displayItemsToWeigh();
                    int weighedProduct = scanner.nextInt();
                    shoppingCart.weighItem(weighedProduct);
                }
                case 3 -> shoppingCart.displayItems();
                case 4 -> {
                    System.out.print("Выберите товар для удаления из корзины: ");
                    shoppingCart.displayItems();
                    int removeChoice = scanner.nextInt();
                    shoppingCart.removeItem(removeChoice);
                }
                case 5 -> {
                    System.out.println("Выберите как оплатить покупки: ");
                    customer.showBalanceBonusesAndCash();
                    System.out.println("1. Оплата наличными");
                    System.out.println("2. Оплата бонусами");
                    int paymentChoice = scanner.nextInt();

                    PaymentStrategy paymentStrategy = null;

                    if (paymentChoice == 1) {
                        paymentStrategy = new CashPayment();
                    } else if (paymentChoice == 2) {
                        paymentStrategy = new BonusPayment();
                    } else {
                        System.out.println("Ошибка. Выберите из предложенных способов оплаты");
                    }

                    if (paymentStrategy != null) {
                        customer.choosePaymentStrategy(paymentStrategy);
                    }
                }
                case 6 -> {
                    if (!shoppingCart.allItemsWeighed()) {
                        System.out.println("Взвесьте все товары, а затем попробуйте снова оплатить");
                    } else if (customer.getPaymentStrategy() != null) {
                        double totalCost = shoppingCart.getTotalCartCost();
                        customer.makePayment(totalCost);
                    } else {
                        System.out.println("Сначала выберите как оплатить покупку");
                    }
                }
                case 7 -> {
                    System.out.println("Спасибо за покупки! До свидания!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Неверный выбор. Пожалуйста, выберите действие от 1 до 7.");
            }
        }
    }
}
