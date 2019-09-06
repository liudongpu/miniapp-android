package com.uhutu.miniapp.miniapp_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uhutu.miniapp.mappandroid.miniapp.MiniappJumpUtil;

import java.util.HashMap;
import java.util.Map;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);



        Button btn = (Button) findViewById(R.id.button2);
        btn .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Map<String,String> map=new HashMap<>();
                map.put("aaa","bbbccc");

                new MiniappJumpUtil().sendNativeNotice("notice_abc",map);

                finish();


            }
        });


    }
}
