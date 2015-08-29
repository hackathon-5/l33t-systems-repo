package l33tsystems.hackathon5.tronwarz;

import android.widget.TextView;

/**
 * Created by Chele on 8/29/2015.
 */
public class GameController {

    public Integer currentCurrency = 0;

    public Integer buildings = 0;
    public Integer buildingUnits = 0;
    public Integer buildingUnitsPerSecond = 0;
    public Integer buildingCurrencyPerSecond = 0;
    public Integer buildingCost = 0;

    public Integer bonus = 0;

    public GameController() {
        buildings += 1;
        onTick();
    }

    public void onTick() {
        checkBuildingCalculations();
        addBuildingUnits();
        addCurrency();
    }

    private void checkBuildingCalculations() {
        buildingUnitsPerSecond = (buildings * 10) * (1 + bonus);
        buildingCurrencyPerSecond = (buildings ^ 2) * (1 + bonus);
        buildingCost = buildings * buildings;
    }

    private void addBuildingUnits() {
        buildingUnits += buildingUnitsPerSecond;
    }

    private void addCurrency() {
        currentCurrency += buildingCurrencyPerSecond;
    }

}
