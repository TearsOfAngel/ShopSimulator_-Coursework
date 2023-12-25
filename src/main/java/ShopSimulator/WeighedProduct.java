package ShopSimulator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeighedProduct extends Product{

    private double weight;

    private boolean isWeighed;


    public WeighedProduct(String name, double price, double weight) {
        super(name, price);
        this.weight = weight;
        this.isWeighed = false;
    }

    @Override
    void displayInfo() {
        System.out.println(getName() + " - " + getPrice() + " рублей за 1 кг " + weight + " кг");
    }

    @Override
    public String toString() {
        return String.format("%s - цена %.2f за кг, вес %.2f г.", getName(), getPrice(), weight);
    }
}
