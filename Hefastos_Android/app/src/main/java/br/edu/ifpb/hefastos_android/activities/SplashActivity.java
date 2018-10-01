package br.edu.ifpb.hefastos_android.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import br.edu.ifpb.hefastos_android.R;

public class SplashActivity extends AppCompatActivity implements Runnable {

    private static int splashTime = 3000;
    private ImageView image;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        image = (ImageView) findViewById(R.id.image);

        image.setBackgroundResource(R.drawable.loader);

        AnimationDrawable load = (AnimationDrawable) image.getBackground();
        load.start();

        new Handler().postDelayed(this, splashTime);
    }


    @Override
    public void run() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
