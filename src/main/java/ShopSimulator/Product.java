package ShopSimulator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private String name;

    private double price;

    @Override
    public String toString() {
        return String.format("%s - цена %.2f", name, price);
    }
}
