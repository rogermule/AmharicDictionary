package com.brainuptech.amharicdictionary;

import android.app.Application;

import com.brainuptech.amharicdictionary.Database.MyDatabase;

/**
 * Created by Roger on 4/21/2016.
 */
public class Dictionary extends Application {
    private static final String ADMOB__BANNER_AD_UNIT_ID = "ca-app-pub-6608952583904649/4380270017";
    private static final String ADMOB_BANNER_APP_ID = "ca-app-pub-6608952583904649~5637765614";
    private static final String ADMOB__NATIVE_AD_UNIT_ID = "ca-app-pub-6608952583904649/1426803618";
    private static final String ADMOB_NATIVE_APP_ID = "ca-app-pub-6608952583904649~5637765614";

    public static MyDatabase myDB;
    @Override
    public void onCreate() {
        super.onCreate();
        myDB = new MyDatabase(this);
    }
}
