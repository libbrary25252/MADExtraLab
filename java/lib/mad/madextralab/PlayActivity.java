package lib.mad.madextralab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    ImageView cat_tap;
    TextView textResult, textInstruct;

    int currentTap = 0; //Tap counter
    boolean gameStarted = false;

    MediaPlayer gameMus = null;

    CountDownTimer timer;

    int bestResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        cat_tap = (ImageView) findViewById(R.id.imgCat);
        textResult = (TextView) findViewById(R.id.textResult);
        textInstruct =  (TextView) findViewById(R.id.textInstruct);

        //Get data and display the best result
        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        bestResult = preferences.getInt("Highest score", 0);
        textResult.setText("Best Result: " + bestResult);

        //Background music
        gameMus = MediaPlayer.create(this, R.raw.usuallife);//loop-playing
        gameMus.setLooping(true);
        Switch soundSw = (Switch) findViewById(R.id.gameSound);
        soundSw.setChecked(true); // set the switch as Checked (ON) initially
        soundSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // is enabled
                    if (gameMus != null) gameMus.start(); // start playing
                } else {
                    // is disabled
                    if (gameMus != null) gameMus.pause(); // pause playing
                }
            }
        });

        //Tapping the photo
        cat_tap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(gameStarted) {
                    // if game start, start to count the number of tapping
                    currentTap++;
                }else{
                    // start the game if the game is not started yet
                    textInstruct.setText("Tap ME meow~~~!!");
                    gameStarted = true;
                    timer.start();
                }
            }
        });

        //Set up the timer (10 seconds with 1 second interval)
        timer = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //display the remaining time
                long timeTillEnd = (millisUntilFinished/1000) + 1;
                textResult.setText("Time Remaining:" + timeTillEnd);
            }

            @Override
            public void onFinish() { // Game is over
                cat_tap.setEnabled(false);
                gameStarted = false;
                textInstruct.setText("Time Over~!" + "\nThe cat is now happy :3");

                //Check the highest result and update it with the best score if perform better
                if(currentTap > bestResult) {
                    bestResult = currentTap;
                    SharedPreferences preferences1 = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putInt("Highest score", bestResult);
                    editor.apply();
                }

                //Display the best result and the current score
                textResult.setText("Best Result: " + bestResult + "\nTotal Taps: " + currentTap);

                //Reset everything and prepare for the new game after 2 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cat_tap.setEnabled(true);
                        textInstruct.setText("Tap!!!!!");
                        currentTap = 0;
                    }
                },2000);
            }
        };
    }
}
