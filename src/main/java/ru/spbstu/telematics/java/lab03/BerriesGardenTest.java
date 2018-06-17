package ru.spbstu.telematics.java.lab03;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BerriesGardenTest {

  private int initialBerriesCount = 300;
  BerriesGarden garden = new BerriesGarden(initialBerriesCount);

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
    System.out.println("Pinkey's result: " + garden.pinkeysBusket);
    System.out.println("Brain's result: " + garden.brainsBusket);
    assertTrue("Neighbours are cheating!", Math.abs(garden.pinkeysBusket - garden.brainsBusket) < initialBerriesCount/3);
  }

}
