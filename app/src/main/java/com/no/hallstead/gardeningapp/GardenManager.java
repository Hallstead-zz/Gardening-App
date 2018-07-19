package com.no.hallstead.gardeningapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * used for deleting saved plots
 */
public class GardenManager {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public GardenManager(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    public void nuke(String name) {
        editor.remove(name + "Spaces");

        for (int i = 1; i < 26; i++) {
            editor.remove(name + "Plant" + Integer.toString(i));
        }

        editor.apply();
    }
}
