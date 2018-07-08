package com.no.hallstead.gardeningapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Activity responsible for inserting a plant into a plot space.
 */
public class AddPlantToPlot extends AppCompatActivity {

    String plotLocation;

    /** */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant_to_plot);
        Intent intent = getIntent();
        plotLocation = intent.getStringExtra("plotLocation");
    }

    /**
     * Verifies that the date planted is valid
     * @param s
     * @return
     */
    boolean isLegalDate(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        return sdf.parse(s, new ParsePosition(0)) != null;
    }

    /**
     * Collects data from entry boxes to create a stored plant
     * @param view
     */
    public void addPlant(View view) {

        //For future toasts
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        //Gets the date input
        EditText editText0 = findViewById(R.id.monthInput);
        String month = editText0.getText().toString();
        EditText editText1 = findViewById(R.id.dayInput);
        String day = editText1.getText().toString();
        EditText editText2 = findViewById(R.id.yearInput);
        String year = editText2.getText().toString();

        //Validates that the user input a date
        if (month.equals("") || day.equals("") || year.equals("")) {
            Toast toast = Toast.makeText(context, "Please enter a date", duration);
            toast.show();
            return;
        }

        Spinner mySpinner = findViewById(R.id.typeSpinner);
        String plantName = mySpinner.getSelectedItem().toString();

        //Validates that the user selected a plant type
        if (plantName.equals("Select...")){
            Toast toast = Toast.makeText(context, "Please select a plant", duration);
            toast.show();
            return;
        }

        //Validates the entered date
        if(!isLegalDate((year + "-" + month + "-" + day))) {
            Toast toast = Toast.makeText(context, "Requires a valid date!", duration);
            toast.show();
            return;
        }
        GregorianCalendar planted = new GregorianCalendar(Integer.valueOf(year), Integer.valueOf(month) - 1, Integer.valueOf(day));

        //Saves the plant to shared preferences
        Gson gson = new Gson();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        PlantType type = gson.fromJson(preferences.getString(plantName, ""), PlantType.class);
        Plant newPlant = new Plant(type, planted);
        SharedPreferences.Editor editor = preferences.edit();
        String mainPlant = gson.toJson(newPlant, Plant.class);
        String activePlot = preferences.getString("activePlot", "");
        editor.putString((activePlot + "Plant" + plotLocation), mainPlant);
        editor.apply();

        Log.i("AddPlantToPlot","Planted a " + plantName + " at " + plotLocation);

        //Informs the user that the Plant was added and exits
        Toast toast = Toast.makeText(context, "Plant was added!", duration);
        toast.show();
        finish();

    }


}
