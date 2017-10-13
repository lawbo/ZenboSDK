package com.asus.robotdevsample;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.asus.robotframework.API.RobotCallback;
import com.asus.robotframework.API.RobotCmdState;
import com.asus.robotframework.API.RobotErrorCode;
import com.robot.asus.robotactivity.RobotActivity;

public class MainActivity extends Activity {

    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button)findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(myIntent);
            }
        });

        bt2 = (Button)findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, VisionRequestDetectPerson.class);
                startActivity(myIntent);
            }
        });

        bt3 = (Button)findViewById(R.id.button3);
        bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, sale.class);
                startActivity(myIntent);
            }
        });
        
        bt4 = (Button)findViewById(R.id.button4);
        bt4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri = Uri.parse("https://itunes.apple.com/tw/app/zenbo-master/id1179640818?l=zh&mt=8"); 
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                robotAPI.robot.speak("請用手機下載 Zenbo 小主人，就可以遙控監視囉");
            }
        });
        

        bt6 = (Button)findViewById(R.id.button6);
        bt6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, QaActivity.class);
                startActivity(myIntent);
            }
        });




    }

}
