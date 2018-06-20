package com.no.hallstead.gardeningapp;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Plant {
    private String name;
    private PlantType type;
    private GregorianCalendar datePlanted;
    private GregorianCalendar dateToHarvest;
    private GregorianCalendar dateLastWatered;
    private boolean needsWater;
    private boolean needsHarvest;

    public Plant(PlantType type, GregorianCalendar datePlanted) {
        name = type.getName();
        this.type = type;
        this.datePlanted = datePlanted;
        dateToHarvest = new GregorianCalendar();
        dateToHarvest.add(dateToHarvest.DAY_OF_MONTH, type.getTimeToHarvest());
        GregorianCalendar newCalendar = (GregorianCalendar) datePlanted.clone();
        dateLastWatered = newCalendar;
    }

    public String getName() {
        return name;
    }

    public GregorianCalendar getDatePlanted() {
        return datePlanted;
    }

    public void water() {
        dateLastWatered = new GregorianCalendar();
    }

    public GregorianCalendar getDateLastWatered() {
        return dateLastWatered;
    }

    public void harvest() {
        return;
    }



}
