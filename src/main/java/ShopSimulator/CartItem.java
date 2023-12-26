package ShopSimulator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {

    private Product product;

    private int quantity;

    private double totalCost;

    CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
        this.totalCost = product.getPrice();
    }

    void incrementQuantity() {
        this.quantity++;
        this.totalCost = quantity * product.getPrice();
    }

    @Override
    public String toString() {
        return String.format("%s x%d, общая стоимость: %.2f руб.",
                product.getName(), quantity, product.getPrice() * quantity);
    }
}