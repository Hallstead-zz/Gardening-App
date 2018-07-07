package com.no.hallstead.gardeningapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NewPlot extends AppCompatActivity {
    private boolean selected[] = new boolean[26];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plot);
        for (int i = 0; i < selected.length; i++)
            selected[i] = false;
    }
    @SuppressLint("ResourceType")
    public void onClickPlot(View view) {
        int tag = Integer.parseInt((String) view.getTag());
        if (!selected[tag]) {
            view.setBackgroundColor(0xffa52a2a);
            selected[tag] = true;
        } else {
            view.setBackgroundColor(0xffd6d7d7);
            selected[tag] = false;
        }
    }

    public void onClickCreatePlot(View view) {

    }
}
