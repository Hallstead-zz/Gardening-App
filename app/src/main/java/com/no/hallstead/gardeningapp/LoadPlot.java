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

public class LoadPlot extends AppCompatActivity {

    /**
     * Generates a list of currently saved plots.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_plot);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        ArrayList<String> plotsList = gson.fromJson(preferences.getString("allPlots", ""), ArrayList.class);
        String out = "";
        if (plotsList.contains("initial")) {
            TextView initView = findViewById(R.id.plotsList);
            initView.setText("There are no plots.");
            return;
        }
            while(!plotsList.isEmpty()) {
            String current = plotsList.get(0);
            out += (current + "\n");
            plotsList.remove(current);
        }

        TextView initView = findViewById(R.id.plotsList);
        initView.setText(out);
    }

    /**
     * loads the plot into the main activity with the same name as the data in the textbox.
     * @param view
     */
    public void onClickLoadPlot(View view) {
        //For future toasts
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        //Gets the name input
        EditText editText0 = findViewById(R.id.nameInput);
        String name = editText0.getText().toString();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        ArrayList<String> plotsList = gson.fromJson(preferences.getString("allPlots", ""), ArrayList.class);

        if (plotsList.contains("initial")) {
            Toast toast = Toast.makeText(context, "Please create a plot before trying to load one.", duration);
            toast.show();
            return;
        }

        if (name.equals("")) {
            Toast toast = Toast.makeText(context, "Please enter a name.", duration);
            toast.show();
            return;
        }

        if (!plotsList.contains(name)) {
            Toast toast = Toast.makeText(context, "Please enter a valid name.", duration);
            toast.show();
            return;
        }

        editor.putString("activePlot", name);
        editor.apply();

        finish();
    }
}
