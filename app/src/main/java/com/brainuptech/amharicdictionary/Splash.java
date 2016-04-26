package com.brainuptech.amharicdictionary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.brainuptech.amharicdictionary.Database.DBHelper;
import com.brainuptech.amharicdictionary.Database.MainDB;
import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;

import java.util.ArrayList;

/**
 * Created by Roger on 3/23/2016.
 */

public class Splash extends AppCompatActivity {


    public static ArrayList<DictionaryEntitty> amharicwords;
    public static ArrayList<DictionaryEntitty> englishwords;
    MainDB mainDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Hiding Title bar of this activity screen */
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);
        mainDB = new MainDB(this);
        final ProgressDialog myDialog = new ProgressDialog(this);

        myDialog.setTitle(R.string.app_name);
        myDialog.setMessage("Loading 60,000 words.... \nPlease wait");
        myDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        myDialog.setMax(60000);
//        myDialog.show();

        Thread splash = new Thread(){
            @Override
            public void run() {
                try {
//                    englishwords  = Dictionary.myDB.getWordsEng();
//                    amharicwords  = Dictionary.myDB.getWordsAmh();

                    Log.i(MainDB.TAG,"English DB table count: " + mainDB.count(DBHelper.TABLE_ENGLISH));
                    Log.i(MainDB.TAG, "Amharic DB table count: " + mainDB.count(DBHelper.TABLE_AMHARIC));

                    if(mainDB.count(DBHelper.TABLE_AMHARIC)==0) {
                        Log.i(MainDB.TAG, "word add");
                        Dictionary.myDB.getWordsAmhToAdd();
                        Log.i(MainDB.TAG, "word add finished!");
                    }
                    myDialog.setProgress(23000);
                    if(mainDB.count(DBHelper.TABLE_ENGLISH)==0) {
                        Log.i(MainDB.TAG, "word add");
                        Dictionary.myDB.getWordsEngToAdd();
                        Log.i(MainDB.TAG, "word add finished!");
                    }
                    else
                        Log.i(MainDB.TAG, "Data already inserted");
                    myDialog.setProgress(60000);

                    amharicwords = mainDB.getWordsAmh();
                    englishwords = mainDB.getWordsEng();
                        //sleep(2000);
                } catch(Exception e){
                } finally {
                    getNextActivity();
                    myDialog.cancel();
                    finish();
                }
            }
        };

        splash.start();
    }

    private void getNextActivity() {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
        mainDB.dispose();
    }
}
