package ShopSimulator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {

    private Product product;
    private int quantity;

    CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    void incrementQuantity() {
        this.quantity++;
    }

    @Override
    public String toString() {
        return String.format("%s x%d, общая стоимость: %.2f руб.",
                product.getName(), quantity, product.getPrice() * quantity);
    }
}