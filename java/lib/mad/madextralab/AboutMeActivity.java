package lib.mad.madextralab;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutMeActivity extends AppCompatActivity {
    String strIntro = "I am Ng Lai Ying, Libby!\n"+"I have many hobbies, such as drawing and play games\n"
            +"Recently, I am bothering about my hair which get oily so quickly!!!!!!!!";
    MediaPlayer bkMus = null;

    //Rotating photos
    public void RotatePhotoA(){
        RotateAnimation animR = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animR.setInterpolator(new LinearInterpolator());
        animR.setRepeatCount(Animation.INFINITE);
        animR.setDuration(3000);
    // Simple scaling the background image view
        ImageView imgV1 = ((ImageView) findViewById(R.id.imageView));
        imgV1.setScaleX(2); imgV1.setScaleY(2);
    // Start animating (rotating) the background image view
        imgV1.startAnimation(animR);
    }
    public void RotatePhotoB(){
        RotateAnimation animR = new RotateAnimation(360f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animR.setInterpolator(new LinearInterpolator());
        animR.setRepeatCount(Animation.INFINITE);
        animR.setDuration(800);
        // Simple scaling the background image view
        ImageView imgV2 = ((ImageView) findViewById(R.id.imageView2));
        imgV2.setScaleX(2); imgV2.setScaleY(2);
        // Start animating (rotating) the background image view
        imgV2.startAnimation(animR);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        //Text of about me
        final TextView Tv = (TextView) findViewById(R.id.textIntro);
        Tv.setText(strIntro);

        //Background music
        bkMus = MediaPlayer.create(this, R.raw.frogspiano);
        bkMus.setLooping(true); //loop-playing

        //Rotate photos
        RotatePhotoA();
        RotatePhotoB();

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
