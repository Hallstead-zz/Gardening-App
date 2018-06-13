package com.no.hallstead.gardeningapp;

import org.junit.Test;

import java.lang.reflect.GenericArrayType;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void plantCarrotsWaterTest() {
        GregorianCalendar carrotPlantDate = new GregorianCalendar();
        carrotPlantDate.add(GregorianCalendar.DAY_OF_MONTH, -2);
        PlantType carrotType = new PlantType("Carrot", 2, 30);
        Plant carrot = new Plant(carrotType, carrotPlantDate);
        carrot.water();
        GregorianCalendar today = new GregorianCalendar();
        GregorianCalendar lastWatered = carrot.getDateLastWatered();
        assertEquals(today.DAY_OF_MONTH, lastWatered.DAY_OF_MONTH);


    }
}