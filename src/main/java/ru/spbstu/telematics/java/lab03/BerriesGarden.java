package ru.spbstu.telematics.java.lab03;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BerriesGarden {

  int berriesCount;
  boolean flag;
  int pinkeysBusket;
  int brainsBusket;

  public BerriesGarden(int berriesCount) {
    this.berriesCount = berriesCount;
  }

  Runnable goingForBerries = () -> {
    while (berriesCount > 0) {
      try {
        if (!flag) {
          takingBerries();
        } else {
          System.out.println("Neighbour checked the flag");
        }
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        System.out.println("Ooops, something went wrong!");
      }
    }
  };

  private synchronized void takingBerries() throws InterruptedException {
    flag = true;
    String threadName = Thread.currentThread().getName();
    System.out.println("In the garden " + threadName);

    int maxBerriesToTake = (int) Math.floor(berriesCount >> 1);
    int currentProgress = maxBerriesToTake == 0 ? 1 : 1 + new Random().nextInt(maxBerriesToTake);
    TimeUnit.SECONDS.sleep(currentProgress / 100);

    berriesCount -= currentProgress;
    if (threadName.contains("Brain")) {
      brainsBusket += currentProgress;
    } else {
      pinkeysBusket += currentProgress;
    }

    System.out.println(threadName + " out of the garden with " + currentProgress + " berries");
    flag = false;
  }

}