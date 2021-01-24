package lib.mad.madextralab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MediaPlayer bkMus = null;

//////START - Button Click to different activity screens//////
    public void but1Clicked (View view) {
        startActivity(new Intent(this, PlayActivity.class));
    }
    public void but2Clicked (View view) {
        startActivity(new Intent(this, GuidelinesActivity.class));
    }
    public void but3Clicked (View view) {
        startActivity(new Intent(this, AboutMeActivity.class));
    }
//////END - Button Click to different activity screens///////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Background music
        bkMus = MediaPlayer.create(this, R.raw.a_bit_happy);
        bkMus.setLooping(true); //loop-playing
    }

    @Override
    protected void onResume() {
        super.onResume(); // always call superclass
        if (bkMus != null) bkMus.start(); // start playing
    }

    @Override
    protected void onPause(){
        super.onPause(); // always call superclass
        if (bkMus != null) bkMus.pause(); // pause playing
    }
}

