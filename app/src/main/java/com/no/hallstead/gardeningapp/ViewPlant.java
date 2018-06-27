package com.no.hallstead.gardeningapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ViewPlant extends AppCompatActivity {

    String plotLocation;
    Plant plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plant);
        Intent intent = getIntent();
        plotLocation = intent.getStringExtra("plotLocation");

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        plant = gson.fromJson(preferences.getString(("Plant" + plotLocation), ""), Plant.class);

        TextView initView = findViewById(R.id.nameView);
        initView.setText(plant.getName());
        initView = findViewById(R.id.dateView);
        GregorianCalendar planted = plant.getDatePlanted();
        initView.setText((planted.get(Calendar.MONTH) + "/" + planted.get(Calendar.DAY_OF_MONTH) + "/" + planted.get(Calendar.YEAR)));
        initView = findViewById(R.id.waterView);
        GregorianCalendar watered = plant.getDateLastWatered();
        initView.setText((watered.get(Calendar.MONTH) + "/" + watered.get(Calendar.DAY_OF_MONTH) + "/" + watered.get(Calendar.YEAR)));
    }

    public void harvest(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("Plant" + plotLocation).apply();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, "Plant Harvested", duration);
        toast.show();
        finish();
    }

    public void water(View view) {
        plant.water();

        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        String plantString = gson.toJson(plant, Plant.class);
        editor.putString(("Plant" + plotLocation), plantString);
        editor.apply();

        GregorianCalendar watered = plant.getDateLastWatered();
        TextView waterView = findViewById(R.id.waterView);
        waterView.setText((watered.get(Calendar.MONTH) + "/" + watered.get(Calendar.DAY_OF_MONTH) + "/" + watered.get(Calendar.YEAR)));

        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, "Plant was watered!", duration);
        toast.show();
    }
}
