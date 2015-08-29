package l33tsystems.hackathon5.tronwarz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends Activity {

    GameController controller;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainMenu.ARMY_NAME);
        TextView armyName = (TextView) findViewById(R.id.txtArmyName);
        armyName.setText(message);

        handler= new Handler(Looper.getMainLooper());

        controller = new GameController();

        TickTimerTask tick = new TickTimerTask(this);
        Timer t = new Timer();

        t.scheduleAtFixedRate(tick, 0, 1000);  // 1 sec interval
    }

    class TickTimerTask extends TimerTask {
        Activity activity;

        public TickTimerTask(Activity a) { activity = a; }

        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    controller.onTick();
                    updateText();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void increaseBuilding(View view) {
        if (controller.currentCurrency >= controller.buildingCost) {
            controller.buildings += 1;
            controller.currentCurrency -= controller.buildingCost;
        }
    }

    public void startBattle(View view) {
        Random rnd = new Random(System.nanoTime());
        Integer compScore;
        String status;
        if (rnd.nextBoolean()) {    // WIN
            double compScoreDbl = controller.buildingUnits - (rnd.nextInt(controller.buildingUnits)*.1);
            compScore = (int) compScoreDbl;
            status = "You Won!!";
            controller.bonus += 1;
        } else {
            double compScoreDbl = controller.buildingUnits + (rnd.nextInt(controller.buildingUnits)*.1);
            compScore = (int) compScoreDbl;
            status = "You Lost!?";
        }
        TextView statistics = (TextView) findViewById(R.id.txtBattleStatistics);
        statistics.setText(status + "\r\n" + "Your Score:\t" + controller.buildingUnits + "\r\n" + "Opponent's Score:\t" + compScore);
        if (compScore > controller.buildingUnits) { controller.buildingUnits = 0; }
        else { controller.buildingUnits -= compScore; }
    }

    private void updateText() {
        TextView currency = (TextView) findViewById(R.id.txtCurrency);
        currency.setText(updateCurrencyText(controller.currentCurrency));
        updateBuildingText();
    }

    private String updateCurrencyText(Integer currency) {
        String B = "Bytes";
        Integer c = currency;
        switch(currencyBreakdown(c)) {
            case 1:     c = c / 1000;               B = "KiloBytes";    break;
            case 2:     c = c / 1000000;            B = "MegaBytes";    break;
            case 3:     c = c / (1000 * 1000000);   B = "GigaBytes";    break;
            case 4:     c = c / (100000 * 1000000); B = "TeraBytes";    break;
            default:    break;
        }
        return c.toString() + " " + B;
    }

    private int currencyBreakdown(Integer current) {
        if (current < 1000) { return 0; }
        if (current < 1000000) { return 1; }
        if (current < (1000 * 1000000)) { return 2; }
        if (current < (1000000 * 1000000)) { return 3; }
        return 4;
    }

    private void updateBuildingText() {
        TextView building = (TextView) findViewById(R.id.txtBuildingName);
        building.setText(controller.buildings.toString() + " " + getResources().getString(R.string.buildingName));
        updateUnitText();
        updateBuildingCurrencyText();
    }

    private void updateUnitText() {
        TextView unit = (TextView) findViewById(R.id.txtBuildingUnits);
        unit.setText(controller.buildingUnits.toString() + " " + getResources().getString(R.string.buildingUnits) + " - " + controller.buildingUnitsPerSecond.toString() + " per Second");
    }

    private void updateBuildingCurrencyText() {
        TextView currency = (TextView) findViewById(R.id.txtBuildingCurrency);
        currency.setText("+" + updateCurrencyText(controller.buildingCurrencyPerSecond) + " per Second - Purchase of 1 costs " + updateCurrencyText(controller.buildingCost));
    }
}
