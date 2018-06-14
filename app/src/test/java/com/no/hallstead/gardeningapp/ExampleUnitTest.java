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

        assert(carrotPlantDate != null);



    }

    @Test
    public void createDifferentPlantsTest() {
        GregorianCalendar potatoPlantDate = new GregorianCalendar();
        PlantType potatoType = new PlantType("Potato", 3, 40);
        Plant potato = new Plant(potatoType, potatoPlantDate);
        potatoPlantDate.add(GregorianCalendar.DAY_OF_MONTH, -2);

        GregorianCalendar tomatoPlantDate = new GregorianCalendar();
        PlantType tomatoType = new PlantType("Tomato", 2, 30);
        Plant tomato = new Plant(tomatoType, tomatoPlantDate);
        tomatoPlantDate.add(GregorianCalendar.DAY_OF_MONTH, -2);

        assert(potato != tomato);
    }
}