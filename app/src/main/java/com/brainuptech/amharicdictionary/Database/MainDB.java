package com.brainuptech.amharicdictionary.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.brainuptech.amharicdictionary.Entities.DictionaryEntitty;

import java.util.ArrayList;

/**
 * Created by Roger on 4/25/2016.
 */

public class MainDB {

    public static String TAG = "Amharic Dictionray";
    Context context;
    DBHelper dbHelper;
    SQLiteDatabase myDatabase;
    public MainDB(Context context){
        this.context = context;
        dbHelper = new DBHelper(context);
        myDatabase = dbHelper.getWritableDatabase();
    }


    public long insert(String DB_Table,ContentValues cv){
        long state = myDatabase.insert(DB_Table, null, cv);
        return state;
    }
    public long Delete_All(String DB_Table){
        long state = myDatabase.delete(DB_Table, null, null);
        return state;
    }
    public long remove(String DB_Table,int id){
        String[] args = {""+id};
        long val = myDatabase.delete(DB_Table, "id = ?", args);
        return val;
    }

    public long update(String DB_Table,ContentValues cv,int id){
        Log.i(TAG, "Updating Table: "+DB_Table);
        String[] args = {""+id};
        long state = myDatabase.update(DB_Table, cv, "id = ?", args);
        Log.i(TAG, "Updating Data: "+cv.toString());
        return state;
    }

    public int count(String DB_Table){

        Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
        if(c != null){
            return c.getCount();
        }else{
            return 0;
        }
    }
    public Cursor getAll(String DB_Table){
        Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
        return c;
    }

    public String get_Value_At_Top(String DB_Table,String column){
        String str = "";
        try {
            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
            c.moveToFirst();
            str = c.getString(c.getColumnIndex(column));
        }catch (Exception e){

        }

        return str;
    }

    public String get_Value_At_Bottom(String DB_Table,String column){
        String str = "";
        try{
            Cursor c = myDatabase.query(DB_Table, getColumns(DB_Table), null, null, null, null, null);
            c.moveToLast();
            str = c.getString(c.getColumnIndex(column));
        }catch (Exception e){

        }
        return str;
    }

    public Cursor get_value_by_ID (String DB_Table,String id){
        Cursor cur = myDatabase.rawQuery("select * from " + DB_Table + " where id=" + id, null);
        return cur;
    }



    public ArrayList<DictionaryEntitty> getWordsAmh(){
        String DB_Table = DBHelper.TABLE_AMHARIC;

        String[] column2 = {"_id","word1","word2"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
        Cursor c = myDatabase.query(DB_Table, column2, null, null, null, null, null);
        DictionaryEntitty dis;
        if(c.getCount()>0){
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++){
                c.moveToPosition(i);

                dis = new DictionaryEntitty();
                dis.setId(Integer.parseInt(c.getString(c.getColumnIndex("_id"))));
                dis.setWord1(c.getString(c.getColumnIndex("word1")));
                dis.setDefinition(c.getString(c.getColumnIndex("word2")));
                found.add(dis);
            }

        }
        return found;
    }


    public ArrayList<DictionaryEntitty> getWordsEng(){
        String DB_Table = DBHelper.TABLE_ENGLISH;

        String[] column2 = {"_id","word1","word2"};

        ArrayList<DictionaryEntitty> found = new ArrayList<DictionaryEntitty>();
        Cursor c = myDatabase.query(DB_Table, column2, null, null, null, null, null);
        DictionaryEntitty dis;
        if(c.getCount()>0){
            c.moveToFirst();
            for(int i=0;i<c.getCount();i++){
                c.moveToPosition(i);
                dis = new DictionaryEntitty();
                dis.setId(Integer.parseInt(c.getString(c.getColumnIndex("_id"))));
                dis.setWord1(c.getString(c.getColumnIndex("word1")));
                dis.setDefinition(c.getString(c.getColumnIndex("word2")));
                found.add(dis);
            }
        }
        return found;
    }



    public void dispose(){
        myDatabase.close();
    }

    public String[] getColumns(String DBTable){
        String[] strs = {"_id","word1","word2"};
        return strs;
    }
}
