package com.no.hallstead.gardeningapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewPlot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plot);
    }
    public void onClickPlot(View view) {
        //int value = view.getId();
        view.setBackgroundColor(300);
    }
}
