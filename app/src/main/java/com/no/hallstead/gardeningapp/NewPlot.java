package com.no.hallstead.gardeningapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

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

        //For future toasts
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        //Gets the name input
        EditText editText0 = findViewById(R.id.nameInput);
        String name = editText0.getText().toString();

        if (name.equals("")) {
            Toast toast = Toast.makeText(context, "Please enter a name.", duration);
            toast.show();
            return;
        }

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        ArrayList<String> plotsList = gson.fromJson(preferences.getString("allPlots", ""), ArrayList.class);

        if (plotsList.contains(name)) {
            Toast toast = Toast.makeText(context, "Please enter a unique name.", duration);
            toast.show();
            return;
        }

        String save = "";
        for (int n = 0; n < selected.length; n++) {
            if(selected[n]) {
                save = save + "1" + "|$|blessse|$|";
            } else {
                save = save + "0" + "|$|blessse|$|";
            }
        }
        if (plotsList.contains("initial")) {
            GardenManager manager = new GardenManager(this);
            manager.nuke("initial");
            plotsList.remove("initial");
        }

        plotsList.add(name);

        editor.putString("allPlots", gson.toJson(plotsList, ArrayList.class));
        editor.putString("activePlot", name);
        editor.putString(name + "Spaces", save);
        editor.apply();

        finish();
    }
}
