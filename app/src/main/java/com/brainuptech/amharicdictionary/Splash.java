package com.brainuptech.amharicdictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;

import java.util.ArrayList;

/**
 * Created by Roger on 3/23/2016.
 */

public class Splash extends AppCompatActivity {

    public static ArrayList<DictionaryEntitty> amharicwords;
    public static ArrayList<DictionaryEntitty> englishwords;

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
                    amharicwords = Dictionary.myDB.getWordsAmh();
                    englishwords  = Dictionary.myDB.getWordsEng();
                    //sleep(2000);
                } catch(Exception e){
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
