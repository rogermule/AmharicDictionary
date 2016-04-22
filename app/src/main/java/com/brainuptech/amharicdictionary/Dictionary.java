package com.brainuptech.amharicdictionary;

import android.app.Application;

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
