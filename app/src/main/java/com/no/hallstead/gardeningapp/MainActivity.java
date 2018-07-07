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
        carrot.setTips("Planting:\n" +
                "Plant 3 weeks before the last expected frost. Plant more every 2-3 weeks afterward. Rake the soil so that it is loose and free of rocks before planting. Make sure the carrots have a few inches space in between, as carrots that are too close together produce crooked roots.\n" +
                "\n" +
                "Watering:\n" +
                "Need 1 inch of water per week.\n" +
                "At first carrots need low volumes of water frequently. Once the tops develop more leaves, the frequency and volumes should be reduced to induce longer growth in the roots. Increase just the volume again toward the end of the lifecycle.\n" +
                "\n" +
                "Care:\n" +
                "Keep free of weeds, especially when the carrots are small. Thin the carrots from time to time so they have room to grow.\n" +
                "\n" +
                "Harvesting:\n" +
                "Harvest after 2 - 4 months.\n" +
                "You can harvest carrots when they’re big enough to eat, or let them all mature until you can harvest them all at once. Harvest them on a day with dry air but moist soil. Loosen the soil with a trowel then pull by hand.");
        potato.setTips("Planting:\n" +
                "Plant in early spring in moist but not water-logged soil. 1-2 weeks before planting, set seed potatoes where they will receive light at temperatures between 60-70 F. Cut large potatoes into smaller pieces a couple of days before planting. Plant potatoes 3 feet apart.\n" +
                "\n" +
                "Watering:\n" +
                "Need 1 - 2 inches of water per week.\n" +
                "Water young plants every 4-5 days, Increase frequency to 2-3 days once the tubers have formed. Water via drip irrigation from a garden hose. Overhead watering can damage young plants.\n" +
                "\n" +
                "Care: Whenever the soil becomes crusty or packed, rake it lightly to allow air flow to the potatoes. Perform “hilling” during the first 4-8 weeks by forming mounds of dirt around the plants. This helps fight weeds and promotes healthier growth. Don’t disturb the hills with any tools. If there are weeds, pull them by hand.\n" +
                "\n" +
                "Harvesting:\n" +
                "Harvest 2-3 weeks after potatoes flower. Use a shovel or spade to loosen soil if necessary, then dig up by hand. Dig deep tubers, found 4-6 inches underground, with shovel or spade. Potatoes are easiest to harvest in dry soil.");
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

    public void onClickNewPlot(View view) {
        Intent myIntent = new Intent(this, NewPlot.class);
        startActivity(myIntent);
    }
}
