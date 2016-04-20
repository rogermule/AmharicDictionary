package com.brainuptech.amharicdictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Roger on 3/23/2016.
 */

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Hiding Title bar of this activity screen */
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

        Thread splash = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch(InterruptedException e){
                } finally {
                    getNextActivity();
                }
            }
        };

        splash.start();
    }

    private void getNextActivity() {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
