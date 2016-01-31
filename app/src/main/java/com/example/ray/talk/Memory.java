package com.example.ray.talk;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Ray on 2015/11/15.
 */
//返信パターンをとにかく記録
public class Memory {

    private SQLiteDatabase mDb;
    DatabaseHelper hlpr;
    Context c;

    public Memory(Context con){

        c = con;
    }

    public void r(String str1, String str2){
        hlpr = new DatabaseHelper(c);
        mDb = hlpr.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("word", str1);
        values.put("reply",str2);
        try {
            mDb.insert("pattern", null, values);
        } finally {
            mDb.close();
        }
    }
    public void memory(){
        r("おはよう","おはようございます");
        r("こんにちは", "こんにちは。");
        r("こんばんは", "こんばんは。");
    }
}