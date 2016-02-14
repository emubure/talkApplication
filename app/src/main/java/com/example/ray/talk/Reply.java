package com.example.ray.talk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by Ray on 2015/11/15.
 */
//�ԐM�����AMemory�N���X��sqlite�ɂ��������J�������������ĕԐM
public class Reply {
    private SQLiteDatabase mDb;

    static String SmeInput;
    static String SmeOutput;
    DatabaseHelper hlpr;
    Context con;

    public Reply(Context c) {
        con = c;
    }

    public void MReply(SQLiteDatabase mDb) {
        Log.d("RConfirm", "success");


        final String[] columns = {"word", "reply"};
        String where = "word = ?";//"word LIKE '%' || "+SmeInput+" || '%'";
        String param = "おはよう";//SmeInput;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor c = null;

        try {
            c = mDb.query(DatabaseHelper.TABLE_NAME, //
                    columns, //
                    where, //
                    new String[]{param}, //
                    groupBy, //
                    having, //
                    orderBy);

            Log.d("RConfirm", "success1");
            while (c.moveToNext()) {
                Log.d("RConfirm", "success2");
                String reply = c.getString(c.getColumnIndex("reply"));
                Log.d("RConfirm", reply);
                SmeOutput = reply.toString();
                Log.d("RConfirm", SmeOutput);
            }
        } catch (SQLiteException e) {
            Log.e("SQLError", e.toString());
        } finally {
            if (c != null) {
                c.close();
            }
        }

       /* try {
            Cursor cursor = mDb.query("pattern", colums, selection, selectionArgs, groupBy, having, orderBy);
            boolean isEof = cursor.moveToFirst();
            while(isEof){
                SmeInput = cursor.getString(2);
                isEof = cursor.moveToNext();
            }
            cursor.close();
            //�������ʂ���ĕ`��
            Log.e("Config", SmeInput);
        } catch(SQLiteException e) {
            Log.e("SQLERROR", e.toString());
        } finally {
            mDb.close();
        }*/



        /*Cursor cursor = mDb.query("pattern", new String[] {"word", "reply"}, "word LIKE '%' ||"+SmeInput+"|| '%'", null, null, null, null);
        //�Q�Ɛ����ԏ��߂�
        boolean isEof = cursor.moveToFirst();
        //�f�[�^���擾���Ă���
        while(isEof){
            //��������J����http://tomcky.hatenadiary.jp/entry/2013/08/25/235502
            //�擾����mainactivity�Ɏ擾�����̓n���Ă����łŉ�ʂɕ\�����鏈�����
            Log.d("Reply", "" + cursor.getInt(cursor.getColumnIndex("reply")));
            isEof = cursor.moveToNext();
        }*/
    }
}
