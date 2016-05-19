package com.brainuptech.amharicdictionary;

import android.app.ProgressDialog;
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
        myDialog.setMessage("Loading 60,000 words.... \nPlease wait");
        myDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        myDialog.setMax(60000);

        amharicwords  = Dictionary.myDB.getWordsAmh();
        //englishwords  = Dictionary.myDB.getWordsEng(0);

        getNextActivity();
  /*      myDialog.show();

        Thread splash = new Thread(){
            @Override
            public void run() {
                try {
                    amharicwords  = Dictionary.myDB.getWordsAmh(0);
                    myDialog.setProgress(23000);
                    englishwords  = Dictionary.myDB.getWordsEng(0);
                    myDialog.setProgress(60000);

                        //sleep(2000);
                } catch(Exception e){
                } finally {
                    getNextActivity();
                    myDialog.cancel();
                    finish();
                }
            }
        };

        splash.start();*/
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
