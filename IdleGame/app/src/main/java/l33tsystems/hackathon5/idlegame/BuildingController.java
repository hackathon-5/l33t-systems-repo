package l33tsystems.hackathon5.idlegame;

/**
 * Created by Chele89 on 8/29/2015.
 */
public class BuildingController {

    public static double getCurrencyPerSec(int n, int l, double u) {
        return ((n^2)*l)*u;
    }

    public static double getProductionPerSec(int n, int l, double u) {
        return (n*(((l-9)*(-1))^2))*u;
    }

    public static double getCostPerObject(int n, int l, double u, int b) {
        return (n*l)+(l^b);
    }

    public static double getAttackPowerForUnits(int n, int l, double u) {
        return (n*l)*u;
    }

}
