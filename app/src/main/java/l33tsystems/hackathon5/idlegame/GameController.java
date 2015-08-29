package l33tsystems.hackathon5.idlegame;

import java.util.ArrayList;

/**
 * Created by Chele89 on 8/29/2015.
 */
public class GameController {

    private int computerCurrency;
    private int computerCurrencyPerSecond;
    private int[] computerBuildings = new int[24];
    private int[] computerUnits = new int[computerBuildings.length];
    private int[] computerUpgrades = {0, 0, 0};

    private int playerCurrency;
    private int playerCurrentyPerSecond;
    private int[] playerBuildings = new int[24];
    private int[] playerUnits = new int[playerBuildings.length];
    private int[] playerUpgrades = {0, 0, 0};

    public double getCurrencyPerSec(int n, int l, double u) {
        return ((n^2)*l)*u;
    }

    public double getProductionPerSec(int n, int l, double u) {
        return (n*(((l-9)*(-1))^2))*u;
    }

    public double getCostPerObject(int n, int l, double u, int b) {
        return (n*l)+(l^b);
    }

    public double getAttackPowerForUnits(int n, int l, double u) {
        return (n*l)*u;
    }

    public int getComputerCurrency() { return computerCurrency; }
    public void setComputerCurrency(int c) { computerCurrency = c; }
    public int getComputerCurrencyPerSecond() { return computerCurrencyPerSecond; }
    public void setComputerCurrencyPerSecond() {

    }

}
