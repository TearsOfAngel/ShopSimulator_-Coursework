package ShopSimulator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Shop {

    private List<Product> products;

    Shop() {
        this.products = new ArrayList<>();
        products.add(new Product("Печенье", 150));
        products.add(new Product("Молоко", 98));
        products.add(new Product("Йогурт", 35));
        products.add(new Product("Конфеты", 400));
        products.add(new Product("Шоколадка", 90));
        products.add(new ProductForWeighing("Яблоки", 25, 105));
        products.add(new ProductForWeighing("Картофель", 19, 85));
        products.add(new ProductForWeighing("Лимоны", 30, 120));
    }

    void displayAllItemsInStore() {
        int productPosition = 1;
        for (Product value : products) {
            System.out.println(productPosition + " - " + value);
            productPosition++;
        }
    }
}
