package ru.spbstu.telematics.java.lab03;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//TODO: rewrite using different concurrency features
//TODO: add readme
class BerriesGarden {

  private boolean flag;
  private int berriesCount;
  private long pause;
  private int pinkeysBusket;
  private int brainsBusket;
  private int tryingCount;

  Runnable goingForBerries = () -> {
    while (berriesCount > 0) {
      try {
        if (!flag) {
          takingBerries();
        } else {
          System.out.println("Neighbour checked the flag " + ++tryingCount + " time");
        }
        TimeUnit.SECONDS.sleep(pause);
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
    int possibleProgress = maxBerriesToTake == 0 ? 1 : 1 + new Random().nextInt(maxBerriesToTake);

    int progress = 0;
    while (progress < possibleProgress && tryingCount < 2) {
      TimeUnit.MILLISECONDS.sleep(20*pause);
      progress++;
    }
    countBerries(threadName, progress);
    System.out.println(threadName + " out of the garden with " + progress + " berries");
    tryingCount = 0;
    flag = false;
  }

  private void countBerries(String threadName, int progress) {
    berriesCount -= progress;
    if (threadName.contains("Brain")) {
      brainsBusket += progress;
    } else {
      pinkeysBusket += progress;
    }
  }

  BerriesGarden(int berriesCount, long pause) {
    this.berriesCount = berriesCount;
    this.pause = pause;
  }

  int getPinkeysBusket() {
    return pinkeysBusket;
  }

  int getBrainsBusket() {
    return brainsBusket;
  }
}