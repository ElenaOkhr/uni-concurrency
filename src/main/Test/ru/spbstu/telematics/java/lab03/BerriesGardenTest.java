package ru.spbstu.telematics.java.lab03;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BerriesGardenTest {

    private int initialBerriesCount = 300;
    private BerriesGarden garden = new BerriesGarden(initialBerriesCount, 1L);

    @Before
    public void SetUp() throws InterruptedException {
        Thread threadPinky = new Thread(garden.goingForBerries, "Neighbour Pinky");
        Thread threadBrain = new Thread(garden.goingForBerries, "Neighbour Brain");

        threadPinky.start();
        threadBrain.start();

        threadPinky.join();
        threadBrain.join();
    }

    @Test
    public void testIfCheating(){
        System.out.println("Pinkey's result: " + garden.getPinkeysBusket());
        System.out.println("Brain's result: " + garden.getBrainsBusket());
        assertTrue("Neighbours are cheating!",
                Math.abs(garden.getPinkeysBusket() - garden.getBrainsBusket()) < initialBerriesCount/3);
    }
}