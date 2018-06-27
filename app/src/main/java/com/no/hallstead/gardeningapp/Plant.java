package com.no.hallstead.gardeningapp;

import java.util.Calendar;
import java.util.GregorianCalendar;

/** */
public class Plant {
    private String name;
    private PlantType type;
    private GregorianCalendar datePlanted;
    private GregorianCalendar dateLastWatered;

    /** */
    public Plant(PlantType type, GregorianCalendar datePlanted) {
        name = type.getName();
        this.type = type;
        this.datePlanted = datePlanted;
        //dateLastWatered is set to the value of when it was planted, but as a different variable
        dateLastWatered = (GregorianCalendar) datePlanted.clone();
    }

    /** */
    public String getName() {
        return name;
    }

    /** */
    public GregorianCalendar getDatePlanted() {
        return datePlanted;
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
