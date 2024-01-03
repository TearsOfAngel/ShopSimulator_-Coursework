package ShopSimulator;

import lombok.*;

import java.util.*;

@Getter
@Setter
public class ShoppingCart {

    private List<CartItem> cartItems = new ArrayList<>();

    private HashMap<ProductForWeighing, PacketOfProducts> productsToWeigh = new LinkedHashMap<>();

    private List<Product> allItemsInCart = new LinkedList<>();

    private double totalCartCost;

    void addItem(Product item) {
        allItemsInCart.add(item);

        if (item instanceof ProductForWeighing) {
            productsToWeigh.put((ProductForWeighing) item, new PacketOfProducts(0, false, 0.0));
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
        updateTotalCartCost();
    }

    void addProductToPacket(ProductForWeighing product, int amount) {
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
        updateTotalCartCost();
    }

    void displayItems() {
        System.out.println("Товары в корзине:");
        int index = 1;
        for (CartItem cartItem : cartItems) {
            System.out.println(index + "." + " - " + cartItem);
            index++;
        }
        for (Map.Entry<ProductForWeighing, PacketOfProducts> entry : productsToWeigh.entrySet()) {
            ProductForWeighing product = entry.getKey();
            PacketOfProducts packet = entry.getValue();
            System.out.println(index + "." + " - " + product.toString() + packet.toString());
            index++;
        }
    }

    void displayItemsToWeigh() {
        System.out.println("Товары для взвешивания: ");
        int index = 1;
        for (Map.Entry<ProductForWeighing, PacketOfProducts> entry : productsToWeigh.entrySet()) {
            ProductForWeighing product = entry.getKey();
            PacketOfProducts packet = entry.getValue();
            System.out.println(index + "." + " - " + product.toString() + packet.toString());
            index++;
        }
    }

    void weighItem(int index) {
        index -= 1;
        List<ProductForWeighing> productForWeighingList = new ArrayList<>(productsToWeigh.keySet());

        if (index >= 0 && index < productForWeighingList.size()) {
            ProductForWeighing productForWeighing = productForWeighingList.get(index);
            PacketOfProducts packet = productsToWeigh.get(productForWeighing);
            if (!packet.isWeighed()) {
                packet.setWeighed(true);
                double totalPrice = productForWeighing.getPrice() * packet.getQuantity();
                packet.setTotalCost(totalPrice);
                System.out.println("Взвешивание товара прошло успешно! Общая стоимость: " + totalPrice);
            } else {
                System.out.println("Этот товар уже взвешен.");
            }
        } else {
            System.out.println("Неверный индекс товара для взвешивания.");
        }
        updateTotalCartCost();
    }

    void removeItem(int index) {
        index -= 1;

        if (index >= 0 && index < allItemsInCart.size()) {
            Product removedProduct = allItemsInCart.get(index);

            if (removedProduct instanceof ProductForWeighing) {
                productsToWeigh.remove(removedProduct);
            } else {
                cartItems.removeIf(cartItem -> cartItem.getProduct().equals(removedProduct));
            }

            System.out.println("Товар удален из корзины: " + removedProduct);
        } else {
            System.out.println("Неверный индекс товара для удаления.");
        }
        updateTotalCartCost();
    }

    private void updateTotalCartCost() {
        double totalCost = 0.0;

        for (CartItem cartItem : cartItems) {
            totalCost += cartItem.getTotalCost();
        }

        for (PacketOfProducts packet : productsToWeigh.values()) {
            totalCost += packet.getTotalCost();
        }

        totalCartCost = totalCost;
    }

    public boolean allItemsWeighed() {
        for (PacketOfProducts packet : productsToWeigh.values()) {
            if (!packet.isWeighed()) {
                return false;
            }
        }
        return true;
    }

    public void clearCart() {
        cartItems.clear();
        productsToWeigh.clear();
        allItemsInCart.clear();
        totalCartCost = 0.0;
    }
}
