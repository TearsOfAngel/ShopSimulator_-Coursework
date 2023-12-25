package ShopSimulator;

import lombok.Getter;

@Getter
public class Product {

    private String name;

    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    void displayInfo() {
        System.out.println(name + " - $" + price);
    }

    @Override
    public String toString() {
        return String.format("%s - цена %.2f", name, price);
    }
}
