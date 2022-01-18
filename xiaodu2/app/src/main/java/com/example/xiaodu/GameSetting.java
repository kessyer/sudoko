package com.example.xiaodu;



import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.app.Activity;
import android.content.Intent;
public class GameSetting extends Activity {
    public  CheckBox chbMusic=null;
    public static boolean isChbMusic=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);
        chbMusic=(CheckBox) findViewById(R.id.chbMusic);
        if (isChbMusic) {
            chbMusic.setChecked(true);
        }
        else {
            chbMusic.setChecked(false);
        }
        chbMusic.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isChbMusic=true;
                    Intent musicIntent=new Intent(GameSetting.this,Music.class);
                    musicIntent.setPackage(getPackageName());
                    startService(musicIntent);
                }
                else {
                    isChbMusic=false;
                    Intent musicIntent=new Intent(GameSetting.this,Music.class);
                    stopService(musicIntent);
                }
            }
        });
    }
}
