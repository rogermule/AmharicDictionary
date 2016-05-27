package com.brainuptech.amharicdictionary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;

import java.util.ArrayList;

/**
 * Created by Roger on 3/23/2016.
 */

public class Splash extends AppCompatActivity {
    public static final String PREFS_NAME = "rateCount";

    public static ArrayList<DictionaryEntitty> amharicwords;
    //public static ArrayList<DictionaryEntitty> englishwords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Hiding Title bar of this activity screen */
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);


        final ProgressDialog myDialog = new ProgressDialog(this);
        myDialog.setTitle(R.string.app_name);
        myDialog.setMessage("Loading.... \nPlease wait");
        myDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        myDialog.setMax(23000);
        //myDialog.show();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if(!settings.getBoolean("hasRate",false)) {
            SharedPreferences.Editor editor = settings.edit();
            int count = settings.getInt("count", 0);

            if (settings.getInt("count", 0) == 0) {
                editor.putInt("count", 1);
                editor.commit();
                Log.i("Rate Me","Count is made 1");
            } else {
                editor.putInt("count", count+1);
                editor.commit();
                Log.i("Rate Me","Count is " + count);
            }
        }

        Thread splash = new Thread(){
            @Override
            public void run() {
                try {
                    amharicwords  = Dictionary.myDB.getWordsAmhWithoutEnc();
                    //myDialog.setProgress(23000);
//                    englishwords  = Dictionary.myDB.getWordsEng(0);
//                    myDialog.setProgress(60000);

                        //sleep(2000);
                } catch(Exception e){
                } finally {
                    getNextActivity();
                    //myDialog.cancel();
                    finish();
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
