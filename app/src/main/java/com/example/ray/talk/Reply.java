package com.example.ray.talk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Ray on 2015/11/15.
 */
//返信処理、Memoryクラスでsqliteについかしたカルムを検索して返信
public class Reply {
    private SQLiteDatabase mDb;

    static  String SmeInput = "";
    DatabaseHelper hlpr;
    Context con;

    public Reply(Context c){
        con = c;
    }

    public void MReply(){
        hlpr  = new DatabaseHelper(con);
        mDb = hlpr.getWritableDatabase();

        String[] colums = {"word ","reply"};
        String selection = SmeInput;//"word LIKE '%' || "+SmeInput+" || '%'";
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        try {
            Cursor cursor = mDb.query("pattern", colums, selection, selectionArgs, groupBy, having, orderBy);
            StringBuilder text = new StringBuilder();
            while (cursor.moveToNext()) {
                String SmeInput = cursor.getString(1);
            }
        } catch(SQLiteException e) {
            Log.e("SQLERROR", e.toString());
        } finally {
            mDb.close();
        }

        /*Cursor cursor = mDb.query("pattern", new String[] {"word", "reply"}, "word LIKE '%' ||"+SmeInput+"|| '%'", null, null, null, null);
        //参照先を一番初めに
        boolean isEof = cursor.moveToFirst();
        //データを取得していく
        while(isEof){
            //ここから開発中http://tomcky.hatenadiary.jp/entry/2013/08/25/235502
            //取得してmainactivityに取得したの渡してそこでで画面に表示する処理作る
            Log.d("Reply", "" + cursor.getInt(cursor.getColumnIndex("reply")));
            isEof = cursor.moveToNext();
        }*/
    }
}
