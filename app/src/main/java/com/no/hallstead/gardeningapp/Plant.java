package com.no.hallstead.gardeningapp;

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
        dateLastWatered = new GregorianCalendar();
    }

    public String getName() {
        return name;
    }

    public GregorianCalendar getDatePlanted() {
        return datePlanted;
    }

    public void water() {
        
    }

    public void harvest() {
        return;
    }



}
