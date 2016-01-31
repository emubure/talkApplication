package com.example.ray.talk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ray on 2015/11/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "talkdb";
    private static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "create table pattern ( " +
                                           "id integer primary key autoincrement, "+
                                           "word text not null, " +
                                           "reply text not null);";
    static final String DROP_TABLE = "drop table pattern;";

    public DatabaseHelper(Context c){
        super(c, DB_NAME, null, DB_VERSION);
    }
    /**
     *データベースファイル初回使用時に実行される処理
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        //テーブル作成時のクエリを発行
        db.execSQL(CREATE_TABLE);
    }

    /**
     * データベースのバージョンアップ時に実行される処理
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //テーブルの破棄と再作成
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
