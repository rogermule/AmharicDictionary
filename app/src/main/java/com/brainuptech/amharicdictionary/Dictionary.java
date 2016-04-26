package com.brainuptech.amharicdictionary;

import android.app.Application;

import com.brainuptech.amharicdictionary.Database.MyDatabase;

/**
 * Created by Roger on 4/21/2016.
 */
public class Dictionary extends Application {

    public static MyDatabase myDB;
    @Override
    public void onCreate() {
        super.onCreate();
        myDB = new MyDatabase(this);
    }
}
