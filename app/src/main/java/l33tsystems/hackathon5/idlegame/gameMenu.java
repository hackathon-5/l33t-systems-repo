package l33tsystems.hackathon5.idlegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class gameMenu extends Activity {

    private int timeout = 30;

    // 0 = Freeplay, 1 = Easy, 2 = Normal, 3 = Hard, 4 = Random
    private int difficultyValue = 0;
    // 0 = Self-Paced, 1 = Quick, 2 = Normal, 3 = Extensive, 4 = Random
    private int lengthValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_menu);

        // TODO:  Check for Save.xml, and load saved game from it on startup
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_menu, menu);
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

    // Called when the user clicks the Red Button //
    public void onClickRed(View view) {
        hideMenu();
        showDifficulty();
        resetTimeout();
        setArmyName(getResources().getString(R.string.redArmy));
    }

    // Called when the user clicks the Blue Button //
    public void onClickBlue(View view) {
        hideMenu();
        showDifficulty();
        resetTimeout();
        setArmyName(getResources().getString(R.string.blueArmy));
    }

    // Called when the user clicks the Green Button //
    public void onClickGreen(View view) {
        hideMenu();
        showDifficulty();
        resetTimeout();
        setArmyName(getResources().getString(R.string.greenArmy));
    }

    // Called when the user clicks the Exit Button //
    public void onClickExit(View view) {
        finish();
        System.exit(0);
    }

    // Called when the user clicks the Difficulty Plus Button //
    public void onClickDifficultyIncrease(View view) {
        setDifficultyValue(getDifficultyValue() + 1);
        if (getDifficultyValue() > 4) { setDifficultyValue(0); }
        changeDifficultyText();
        resetTimeout();
    }

    // Called when the user clicks the Difficulty Subtract Button //
    public void onClickDifficultyDecrease(View view) {
        setDifficultyValue(getDifficultyValue() - 1);
        if (getDifficultyValue() < 0) { setDifficultyValue(4); }
        changeDifficultyText();
        resetTimeout();
    }

    // Called when the user clicks the Difficulty Plus Button //
    public void onClickLengthIncrease(View view) {
        setLengthValue(getLengthValue() + 1);
        if (getLengthValue() > 4) { setLengthValue(0); }
        changeLengthText();
        resetTimeout();
    }

    // Called when the user clicks the Length Decrease Button //
    public void onClickLengthDecrease(View view) {
        setLengthValue(getLengthValue() - 1);
        if (getLengthValue() < 0) { setLengthValue(4); }
        changeLengthText();
        resetTimeout();
    }

    // Called when the user clicks the Start Game Button //
    public void onClickStartGame(View view) {
        hideDifficulty();
        showGame();
        resetTimeout();
    }

    private void hideMenu() {
        // Hide Menu Buttons
    /*    View redButton = (Button) findViewById(R.id.buttonRed);
        redButton.setVisibility(View.INVISIBLE);
        View blueButton = (Button) findViewById(R.id.buttonBlue);
        blueButton.setVisibility(View.INVISIBLE);
        View greenButton = (Button) findViewById(R.id.buttonGreen);
        greenButton.setVisibility(View.INVISIBLE);
        View exitButton = (Button) findViewById(R.id.buttonExit);
        exitButton.setVisibility(View.INVISIBLE);   */
        View menu = (RelativeLayout) findViewById(R.id.layoutMenu);
        menu.setVisibility(View.INVISIBLE);
    }

    private void showMenu() {
        // Show Menu Buttons
    /*    View redButton = (Button) findViewById(R.id.buttonRed);
        redButton.setVisibility(View.VISIBLE);
        View blueButton = (Button) findViewById(R.id.buttonBlue);
        blueButton.setVisibility(View.VISIBLE);
        View greenButton = (Button) findViewById(R.id.buttonGreen);
        greenButton.setVisibility(View.VISIBLE);
        View exitButton = (Button) findViewById(R.id.buttonExit);
        exitButton.setVisibility(View.VISIBLE); */
        View menu = (RelativeLayout) findViewById(R.id.layoutMenu);
        menu.setVisibility(View.VISIBLE);
    }

    private void hideDifficulty() {
    /*    View difficultyTitleText = (TextView) findViewById(R.id.textDifficulty);
        difficultyTitleText.setVisibility(View.INVISIBLE);
        View difficultyIncreaseButton = (Button) findViewById(R.id.buttonDifficultyIncrease);
        difficultyIncreaseButton.setVisibility(View.INVISIBLE);
        View difficultyValueText = (TextView) findViewById(R.id.textDifficultyValue);
        difficultyValueText.setVisibility(View.INVISIBLE);
        View difficultyDecreaseButton = (Button) findViewById(R.id.buttonDifficultyDecrease);
        difficultyDecreaseButton.setVisibility(View.INVISIBLE); */
        disableLength();
        View menu = (RelativeLayout) findViewById(R.id.layoutDifficulty);
        menu.setVisibility(View.INVISIBLE);
    }

    private void showDifficulty() {
    /*    View difficultyTitleText = (TextView) findViewById(R.id.textDifficulty);
        difficultyTitleText.setVisibility(View.VISIBLE);
        View difficultyIncreaseButton = (Button) findViewById(R.id.buttonDifficultyIncrease);
        difficultyIncreaseButton.setVisibility(View.VISIBLE);
        View difficultyValueText = (TextView) findViewById(R.id.textDifficultyValue);
        difficultyValueText.setVisibility(View.VISIBLE);
        View difficultyDecreaseButton = (Button) findViewById(R.id.buttonDifficultyDecrease);
        difficultyDecreaseButton.setVisibility(View.VISIBLE);   */
        View menu = (RelativeLayout) findViewById(R.id.layoutDifficulty);
        menu.setVisibility(View.VISIBLE);
        if (getDifficultyValue() > 0) { enableLength(); }
    }

    private void changeDifficultyText() {
        TextView difficulty = (TextView) findViewById(R.id.textDifficultyValue);
        switch(getDifficultyValue()) {
            case 0:
                difficulty.setText(getResources().getString(R.string.difficulty0));
                disableLength();
                setLengthValue(0);
                break;
            case 1:
                difficulty.setText(getResources().getString(R.string.difficulty1));
                enableLength();
                break;
            case 2:
                difficulty.setText(getResources().getString(R.string.difficulty2));
                enableLength();
                break;
            case 3:
                difficulty.setText(getResources().getString(R.string.difficulty3));
                enableLength();
                break;
            case 4:
                difficulty.setText(getResources().getString(R.string.difficulty4));
                enableLength();
                break;
            default:
                break;
        }
    }

    private void resetTimeout() {
        timeout = 30;
    }

    private void setArmyName(String name) {
        // TODO:  Change Army Name Label
    }

    private void disableLength() {
        View lengthIncreaseButton = (Button) findViewById(R.id.buttonLengthIncrease);
        lengthIncreaseButton.setEnabled(false);
        lengthIncreaseButton.setVisibility(View.INVISIBLE);
        View lengthDecreaseButton = (Button) findViewById(R.id.buttonLengthDecrease);
        lengthDecreaseButton.setEnabled(false);
        lengthDecreaseButton.setVisibility(View.INVISIBLE);
        View lengthValueText = (TextView) findViewById(R.id.textLengthValue);
        lengthValueText.setVisibility(View.INVISIBLE);
        View lengthTitleText = (TextView) findViewById(R.id.textLength);
        lengthTitleText.setVisibility(View.INVISIBLE);
    }

    private void enableLength() {
        View lengthIncreaseButton = (Button) findViewById(R.id.buttonLengthIncrease);
        lengthIncreaseButton.setEnabled(true);
        lengthIncreaseButton.setVisibility(View.VISIBLE);
        View lengthDecreaseButton = (Button) findViewById(R.id.buttonLengthDecrease);
        lengthDecreaseButton.setEnabled(true);
        lengthDecreaseButton.setVisibility(View.VISIBLE);
        View lengthValueText = (TextView) findViewById(R.id.textLengthValue);
        lengthValueText.setVisibility(View.VISIBLE);
        View lengthTitleText = (TextView) findViewById(R.id.textLength);
        lengthTitleText.setVisibility(View.VISIBLE);
    }

    private void changeLengthText() {
        TextView length = (TextView) findViewById(R.id.textLengthValue);
        switch(getLengthValue()) {
            case 0:
                length.setText(getResources().getString(R.string.length0));
                break;
            case 1:
                length.setText(getResources().getString(R.string.length1));
                break;
            case 2:
                length.setText(getResources().getString(R.string.length2));
                break;
            case 3:
                length.setText(getResources().getString(R.string.length3));
                break;
            case 4:
                length.setText(getResources().getString(R.string.length4));
                enableLength();
                break;
            default:
                break;
        }
    }

    private void hideGame() {
        View game = (RelativeLayout) findViewById(R.id.layoutGame);
        game.setVisibility(View.INVISIBLE);
    }

    private void showGame() {
        View game = (RelativeLayout) findViewById(R.id.layoutGame);
        game.setVisibility(View.VISIBLE);
    }

    private int getDifficultyValue() { return difficultyValue; }
    private void setDifficultyValue(int d) { difficultyValue = d; }
    private int getLengthValue() { return lengthValue; }
    private void setLengthValue(int l) { lengthValue = l; }

    //TODO:  Add timeout countdown.
    //TODO:  Add XML Save file, read on open and write on exit.
}
