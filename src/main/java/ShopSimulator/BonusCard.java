package ShopSimulator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BonusCard {

    private double points;

    public void redeemBonusPoints(double points) {
        if (this.points < points) {
            System.out.println("Недостаточно баллов на карте");
        } else {
            this.points -= points;
        }
    }

    @Override
    public String toString() {
        return "Количество баллов на карте: " + points;
    }
}
