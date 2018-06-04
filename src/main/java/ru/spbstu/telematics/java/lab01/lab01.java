package ru.spbstu.telematics.java.lab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class lab01 {

  private static final int SIZE = 4;
  private static final String FILEPATH =
      "src/main/java/ru/spbstu/telematics/java/lab01/input.txt";

  private static int[][] matrixA = {{1, 3, 8, 1},
                                  {13, 4, 7, 20},
                                  {22, 13, 5, 0},
                                  {6, 23, 6, 3}};

  public static void main(String[] args) {
    int[][] matrix = readMatrixB(new File(FILEPATH));
    if (matrix != null) {
      printResult(calculateSum(matrix));
    }
  }

  static int[][] readMatrixB(File file) {
    try (Scanner scanner = new Scanner(file)) {

      int[][] matrixB = new int[SIZE][SIZE];

      for (int row = 0; scanner.hasNextLine(); row++) {
        for (int col = 0; col < SIZE; col++) {
          matrixB[row][col] = scanner.nextInt();
        }
      }
      return matrixB;

    } catch (FileNotFoundException e) {
      System.out.println("File not found!");
      return null;
    }
  }

  static int[][] calculateSum(int[][] matrixB) {
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        matrixB[row][col] += matrixA[row][col];
      }
    }
    return matrixB;
  }

  static void printResult(int[][] result) {
    System.out.println("A + B =");

    for (int[] c : result) {
      System.out.println(Arrays.toString(c));
    }
  }
}
