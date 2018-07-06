package com.no.hallstead.gardeningapp;

/**
 * Defines the category of plant planted, as well as its special traits
 */
public class PlantType {
    private String name;
    private int waterFreq;
    private int timeToHarvest;
    private String tipsText;

    /**
     * Non-default constructor
     * @param name
     * @param waterFreq
     * @param timeToHarvest
     */
    public PlantType(String name, int waterFreq, int timeToHarvest) {
        this.name = name;
        this.waterFreq = waterFreq;
        this.timeToHarvest = timeToHarvest;
    }

    //Getters:

    public String getName() {
        return name;
    }

    public int getWaterFreq() {
        return waterFreq;
    }

    public int getTimeToHarvest() {
        return timeToHarvest;
    }

    public String getTips() {
        return tipsText;
    }

    public void setTips(String text) {
        tipsText = text;
    }
}
