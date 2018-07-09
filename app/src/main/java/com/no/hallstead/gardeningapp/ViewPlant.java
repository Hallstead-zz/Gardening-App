package com.no.hallstead.gardeningapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Activity that displays the data of a selected plant in the plot
 */
public class ViewPlant extends AppCompatActivity {

    String plotLocation;
    Plant plant;
    PlantType plantType;
    ColorStateList oldColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_plant);
        Intent intent = getIntent();
        plotLocation = intent.getStringExtra("plotLocation");

        //fetches the plant from shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String activePlot = preferences.getString("activePlot", "");
        plant = gson.fromJson(preferences.getString((activePlot + "Plant" + plotLocation), ""), Plant.class);
        plantType = (plant.getType());

        //sets the fields to the information of the plant
        TextView initView = findViewById(R.id.nameView);
        initView.setText(plant.getName());
        initView = findViewById(R.id.dateView);
        GregorianCalendar planted = plant.getDatePlanted();
        initView.setText(((planted.get(Calendar.MONTH) + 1) + "/" + planted.get(Calendar.DAY_OF_MONTH) + "/" + planted.get(Calendar.YEAR)));
        initView = findViewById(R.id.waterView);
        GregorianCalendar watered = plant.getDateLastWatered();
        initView.setText(((watered.get(Calendar.MONTH) + 1) + "/" + watered.get(Calendar.DAY_OF_MONTH) + "/" + watered.get(Calendar.YEAR)));
        TextView tipsView = findViewById(R.id.tipsView);
        tipsView.setText(plantType.getTips());

        //sets date to red if it needs watering
        GregorianCalendar today = new GregorianCalendar();
        today.add(Calendar.DAY_OF_MONTH, (-1 * plantType.getWaterFreq()));
        if (plant.getDateLastWatered().before(today)) {
            initView.setTextColor(0xffa52a2a);
        }
    }

    /**
     * Function call to remove a plant from the plot
     * @param view
     */
    public void harvest(View view) {

        //Removes the plant from shared preferences.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        String activePlot = preferences.getString("activePlot", "");
        editor.remove(activePlot + "Plant" + plotLocation).apply();

        //Informs the user that the plant has been removed
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, "Plant Harvested", duration);
        toast.show();

        //After the activity is finished, the plant is eligible for garbage collection.
        finish();
    }

    /**
     * Changes date last watered to the current system date
     * @param view
     */
    public void water(View view) {
        plant.water();

        //Replaces the plant in preferences with the current plant after watering
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        String plantString = gson.toJson(plant, Plant.class);
        String activePlot = preferences.getString("activePlot", "");
        editor.putString((activePlot + "Plant" + plotLocation), plantString);
        editor.apply();

        //Refreshes the text view with the new date.
        GregorianCalendar watered = plant.getDateLastWatered();
        TextView waterView = findViewById(R.id.waterView);
        waterView.setText(((watered.get(Calendar.MONTH) + 1) + "/" + watered.get(Calendar.DAY_OF_MONTH) + "/" + watered.get(Calendar.YEAR)));

        //sets text back to normal
        waterView.setTextColor(0xFF747474);

        //Informs the user that the plant was watered.
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, "Plant was watered!", duration);
        toast.show();
    }
}
