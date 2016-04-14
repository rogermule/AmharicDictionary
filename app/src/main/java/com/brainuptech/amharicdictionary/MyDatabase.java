package com.brainuptech.amharicdictionary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Roger on 3/31/2016.
 */

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "english.sqlite";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getW() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"word1", "word2"};
        String sqlTables = "EnglishDB";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);

        c.moveToFirst();

        db.close();
        return c;
    }

    public ArrayList<DictionaryEntitty> getWords(){
        String DB_Table = "EnglishDB";
        SQLiteDatabase db = getReadableDatabase();
        String[] column = {"word1", "word2"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
        Cursor c = db.query(DB_Table, column, null, null, null,null,null);

        if(c.getCount()>0){
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++){
                c.moveToPosition(i);
                DictionaryEntitty dis = new DictionaryEntitty();
                dis.setWord1(c.getString(c.getColumnIndex("word1")));
                dis.setDefinition(c.getString(c.getColumnIndex("word2")));
                found.add(dis);
            }
        }
        db.close();
        return found;
    }

    public ArrayList<DictionaryEntitty> getWordsAmharic(){
        String DB_Table = "EnglishDB";
        SQLiteDatabase db = getReadableDatabase();
        String[] column = {"word1", "word2"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
        Cursor c = db.query(DB_Table, column, null, null, null,null,null);

        if(c.getCount()>0){
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++){
                c.moveToPosition(i);
                DictionaryEntitty dis = new DictionaryEntitty();
                dis.setWord1(c.getString(c.getColumnIndex("word2")));
                dis.setDefinition(c.getString(c.getColumnIndex("word1")));

                found.add(dis);
            }
        }
        db.close();
        return found;
    }

    public DictionaryEntitty getDetail(String word, String lang){
        SQLiteDatabase db = getReadableDatabase();
        DictionaryEntitty dict = new DictionaryEntitty();
        String DB_Table = "EnglishDB";
        if(lang.equals("eng")) {
            Cursor cur = db.rawQuery("select * from " + DB_Table + " where word1='" + word + "'", null);
            cur.moveToFirst();
            dict.setWord1(word);
            dict.setDefinition(cur.getString(cur.getColumnIndex("word2")));
            db.close();
            return dict;
        }
        else if(lang.equals("amh")){
            Cursor cur = db.rawQuery("select * from " + DB_Table + " where word2='" + word+"'", null);
            cur.moveToFirst();
            dict.setWord1(word);
            dict.setDefinition(cur.getString(cur.getColumnIndex("word1")));
            db.close();
            return dict;
        }
        db.close();
        return null;
    }

    public DictionaryEntitty getEnglishDetail(String word1){
        SQLiteDatabase db = getReadableDatabase();
        DictionaryEntitty dict = new DictionaryEntitty();

        String DB_Table = "EnglishDB";
        Cursor cur = db.rawQuery("select * from " + DB_Table + " where word1='" + word1+"'", null);
        Log.i("Test", cur.getCount()+"");
        Log.i("Test", word1);
        cur.moveToFirst();
        dict.setWord1(word1);
        dict.setDefinition(cur.getString(cur.getColumnIndex("word2")));
        db.close();
        return dict;
    }

    public DictionaryEntitty getAmharicDetail(String word){
        SQLiteDatabase db = getReadableDatabase();
        DictionaryEntitty dict = new DictionaryEntitty();
        String DB_Table = "EnglishDB";
        Cursor cur = db.rawQuery("select * from " + DB_Table + " where word2='" + word+"'", null);
        cur.moveToFirst();
        dict.setWord1(word);
        dict.setDefinition(cur.getString(cur.getColumnIndex("word1")));
        db.close();
        return dict;
    }

}
