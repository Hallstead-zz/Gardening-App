package com.no.hallstead.gardeningapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class DeletePlot extends AppCompatActivity {
    GardenManager manager;

    /**
     * generates the list of currently existing plots
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_plot);

        manager = new GardenManager(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        ArrayList<String> plotsList = gson.fromJson(preferences.getString("allPlots", ""), ArrayList.class);
        String out = "";
        if (plotsList.contains("initial")) {
            TextView initView = findViewById(R.id.plotsList3);
            initView.setText("There are no plots.");
            return;
        }
        while(!plotsList.isEmpty()) {
            String current = plotsList.get(0);
            out += (current + "\n");
            plotsList.remove(current);
        }

        TextView initView = findViewById(R.id.plotsList3);
        initView.setText(out);
    }

    /**
     * takes data from the textbox and deletes the plot with that name.
     * @param view
     */
    public void onClickDeletePlot(View view) {
        //For future toasts
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        Gson gson = new Gson();

        ArrayList<String> allPlots = gson.fromJson(preferences.getString("allPlots", ""), ArrayList.class);

        //Gets the name input
        EditText editText0 = findViewById(R.id.nameInput);
        String name = editText0.getText().toString();

        if (name.equals("")) {
            Toast toast = Toast.makeText(context, "Please enter a name.", duration);
            toast.show();
            return;
        }

        if (!allPlots.contains(name)) {
            Toast toast = Toast.makeText(context, "Please enter a valid name.", duration);
            toast.show();
            return;
        }

        SharedPreferences.Editor editor = preferences.edit();
        manager.nuke(name);
        allPlots.remove(name);
        String oneLessPlot = gson.toJson(allPlots);
        editor.putString("allPlots", oneLessPlot);
        if (allPlots.size() > 0) {
            editor.putString("activePlot", allPlots.get(0));
            editor.putString("allPlots", oneLessPlot);
        } else {
            editor.putString("activePlot", "");
            editor.putString("allPlots", "");
        }

        editor.apply();
        finish();
    }

    /**
     * Deletes all currently saved plots.
     * @param view
     */
    public void onClickDeleteAll(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        ArrayList<String> allPlots = gson.fromJson(preferences.getString("allPlots", ""), ArrayList.class);

        while (!allPlots.isEmpty()) {
            String name = allPlots.get(0);
            manager.nuke(name);
            allPlots.remove(name);
        }

        editor.putString("allPlots", "");
        editor.apply();
        finish();
    }

    /*public void onClickDeleteCurrent(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        ArrayList<String> allPlots = gson.fromJson(preferences.getString("allPlots", ""), ArrayList.class);
        
            String name = preferences.getString("activePlot", "");
            nuke(name);
            allPlots.remove(name);

        editor.putString("activePlot", "");
        editor.apply();
        finish();
    }*/


}
