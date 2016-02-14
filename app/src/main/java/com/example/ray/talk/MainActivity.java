package com.example.ray.talk;

import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    private InputMethodManager inputMethodManager;
    private RelativeLayout mainLayout;

    private SQLiteDatabase mDb;

    private EditText meInput;
    private TextView meText;
    private TextView aiText;
    int number=0;


    //他クラスの宣言
    Reply Reply;
    Memory Memory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper hlpr = new DatabaseHelper(getApplicationContext());
        mDb = hlpr.getWritableDatabase();

        Reply = new Reply(getApplicationContext()); //reply
        Memory= new Memory(getApplicationContext());//memory
        Memory.memory(mDb);
        //mDb.execSQL("create table pattern(id integer primary key, word text, reply text);");

        meInput = (EditText)findViewById(R.id.meInput);//入力したもの
        meText = (TextView)findViewById(R.id.meText);//入力したのを描画するやつ
        aiText = (TextView)findViewById(R.id.aiText);//AIの描画

        //画面全体のレイアウト

        mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
        //キーボード表示を制御するためのオブジェクト
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //Log.d("MainActivity","success1");
        meInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    //キーボードを閉じる
                    inputMethodManager.hideSoftInputFromWindow(meInput.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);

                    /*String sql = "select * from pattern;";
                    try {
                        mDb.execSQL(sql);
                    } catch (SQLException e){
                        Log.e("ERROR", e.toString());
                    }*/
                    //反応
                    Reply.SmeInput = meInput.getText().toString();
                    Log.d("MConfirm", Reply.SmeInput);
                    Reply.MReply(mDb);//返信処理

                    aiText.setText(""+Reply.SmeOutput); //処理結果を代入して描画
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
