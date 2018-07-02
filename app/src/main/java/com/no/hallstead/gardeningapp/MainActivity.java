package com.no.hallstead.gardeningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.gson.Gson;

/**
 * Primary working space for the application
 */
public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    /**
     * Sets the default values for all predefined plant types
     */
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

    /**
     * Code that runs when the activity is created. Generates the view of the plot.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //If the app has never been run before, it will add the two initial types into sharedPreferences
        if (preferences.getString("Carrots", "").equals("")) {
            setPlantTypes();
        }
    }

    /**
     * When you click on a square in the plot, this determines whether the
     * AddPlantToPlot or ViewPlant activity is started.
     * @param view
     */
    public void onClickPlot(View view) {
        Intent myIntent;

        //Each square in the plot has a tag for which square it is.
        String value = (String) view.getTag();

        //Depending on whether there is a plant in the square or not, it will start the respective activity.
        //it will save which square it is in the intent.
        if (preferences.getString(("Plant" + value), "").equals("")) {
            myIntent = new Intent(this, AddPlantToPlot.class);
            myIntent.putExtra("plotLocation", value);
        }
        else {
            myIntent = new Intent(this, ViewPlant.class);
            myIntent.putExtra("plotLocation", value);
        }

        //myIntent.putExtra("key", value); //Optional parameters for future reference
        startActivity(myIntent);
    }

}
