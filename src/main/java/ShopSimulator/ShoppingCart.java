package ShopSimulator;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class ShoppingCart {

    private List<CartItem> cartItems = new ArrayList<>();

    private HashMap<WeighedProduct, PacketOfProducts> productsToWeigh = new LinkedHashMap<>();

    private List<Product> allItemsInCart = new LinkedList<>();

    void addItem(Product item) {
        allItemsInCart.add(item);

        if (item instanceof WeighedProduct) {
            productsToWeigh.put((WeighedProduct) item, new PacketOfProducts(0, false, 0.0));
        } else {
            cartItems.stream()
                    .filter(cartItem -> cartItem.getProduct().equals(item))
                    .findFirst()
                    .ifPresentOrElse(
                            CartItem::incrementQuantity,
                            () -> cartItems.add(new CartItem(item))
                    );
        }

        System.out.println("Товар добавлен в корзину: " + item);
    }

    void addProductToPacket(WeighedProduct product, int amount) {
        if (productsToWeigh.containsKey(product)) {
            PacketOfProducts packet = productsToWeigh.get(product);

            if (packet.isWeighed()) {
                packet.setWeighed(false);
                packet.setTotalCost(0.0);
            }

            packet.setQuantity(packet.getQuantity() + amount);
        } else {
            PacketOfProducts packet = new PacketOfProducts(amount, false, 0.0);
            productsToWeigh.put(product, packet);
        }
        allItemsInCart.add(product);
        System.out.println("Товар добавлен в пакет. Его необходимо будет взвесить: " + product);
    }

    void displayItems() {
        System.out.println("Товары в корзине:");
        int index = 1;
        for (CartItem cartItem : cartItems) {
            System.out.println(index + "." + " - " + cartItem);
            index++;
        }
        for (Map.Entry<WeighedProduct, PacketOfProducts> entry : productsToWeigh.entrySet()) {
            WeighedProduct product = entry.getKey();
            PacketOfProducts packet = entry.getValue();
            System.out.println(index + "." + " - " + product.toString() + packet.toString());
            index++;
        }
    }

    void displayItemsToWeigh() {
        System.out.println("Товары для взвешивания: ");
        int index = 1;
        for (Map.Entry<WeighedProduct, PacketOfProducts> entry : productsToWeigh.entrySet()) {
            WeighedProduct product = entry.getKey();
            PacketOfProducts packet = entry.getValue();
            System.out.println(index + "." + " - " + product.toString() + packet.toString());
            index++;
        }
    }

    void weighItem(int index) {
        index -= 1;
        List<WeighedProduct> weighedProductList = new ArrayList<>(productsToWeigh.keySet());

        if (index >= 0 && index < weighedProductList.size()) {
            WeighedProduct weighedProduct = weighedProductList.get(index);
            PacketOfProducts packet = productsToWeigh.get(weighedProduct);
            if (!packet.isWeighed()) {
                packet.setWeighed(true);
                double totalPrice = weighedProduct.getPrice() * packet.getQuantity();
                packet.setTotalCost(totalPrice);
                System.out.println("Взвешивание товара прошло успешно! Общая стоимость: " + totalPrice);
            } else {
                System.out.println("Этот товар уже взвешен.");
            }
        } else {
            System.out.println("Неверный индекс товара для взвешивания.");
        }
    }

    void removeItem(int index) {
        index -= 1;

        if (index >= 0 && index < allItemsInCart.size()) {
            Product removedProduct = allItemsInCart.get(index);

            if (removedProduct instanceof WeighedProduct) {
                productsToWeigh.remove(removedProduct);
            } else {
                cartItems.removeIf(cartItem -> cartItem.getProduct().equals(removedProduct));
            }

            System.out.println("Товар удален из корзины: " + removedProduct);
        } else {
            System.out.println("Неверный индекс товара для удаления.");
        }
    }
}
