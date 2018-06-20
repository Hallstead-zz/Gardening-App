package com.no.hallstead.gardeningapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Plant plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onClickPlot(View view) {
        Intent myIntent;
        if (plant == null) {
            myIntent = new Intent(this, AddPlantToPlot.class);
        }
        else {
            myIntent = new Intent(this, ViewPlant.class);
        }

        //myIntent.putExtra("key", value); //Optional parameters
        startActivity(myIntent);
    }
}
