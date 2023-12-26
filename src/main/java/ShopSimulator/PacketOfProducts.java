package ShopSimulator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PacketOfProducts {

    private int quantity;

    private boolean isWeighed;

    private double totalCost;

    @Override
    public String toString() {
        return  " количество: " + quantity +
                ", взвешено ли: " + isWeighed +
                ", общая стоимость: " + totalCost;
    }
}
