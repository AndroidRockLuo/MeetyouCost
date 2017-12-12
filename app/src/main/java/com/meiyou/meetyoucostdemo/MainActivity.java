package com.meiyou.meetyoucostdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.meiyou.meetyoucostplugin.Cost;

public class MainActivity extends Activity {

    @Override
    @Cost
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show();
        showTest("",new View(this),0);
        show("",new View(this));
    }
    @Cost
    public void show() {
        for (int i = 0; i < 100; i++) {

        }
    }

    //@Cost
    public void showTest(String str,View view,int value) {
        for (int i = 0; i < 100; i++) {

        }
    }
    //@Cost
    public void show(String str,View view) {
        for (int i = 0; i < 100; i++) {

        }
    }
}
