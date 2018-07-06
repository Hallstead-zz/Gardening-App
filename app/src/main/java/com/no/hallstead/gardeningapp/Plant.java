package com.no.hallstead.gardeningapp;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * stores the values of a plant object.
 */
public class Plant {
    private String name;
    private PlantType type;
    private GregorianCalendar datePlanted;
    private GregorianCalendar dateLastWatered;

    /**
     * Plant non-default constructor.
     * @param type
     * @param datePlanted
     */
    public Plant(PlantType type, GregorianCalendar datePlanted) {
        name = type.getName();
        this.type = type;
        this.datePlanted = datePlanted;
        //dateLastWatered is set to the value of when it was planted, but as a different variable
        dateLastWatered = (GregorianCalendar) datePlanted.clone();
    }

    //Getters and setters:

    public String getName() {
        return name;
    }

    public GregorianCalendar getDatePlanted() {
        return datePlanted;
    }

    public PlantType getType() {
        return type;
    }

    /** */
    public void water() {
        dateLastWatered = new GregorianCalendar();
    }

    /** */
    public GregorianCalendar getDateLastWatered() {
        return dateLastWatered;
    }
}
