package com.brainuptech.amharicdictionary.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Roger on 4/25/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "dictionary";
    private final static int DATABASE_VERSION = 2;
    Context context;

    public static final String TABLE_AMHARIC = "AmharicDB";
    public static final String TABLE_ENGLISH = "EnglishDB";
    public static final String UID = "_id";
    public static final String WORD1 = "word1";
    public static final String WORD2 = "word2";

    public static final String CREATE_AMHARIC = "CREATE TABLE IF NOT EXISTS " + TABLE_AMHARIC
            + "( "+ UID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + WORD1 + " VARCHAR(255),"
            +WORD2 + " VARCHAR(255));";

    public static final String CREATE_ENGLISH = "CREATE TABLE IF NOT EXISTS " + TABLE_ENGLISH
            + "( "+ UID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + WORD1 + " VARCHAR(255),"
            +WORD2 + " VARCHAR(255));";

    public static final String DROP_TABLE_AMHARIC = "DROP TABLE IF EXISTS " + TABLE_AMHARIC;
    public static final String DROP_TABLE_ENGLISH = "DROP TABLE IF EXISTS " + TABLE_ENGLISH;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_AMHARIC);
        db.execSQL(CREATE_ENGLISH);
        Log.i(MainDB.TAG,"New Database with tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_AMHARIC);
        db.execSQL(DROP_TABLE_ENGLISH);
        onCreate(db);
    }
}
