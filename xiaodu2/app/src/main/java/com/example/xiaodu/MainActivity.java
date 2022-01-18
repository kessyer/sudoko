package com.example.xiaodu;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.Button;

import java.lang.reflect.Field;

public class MainActivity extends Activity {

    public static boolean isStartByStart=true;
    public static String recordContinueDifficulty="";
    private Button btnContinue = null;
    private Button btnStart = null;
    private Button btnTopten = null;
    private Button btnExit = null;

    // 初始化界面控件
    public void init() {
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnTopten = (Button) findViewById(R.id.btnTopten);
        btnExit = (Button) findViewById(R.id.btnExit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); // 初始化界面控件
        MyOnClick(); // 界面按键监听事件
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (GameSetting.isChbMusic) {
            Intent musicIntent=new Intent(this,Music.class);
            startService(musicIntent);
        }
    }
    // 界面按键监听事件
    public void MyOnClick() {
        // btnContinue按键监听事件
        btnContinue.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                isStartByStart=false;
                if (!((MyView.unChange_Num==null))) {
                    startGame(Game.DIFContinue);
                }
                else {
                    Dialog myDialog=new AlertDialog.Builder(MainActivity.this).setTitle("提示信息：")
                            .setMessage("没有找到可以继续的游戏！").create();
                    myDialog.show();
                }
            }
        });
        // btnStart按键监听事件
        btnStart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                isStartByStart=true;
                recordContinueDifficulty="";
                Game.recordTime=0;
                seletedGameDifficulty();
            }
        });
        // btnTopten按键监听事件
        btnTopten.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(MainActivity.this,RecordRank.class);
                startActivity(myIntent);

            }
        });
        // btnExit按键监听事件
        btnExit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
    }

    private void seletedGameDifficulty() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.gameDifficulty)
                .setItems(R.array.difficulty,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                startGame(which);
                            }
                        }).show();
    }

    private void startGame(int which) {
        Intent difficultyIntent = new Intent(MainActivity.this, Game.class);
        difficultyIntent.putExtra("difficulty", which);
        startActivity(difficultyIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shu_du_game, menu);//加载选项菜单
        menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent settingIntent=new Intent(MainActivity.this, GameSetting.class);
                startActivity(settingIntent);
                return false;
            }
        });
        return true;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent musicIntent=new Intent(this,Music.class);
        stopService(musicIntent);
    }
}
