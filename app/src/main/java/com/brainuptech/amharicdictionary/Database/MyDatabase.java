package com.brainuptech.amharicdictionary.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Base64;
import android.util.Log;

import com.brainuptech.amharicdictionary.Enc;
import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Roger on 3/31/2016.
 */

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "english.sqlite";
    private static final int DATABASE_VERSION = 1;
    public static final String[] column = {"_id "," word1 ", " word2"};
    public static byte[] keycode  = Enc.generateKey("roger");

    MainDB mainDB;
    Context context;
    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mainDB = new MainDB(context);
        this.context = context;
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

    public ArrayList<DictionaryEntitty> getWordsAmh(){
        SQLiteDatabase db = getReadableDatabase();

        String DB_Table = "AmharicDB";

        String[] column2 = {"*"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
        Cursor c = db.query(DB_Table, column2, null, null, null, null, null);

        byte[] byte_word1,byte_word2;
        String word1,word2;
        String decrypted_word1,decrypted_word2;
        if(c.getCount()>0){
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++){
                c.moveToPosition(i);

                DictionaryEntitty dis = new DictionaryEntitty();
                word1 = c.getString(c.getColumnIndex(c.getColumnName(1)));
                word2 = c.getString(c.getColumnIndex(c.getColumnName(2)));

                try {
                    byte_word1 = Enc.decodeFile(keycode, Base64.decode(word1, 0));
                    byte_word2 = Enc.decodeFile(keycode, Base64.decode(word2, 0));

                    if(byte_word1!=null | byte_word2!=null) {
                        decrypted_word1 = new String(byte_word1,"UTF-8");
                        decrypted_word2 = new String(byte_word2);

                        dis.setId(Integer.parseInt(c.getString(c.getColumnIndex(c.getColumnName(0)))));
                        dis.setWord1(decrypted_word1);
                        dis.setDefinition(decrypted_word2);
                        found.add(dis);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        db.close();
        return found;
    }

    public ArrayList<DictionaryEntitty> getWordsEng(){
        SQLiteDatabase db = getReadableDatabase();

        String DB_Table = "EnglishDB";

        String[] column2 = {"*"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
        Cursor c = db.query(DB_Table, column2, null, null, null, null, null);
        Log.i("Test", "row count: "+ c.getCount() + " \n Column count: "+c.getColumnCount());

        byte[] byte_word1,byte_word2;
        String word1,word2;
        String decrypted_word1,decrypted_word2;
        if(c.getCount()>0){
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++){
                c.moveToPosition(i);

                DictionaryEntitty dis = new DictionaryEntitty();
                word1 = c.getString(c.getColumnIndex(c.getColumnName(1)));
                word2 = c.getString(c.getColumnIndex(c.getColumnName(2)));

                try {
                    byte_word1 = Enc.decodeFile(keycode, Base64.decode(word1, 0));
                    byte_word2 = Enc.decodeFile(keycode, Base64.decode(word2, 0));

                    if(byte_word1!=null | byte_word2!=null) {
                        decrypted_word1 = new String(byte_word1);
                        decrypted_word2 = new String(byte_word2,"UTF-16BE");

                        dis.setId(Integer.parseInt(c.getString(c.getColumnIndex(c.getColumnName(0)))));
                        dis.setWord1(decrypted_word1);
                        dis.setDefinition(decrypted_word2);
                        found.add(dis);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        db.close();
        return found;
    }



    public void getWordsAmhToAdd(){

        SQLiteDatabase db = getReadableDatabase();
        String DB_Table = "AmharicDB";
        String[] column2 = {"*"};

        Cursor c = db.query(DB_Table, column2, null, null, null, null, null);

        byte[] byte_word1,byte_word2;
        String word1,word2;
        String decrypted_word1,decrypted_word2;
        if(c.getCount()>0){
            c.moveToFirst();
            ContentValues cv;
            for(int i=0;i<c.getCount();i++){
                c.moveToPosition(i);
                cv = new ContentValues();
                word1 = c.getString(c.getColumnIndex(c.getColumnName(1)));
                word2 = c.getString(c.getColumnIndex(c.getColumnName(2)));

                try {
                    byte_word1 = Enc.decodeFile(keycode, Base64.decode(word1, 0));
                    byte_word2 = Enc.decodeFile(keycode, Base64.decode(word2, 0));

                    if(byte_word1!=null | byte_word2!=null) {
                        decrypted_word1 = new String(byte_word1,"UTF-8");
                        decrypted_word2 = new String(byte_word2);

                        cv.put(DBHelper.UID, c.getString(c.getColumnIndex(c.getColumnName(0))));
                        cv.put(DBHelper.WORD1, decrypted_word1);
                        cv.put(DBHelper.WORD2, decrypted_word2);
                        mainDB.insert(DBHelper.TABLE_AMHARIC, cv);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        db.close();
    }



    public void getWordsEngToAdd(){
        SQLiteDatabase db = getReadableDatabase();

        String DB_Table = DBHelper.TABLE_ENGLISH;

        String[] column2 = {"*"};

        Cursor c = db.query(DB_Table, column2, null, null, null, null, null);

        byte[] byte_word1,byte_word2;
        String word1,word2;
        String decrypted_word1,decrypted_word2;
        if(c.getCount()>0){
            c.moveToFirst();
            ContentValues cv;
            for(int i=0;i<c.getCount();i++){
                c.moveToPosition(i);

                cv = new ContentValues();
                word1 = c.getString(c.getColumnIndex(c.getColumnName(1)));
                word2 = c.getString(c.getColumnIndex(c.getColumnName(2)));

                try {
                    byte_word1 = Enc.decodeFile(keycode, Base64.decode(word1, 0));
                    byte_word2 = Enc.decodeFile(keycode, Base64.decode(word2, 0));

                    if(byte_word1!=null | byte_word2!=null) {
                        decrypted_word1 = new String(byte_word1);
                        decrypted_word2 = new String(byte_word2,"UTF-16BE");

                        cv.put(DBHelper.UID, c.getString(c.getColumnIndex(c.getColumnName(0))));
                        cv.put(DBHelper.WORD1,decrypted_word1);
                        cv.put(DBHelper.WORD2,decrypted_word2);
                        mainDB.insert(DBHelper.TABLE_ENGLISH,cv);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        db.close();
    }



    public DictionaryEntitty getDetailEng(int id){
        String db_table = "EnglishDB";

        SQLiteDatabase db = getReadableDatabase();
        DictionaryEntitty dict = new DictionaryEntitty();

        Cursor cur = db.rawQuery("select * from " + db_table + " where " + column[0] + "='" + id + "'", null);
        byte[] byte_word1,byte_word2;
        String word1,word2;
        String decrypted_word1,decrypted_word2;
        if(cur.getCount()>0) {
            cur.moveToFirst();

            word1 = cur.getString(cur.getColumnIndex(cur.getColumnName(1)));
            word2 = cur.getString(cur.getColumnIndex(cur.getColumnName(2)));


            try {
                byte_word1 = Enc.decodeFile(keycode, Base64.decode(word1, 0));
                byte_word2 = Enc.decodeFile(keycode, Base64.decode(word2, 0));

                if(byte_word1!=null | byte_word2!=null) {
                    decrypted_word1 = new String(byte_word1);
                    decrypted_word2 = new String(byte_word2,"UTF-16BE");

                    dict.setId(id);
                    dict.setWord1(decrypted_word1);
                    dict.setDefinition(decrypted_word2);
                    db.close();
                    return dict;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        db.close();
        return null;
    }

    public DictionaryEntitty getDetailAmh(int id){
        String db_table = "AmharicDB";

        SQLiteDatabase db = getReadableDatabase();
        DictionaryEntitty dict = new DictionaryEntitty();

        Cursor cur = db.rawQuery("select * from " + db_table + " where " + column[0] + "='" + id + "'", null);
        byte[] byte_word1,byte_word2;
        String word1,word2;
        String decrypted_word1,decrypted_word2;
        if(cur.getCount()>0) {
            cur.moveToFirst();

            word1 = cur.getString(cur.getColumnIndex(cur.getColumnName(1)));
            word2 = cur.getString(cur.getColumnIndex(cur.getColumnName(2)));

            try {
                byte_word1 = Enc.decodeFile(keycode, Base64.decode(word1, 0));
                byte_word2 = Enc.decodeFile(keycode, Base64.decode(word2, 0));

                if(byte_word1!=null | byte_word2!=null) {
                    decrypted_word1 = new String(byte_word1,"UTF-8");
                    decrypted_word2 = new String(byte_word2);

                    dict.setId(id);
                    dict.setWord1(decrypted_word1);
                    dict.setDefinition(decrypted_word2);
                    db.close();
                    return dict;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        db.close();
        return null;
    }



    public ArrayList<DictionaryEntitty> getWordsAmhWithoutEnc(){
        SQLiteDatabase db = getReadableDatabase();

        String DB_Table = "AmharicDB";

        String[] column2 = {"*"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
        Cursor c = db.query(DB_Table, column2, null, null, null, null, null);
/*
        byte[] byte_word1,byte_word2;
        String word1,word2;
        String decrypted_word1,decrypted_word2;*/
        if(c.getCount()>0){
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++){
                c.moveToPosition(i);

                DictionaryEntitty dis = new DictionaryEntitty();
   /*             word1 = c.getString(c.getColumnIndex(c.getColumnName(1)));
                word2 = c.getString(c.getColumnIndex(c.getColumnName(2)));

                try {
                    byte_word1 = Enc.decodeFile(keycode, Base64.decode(word1, 0));
                    byte_word2 = Enc.decodeFile(keycode, Base64.decode(word2, 0));

                    if(byte_word1!=null | byte_word2!=null) {
                        decrypted_word1 = new String(byte_word1,"UTF-8");
                        decrypted_word2 = new String(byte_word2);
*/
                        dis.setId(Integer.parseInt(c.getString(c.getColumnIndex(c.getColumnName(0)))));
                        dis.setWord1(c.getString(c.getColumnIndex(c.getColumnName(1))));
                        dis.setDefinition(c.getString(c.getColumnIndex(c.getColumnName(2))));
                        found.add(dis);
                    }

        }
        db.close();
        return found;
    }


    public ArrayList<DictionaryEntitty> getWordsEngWithoutEnc(){
        SQLiteDatabase db = getReadableDatabase();

        String DB_Table = "EnglishDB";

        String[] column2 = {"*"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
        Cursor c = db.query(DB_Table, column2, null, null, null, null, null);
        Log.i("Test", "row count: "+ c.getCount() + " \n Column count: "+c.getColumnCount());

/*        byte[] byte_word1,byte_word2;
        String word1,word2;
        String decrypted_word1,decrypted_word2;*/
        if(c.getCount()>0){
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++){
                c.moveToPosition(i);

                DictionaryEntitty dis = new DictionaryEntitty();
/*                word1 = c.getString(c.getColumnIndex(c.getColumnName(1)));
                word2 = c.getString(c.getColumnIndex(c.getColumnName(2)));

                try {
                    byte_word1 = Enc.decodeFile(keycode, Base64.decode(word1, 0));
                    byte_word2 = Enc.decodeFile(keycode, Base64.decode(word2, 0));

                    if(byte_word1!=null | byte_word2!=null) {
                        decrypted_word1 = new String(byte_word1);
                        decrypted_word2 = new String(byte_word2,"UTF-16BE");*/

                        dis.setId(Integer.parseInt(c.getString(c.getColumnIndex(c.getColumnName(0)))));
                        dis.setWord1(c.getString(c.getColumnIndex(c.getColumnName(1))));
                        dis.setDefinition(c.getString(c.getColumnIndex(c.getColumnName(2))));
                        found.add(dis);
            }
        }
        db.close();
        return found;
    }

}
