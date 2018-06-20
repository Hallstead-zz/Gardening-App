package com.no.hallstead.gardeningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    public void setPlantTypes() {
        Gson gson = new Gson();
        PlantType carrot = new PlantType("Carrots", 2,30);
        PlantType potato = new PlantType("Potatoes", 2,30);
        String carrotJson = gson.toJson(carrot, PlantType.class);
        String potatoJson = gson.toJson(potato, PlantType.class);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Carrots", carrotJson);
        editor.putString("Potatoes", potatoJson);
        editor.apply();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getString("Carrots", "").equals("")) {
            setPlantTypes();
        }
    }

    public void onClickPlot(View view) {
        Intent myIntent;
        if (preferences.getString("Main Plant", "").equals("")) {
            myIntent = new Intent(this, AddPlantToPlot.class);
        }
        else {
            myIntent = new Intent(this, ViewPlant.class);
        }

        //myIntent.putExtra("key", value); //Optional parameters
        startActivity(myIntent);
    }
}
