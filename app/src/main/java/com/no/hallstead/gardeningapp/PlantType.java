package com.no.hallstead.gardeningapp;

public class PlantType {
    private String name;
    private int waterFreq;
    private int timeToHarvest;

    public PlantType(String name, int waterFreq, int timeToHarvest) {
        this.name = name;
        this.waterFreq = waterFreq;
        this.timeToHarvest = timeToHarvest;
    }

    public String getName() {
        return name;
    }

    public int getWaterFreq() {
        return waterFreq;
    }

    public int getTimeToHarvest() {
        return timeToHarvest;
    }
}
