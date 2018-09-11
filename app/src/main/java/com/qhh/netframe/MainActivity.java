package com.qhh.netframe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qhh.network.common.NetUrl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetUrl.baseUrl = "http://www.wanandroid.com";
    }
}
