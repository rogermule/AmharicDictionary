package com.brainuptech.amharicdictionary.Database;

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

    Context context;
    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    public ArrayList<DictionaryEntitty> getWordsAmh(int count){
        SQLiteDatabase db = getReadableDatabase();

        String DB_Table = "AmharicDB";

        String[] column2 = {"*"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
        Cursor c = db.query(DB_Table, column2, null, null, null, null, null,count+","+100);

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

    public ArrayList<DictionaryEntitty> getWordsEng(int count){
        SQLiteDatabase db = getReadableDatabase();

        String DB_Table = "EnglishDB";

        String[] column2 = {"*"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
        Cursor c = db.query(DB_Table, column2, null, null, null, null, null, count+","+100);
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

    public DictionaryEntitty getDetailAmhWithoutEnc(int id){
        String db_table = "AmharicDB";

        SQLiteDatabase db = getReadableDatabase();
        DictionaryEntitty dict = new DictionaryEntitty();

        Cursor cur = db.rawQuery("select * from " + db_table + " where " + column[0] + "='" + id + "'", null);
        String word1,word2;
        if(cur.getCount()>0) {
            cur.moveToFirst();

            word1 = cur.getString(cur.getColumnIndex(cur.getColumnName(1)));
            word2 = cur.getString(cur.getColumnIndex(cur.getColumnName(2)));
            dict.setId(id);
            dict.setWord1(word1);
            dict.setDefinition(word2);
            db.close();
            return dict;
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




    public ArrayList<DictionaryEntitty> searchEng(String value, int count, int range1, int range2){
        SQLiteDatabase db = getReadableDatabase();
        String DB_Table = "EnglishDB";
        String[] column2 = {"*"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
/*        Cursor c = db.rawQuery("select * from " + DB_Table + " where " + column[1] + " like '" + value + "%' " +
                "LIMIT "+count+", 20",  null);*/

        Cursor c = db.rawQuery("SELECT * FROM " + DB_Table + " WHERE " + column[0] + " BETWEEN "+ range1 +" AND "+range2,  null);

        Log.i("Test", "row count: "+ c.getCount() + " \n Column count: "+c.getColumnCount());
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            Log.i("Test",c.getString(0));
            c.moveToPosition(i);
        }

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
/*
                        Log.i("Test 1", "Value "+value+"\nWord1"+decrypted_word1.toLowerCase()+ "\n"+ decrypted_word1.toLowerCase().startsWith("a"));
                        if(decrypted_word1.toLowerCase().startsWith(value)) {
                            dis.setId(Integer.parseInt(c.getString(c.getColumnIndex(c.getColumnName(0)))));
                            dis.setWord1(decrypted_word1);
                            dis.setDefinition(decrypted_word2);
                            found.add(dis);
                            Log.i("Found","Found " + dis.getId());
                        }*/
                        dis.setId(Integer.parseInt(c.getString(c.getColumnIndex(c.getColumnName(0)))));
                        dis.setWord1(decrypted_word1);
                        dis.setDefinition(decrypted_word2);
                        found.add(dis);
                        Log.i("Found","Found " + dis.getId());
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

    public ArrayList<DictionaryEntitty> searchAmh(String value, int count, int range1, int range2){
        SQLiteDatabase db = getReadableDatabase();
        String DB_Table = "AmharicDB";
        String[] column2 = {"*"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
/*        Cursor c = db.rawQuery("select * from " + DB_Table + " where " + column[1] + " like '" + value + "%' " +
                "LIMIT "+count+", 20",  null);*/

        Cursor c = db.rawQuery("SELECT * FROM " + DB_Table + " WHERE " + column[0] + " BETWEEN "+ range1 +" AND "+range2,  null);

        Log.i("Test", "row count: "+ c.getCount() + " \n Column count: "+c.getColumnCount());
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            Log.i("Test",c.getString(0));
            c.moveToPosition(i);
        }

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
/*
                        Log.i("Test 1", "Value "+value+"\nWord1"+decrypted_word1.toLowerCase()+ "\n"+ decrypted_word1.toLowerCase().startsWith("a"));
                        if(decrypted_word1.toLowerCase().startsWith(value)) {
                            dis.setId(Integer.parseInt(c.getString(c.getColumnIndex(c.getColumnName(0)))));
                            dis.setWord1(decrypted_word1);
                            dis.setDefinition(decrypted_word2);
                            found.add(dis);
                            Log.i("Found","Found " + dis.getId());
                        }*/
                        dis.setId(Integer.parseInt(c.getString(c.getColumnIndex(c.getColumnName(0)))));
                        dis.setWord1(decrypted_word1);
                        dis.setDefinition(decrypted_word2);
                        found.add(dis);
                        Log.i("Found","Found " + dis.getId());
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


}
