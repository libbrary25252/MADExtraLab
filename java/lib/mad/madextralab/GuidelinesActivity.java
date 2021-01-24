package lib.mad.madextralab;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class GuidelinesActivity extends AppCompatActivity {
    String strGrideline = "\nGuideline:\n\n" + "Cats need your HELPPP!!!\nThey are so lonely (´；д；`) \n\n"
            + "Tap the screen as fast as possible within the time limit.\n" +
            "\n\n\n The MORE you TAP, the MORE the cat will feel COMFORTABLE~! ";
    MediaPlayer bkMus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidelines);

        //Text of the guideline
        final TextView tV = (TextView) findViewById(R.id.textView);
        tV.setText(strGrideline);

        //Background music
        bkMus = MediaPlayer.create(this, R.raw.frogspiano);
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
