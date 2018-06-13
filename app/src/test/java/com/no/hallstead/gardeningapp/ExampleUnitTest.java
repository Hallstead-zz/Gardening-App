package com.no.hallstead.gardeningapp;

import org.junit.Test;

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
        PlantType carrotType = new PlantType("Carrot", 2, 30);
        Plant carrot = new Plant(carrotType, carrotPlantDate);
        carrotPlantDate.add(GregorianCalendar.DAY_OF_MONTH, -2);



    }
}