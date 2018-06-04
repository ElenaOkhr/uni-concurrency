package ru.spbstu.telematics.java.lab01;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.Test;

public class lab01Test {

  private File existentFile = new File("src/main/java/ru/spbstu/telematics/java/lab01/input.txt");
  private File nonExistentFile = new File(" ");

  private int[][] result = {{2, 7, 10, 9},
                            {25, 8, 15, 21},
                            {78, 15, 8, 12},
                            {7, 45, 51, 20}};

  private int[][] matrix = {{1, 4, 2, 8},
                            {12, 4, 8, 1},
                            {56, 2, 3, 12},
                            {1, 22, 45, 17}};

  @Test
  public void readMatrixSuccessfully() {
    int[][] actual = lab01.readMatrixB(existentFile);

    assertEquals(matrix.length, actual.length);
    assertArrayEquals(matrix[0], actual[0]);
  }

  @Test
  public void readMatrixUnsuccessfully() {
    assertNull(lab01.readMatrixB(nonExistentFile));
  }

  @Test
  public void calculateSum() {
    int[][] actual = lab01.calculateSum(matrix);

    assertArrayEquals(result[result.length-1], actual[actual.length-1]);
  }
}