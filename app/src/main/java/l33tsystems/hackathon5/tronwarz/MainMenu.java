package l33tsystems.hackathon5.tronwarz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainMenu extends Activity {

    boolean difficultySelection = false;
    public static String ARMY_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    public void selectRed(View view) {
        changeArmy(getResources().getString(R.string.redArmy));
    }

    public void selectBlue(View view) {
        changeArmy(getResources().getString(R.string.blueArmy));
    }

    public void selectGreen(View view) {
        changeArmy(getResources().getString(R.string.greenArmy));
    }

    public void selectExit(View view) {
        finish();
        System.exit(0);
    }

    private void changeArmy(String name) {
        ARMY_NAME = name;
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(ARMY_NAME, name);
        startActivity(intent);
    }
}
