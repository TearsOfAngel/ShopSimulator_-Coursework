package ShopSimulator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForWeighing extends Product{

    private double weight;

    private boolean isWeighed;


    public ProductForWeighing(String name, double price, double weight) {
        super(name, price);
        this.weight = weight;
        this.isWeighed = false;
    }

    @Override
    public String toString() {
        return String.format("%s - цена %.2f за кг, вес %.2f г.", getName(), getPrice(), weight);
    }
}
