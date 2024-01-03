package ShopSimulator;

import ShopSimulator.strategy.PaymentStrategy;
import ShopSimulator.strategy.impl.BonusPayment;
import ShopSimulator.strategy.impl.CashPayment;
import ShopSimulator.strategy.impl.MixedPayment;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в магазин!");

        Shop shop = new Shop();
        List<Product> productList = shop.getProducts();
        ShoppingCart shoppingCart = new ShoppingCart();

        Customer customer = new Customer("Юрий", new BonusCard(320), 690.0);
        customer.setShoppingCart(shoppingCart);


        while (true) {
            printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            handleChoice(choice, shop, productList, shoppingCart, customer, scanner);
        }
    }

    private static void printMenu() {
        System.out.println(System.lineSeparator());
        System.out.println("Выберите действие:");
        System.out.println("1. Добавить товар в корзину");
        System.out.println("2. Взвесить товар");
        System.out.println("3. Показать содержимое корзины");
        System.out.println("4. Убрать товар из корзины");
        System.out.println("5. Выбрать стратегию оплаты");
        System.out.println("6. Оплатить покупку");
        System.out.println("7. Посмотреть баланс");
        System.out.println("8. Выйти из магазина");
        System.out.println(System.lineSeparator());
    }

    private static void handleChoice(int choice,
                                     Shop shop,
                                     List<Product> productList,
                                     ShoppingCart shoppingCart,
                                     Customer customer,
                                     Scanner scanner) {
        switch (choice) {
            case 1 -> {
                addItemToCart(shop, productList, shoppingCart, scanner);
            }
            case 2 -> {
                chooseProductForWeighing(shoppingCart, scanner);
            }
            case 3 -> {
                displayCartContent(shoppingCart);
            }
            case 4 -> {
                removeItemFromCart(shoppingCart, scanner);
            }
            case 5 -> {
                choosePaymentStrategy(customer, scanner);
            }
            case 6 -> {
                makePayment(customer, shoppingCart);
            }
            case 7 -> {
                customer.showBalanceBonusesAndCash();
            }
            case 8 -> {
                exitStore(scanner);
            }
            default -> System.out.println("Неверный выбор. Пожалуйста, выберите действие от 1 до 7.");
        }
    }

    private static void addItemToCart(Shop shop, List<Product> productList, ShoppingCart shoppingCart, Scanner scanner) {
        System.out.print("Выберите товар для добавления в корзину: ");
        System.out.println(System.lineSeparator());
        shop.displayAllItemsInStore();
        int product = scanner.nextInt() - 1;
        if (shop.getProducts().get(product) instanceof ProductForWeighing) {
            System.out.println("\nКакое количество единиц товара необходимо?");
            int amountOfProducts = scanner.nextInt();
            shoppingCart.addProductToPacket((ProductForWeighing) productList.get(product), amountOfProducts);
        } else {
            shoppingCart.addItem(productList.get(product));
        }
    }

    private static void chooseProductForWeighing(ShoppingCart shoppingCart, Scanner scanner) {
        System.out.print("Выберите товар, который хотите взвесить:" + "\n");
        shoppingCart.displayItemsToWeigh();
        int weighedProduct = scanner.nextInt();
        shoppingCart.weighItem(weighedProduct);
    }

    private static void displayCartContent(ShoppingCart shoppingCart) {
        shoppingCart.displayItems();
    }


    private static void removeItemFromCart(ShoppingCart shoppingCart, Scanner scanner) {
        System.out.print("Выберите товар для удаления из корзины: ");
        shoppingCart.displayItems();
        int removeChoice = scanner.nextInt();
        shoppingCart.removeItem(removeChoice);
    }

    private static void choosePaymentStrategy(Customer customer, Scanner scanner) {
        System.out.println("Выберите как оплатить покупки: ");
        customer.showBalanceBonusesAndCash();
        System.out.println("1. Оплата наличными");
        System.out.println("2. Оплата бонусами");
        System.out.println("3. Смешанная оплата");
        int paymentChoice = scanner.nextInt();

        PaymentStrategy paymentStrategy = null;

        switch (paymentChoice) {
            case 1 -> paymentStrategy = new CashPayment();
            case 2 -> paymentStrategy = new BonusPayment();
            case 3 -> paymentStrategy = new MixedPayment();
            default -> System.out.println("Ошибка. Выберите из предложенных способов оплаты");
        }

        if (paymentStrategy != null) {
            customer.choosePaymentStrategy(paymentStrategy);
        }
    }

    private static void makePayment(Customer customer, ShoppingCart shoppingCart) {
        if (!shoppingCart.allItemsWeighed()) {
            System.out.println("Взвесьте все товары, а затем попробуйте снова оплатить");
        } else if (customer.getPaymentStrategy() != null) {
            double totalCost = shoppingCart.getTotalCartCost();
            customer.makePayment(totalCost);
        } else {
            System.out.println("Сначала выберите как оплатить покупку");
        }
    }

    private static void exitStore(Scanner scanner) {
        System.out.println("Спасибо за покупки! До свидания!");
        scanner.close();
        System.exit(0);
    }
}
