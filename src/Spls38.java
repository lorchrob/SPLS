import java.util.Random;
import java.util.ArrayList;

/*
 Program to find a 24x24 array with special qualities. 
 This is done iteratively; slots (individual positions in the 2d array) are filled
 one by one along a specified path (see 'path' array) based on generating random integers and
 checking to see if those integers fulfill the requirements of that particular slot. If no valid integers can be found,
 (i.e., if the algorithm runs into a dead end), the algorithm backtracks to previous slots, changes values,
 and tries again. Additionally, the 'path' array also stores a "mark" for each slot, which gives information
 about what special rules that slot needs to follow.
 */
public class Spls38 {
  /*
   * Function to assist with printing the 2d array
   */

  public static void printLongLine() {
    for (int i = 0; i < 13; i++) {
      System.out.print("-------------");
    }
    System.out.println();
  }

  /*
   * Function to print the 2d array in a sensible way
   */
  public static void print(Integer[][] arr) {
    printLongLine();
    for (int i = 0; i < 24; i++) {
      System.out.print("|");
      for (int j = 0; j < 24; j++) {
        System.out.printf(" %4d |", arr[i][j]);
      }
      System.out.println();
      printLongLine();
    }
  }

  /*
   * Function to initialize the slot in the array
   * 
   * NOTE: All other slots (besides the 0-23) will start as 'null'
   */
  public static void initializeSlots(Integer[][] arr) {
    int num = 0;

    // fill every slot of the initial slot with values, starting with 0
    // and counting up
    for (int i = 0; i < 8; i++) {
      for (int j = 6; j < 9; j++) {
        arr[i][j] = num++;
      }
    }
  }

  /*
   * Helper function to assist with initializing the path
   */
  public static int initializePathHelper(int[][] path, int firstRow, int firstCol, int lastRow, int lastCol, int k,
      int mark) {
    for (int i = firstRow; i <= lastRow; i++) {
      for (int j = firstCol; j <= lastCol; j++) {
        path[k][0] = i;
        path[k][1] = j;
        path[k][2] = mark;
        k++;
      }
    }
    return k;
  }

  /*
   * Function to set up the desired path for filling the slots. Also marks each
   * slot, where the mark is a description of the slot (describes which special
   * rules the slot needs to follow, e.g., if a number/slot is in C_1 union C_2
   * and also needs to appear in B_1 union B_3
   */
  public static void initializePath(int[][] path) {
    int k = 0;

    // add C_1 and C_2
    k = initializePathHelper(path, 6, 0, 7, 5, k, 1);

    // add D_2 and D_4
    k = initializePathHelper(path, 0, 15, 5, 15, k, 2);

    // add C_3 and C_4
    k = initializePathHelper(path, 6, 9, 7, 14, k, 3);

    // add F_2 and E_2
    k = initializePathHelper(path, 6, 15, 7, 17, k, 0);

    // add C_5 and C_6
    k = initializePathHelper(path, 6, 18, 7, 23, k, 0);

    // add B_2 and B_4
    k = initializePathHelper(path, 0, 16, 5, 17, k, 4);

    // add first rotated D_1 and D_3
    k = initializePathHelper(path, 8, 18, 8, 23, k, 0);

    // add first rotated stray squares
    k = initializePathHelper(path, 8, 16, 8, 17, k, 0);

    // add first rotated D_2 and D_4
    k = initializePathHelper(path, 15, 18, 15, 23, k, 5);

    // add first rotated C_3 and C_4
    k = initializePathHelper(path, 9, 16, 14, 17, k, 6);

    // add first rotated E_2 and F_2
    k = initializePathHelper(path, 15, 16, 17, 17, k, 0);

    // add first rotated C_5 and C_6
    k = initializePathHelper(path, 18, 16, 23, 17, k, 0);

    // add first rotated B_2 and B_4
    k = initializePathHelper(path, 16, 18, 17, 23, k, 7);

    // add second rotated D_1 and D_3
    k = initializePathHelper(path, 18, 15, 23, 15, k, 0);

    // add second rotated stray squares
    k = initializePathHelper(path, 16, 15, 17, 15, k, 0);

    // add second rotated D_2 and D_4
    k = initializePathHelper(path, 18, 8, 23, 8, k, 8);

    // add second rotated C_3 and C_4
    k = initializePathHelper(path, 16, 9, 17, 14, k, 9);

    // add second rotated E_2 and F_2
    k = initializePathHelper(path, 16, 6, 17, 8, k, 0);

    // add second rotated C_5 and C_6
    k = initializePathHelper(path, 16, 0, 17, 5, k, 0);

    // add second rotated B_2 and B_4
    k = initializePathHelper(path, 18, 6, 23, 7, k, 10);

    // add third rotated D_1 and D_3
    k = initializePathHelper(path, 15, 0, 15, 5, k, 0);

    // add third rotated stray squares
    k = initializePathHelper(path, 15, 6, 15, 7, k, 0);
    k = initializePathHelper(path, 8, 6, 8, 7, k, 0);

    // add third rotated D_2 and D_4
    k = initializePathHelper(path, 8, 0, 8, 5, k, 11);

    // add third rotated C_3 and C_4
    k = initializePathHelper(path, 9, 6, 14, 7, k, 12);

    // add middle
    k = initializePathHelper(path, 8, 8, 15, 15, k, 0);

    // add middle left
    k = initializePathHelper(path, 9, 0, 14, 5, k, 0);

    // add middle right
    k = initializePathHelper(path, 9, 18, 14, 23, k, 0);

    // add upper middle
    k = initializePathHelper(path, 0, 9, 5, 14, k, 0);

    // add lower middle
    k = initializePathHelper(path, 18, 9, 23, 14, k, 0);

    // add upper left
    k = initializePathHelper(path, 0, 0, 5, 5, k, 0);

    // add upper right
    k = initializePathHelper(path, 0, 18, 5, 23, k, 0);

    // add lower left
    k = initializePathHelper(path, 18, 0, 23, 5, k, 0);

    // add lower right
    k = initializePathHelper(path, 18, 18, 23, 23, k, 0);
  }

  public static void initializePreviousValues(ArrayList<ArrayList<Integer>> previousValues) {
    previousValues.clear();

    for (int i = 0; i < (24 * 24) - 24; i++) {
      previousValues.add(new ArrayList<>());
    }
  }

  /*
   * Function to see if a number is an element of a box (subsection) of the 2d
   * array, with the upper-left and lower-right coordinates of the box passed as
   * parameters
   */
  public static boolean elementOf(Integer[][] arr, Integer num, int firstRow, int firstCol, int lastRow, int lastCol) {
    // check every element of the box, returning true if 'num' is equal to that
    // element
    for (int i = firstRow; i <= lastRow; i++) {
      for (int j = firstCol; j <= lastCol; j++) {
        if (num.compareTo(arr[i][j]) == 0) {
          return true;
        }
      }
    }

    // otherwise, the box does not contain 'num'
    return false;
  }

  /*
   * Function to check the four main restrictions (rows, columns, black boxes, red
   * boxes all 0-23) for a specific slot in the 2d array
   */
  public static boolean checkSlotGeneral(Integer[][] arr, Integer num, int rowNum, int colNum) {
    // check row (so far) for validity
    // If any other slot in the same row is equal to this slot, then 'num' is
    // invalid
    // NOTE: make sure to skip the slot that num is currently in
    for (int i = 0; i < 24; i++) {
      if (i != colNum) {
        if (arr[rowNum][i] != null && num.compareTo(arr[rowNum][i]) == 0) {
          return false;
        }
      }
    }

    // check column (so far) for validity
    // If any other slot in the same column is equal to this slot, then 'num' is
    // invalid
    // NOTE: make sure to skip the slot that num is currently in
    for (int i = 0; i < 24; i++) {
      if (i != rowNum) {
        if (arr[i][colNum] != null && num.compareTo(arr[i][colNum]) == 0) {
          return false;
        }
      }
    }

    // check black box (so far) for validity
    // If any other slot in the same black box is equal to this slot, then 'num' is
    // invalid
    // First, we need to find 'boxRow' and 'boxCol', the indices of the upper-left
    // corner
    // of the box
    int boxRow = (rowNum / 3) * 3; // integer division truncates in Java
    int boxCol = (colNum / 8) * 8;
    // now, look for slots in the box that match 'num'
    // NOTE: we don't need to check slots of the same row/column, as we already did
    // this earlier
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 8; j++) {
        if ((boxRow + i) != rowNum && (boxCol + j) != colNum) {
          if (arr[boxRow + i][boxCol + j] != null && num.compareTo(arr[boxRow + i][boxCol + j]) == 0) {
            return false;
          }
        }
      }
    }

    // check red box (so far) for validity
    // If any other slot in the same red box is equal to this slot, then 'num' is
    // invalid
    // If nothing invalid is found, then the placement must be valid
    boxRow = (rowNum / 8) * 8; // integer division truncates in Java
    boxCol = (colNum / 3) * 3;
    // now, look for slots in the box that match 'num'
    // NOTE: we don't need to check slots of the same row/column, as we already did
    // this earlier
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 3; j++) {
        if ((boxRow + i) != rowNum && (boxCol + j) != colNum) {
          if (arr[boxRow + i][boxCol + j] != null && num.compareTo(arr[boxRow + i][boxCol + j]) == 0) {
            return false;
          }
        }
      }
    }

    return true;
  }

  /*
   * Function to fill a single slot according to the rules (general and specific)
   * 
   * NOTE: Throws an exception if there is no valid number for the specified slot
   */
  public static void fillSlot(Integer[][] arr, int rowNum, int colNum, int mark, ArrayList<Integer> previousValues) {
    ArrayList<Integer> values = new ArrayList<>();
    for (int i = 0; i < 24; i++) {
      values.add((Integer) i);
    }

    // remove previous values from potential "values"
    for (Integer value : previousValues) {
      values.remove(value);
    }

    // generate random integer
    Random rand = new Random();
    Integer randNum = values.get(rand.nextInt(values.size()));

    switch (mark) {
    case 0:
      // adding generic blocks
      while (!checkSlotGeneral(arr, randNum, rowNum, colNum)) {
        values.remove((Integer) randNum);
        previousValues.add((Integer) randNum);
        randNum = values.get(rand.nextInt(values.size()));
      }
      break;
    case 1:
      // add C_1, C_2
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 0, 6, 5, 7, true);
      break;
    case 2:
      // add D_2, D_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 0, 8, 5, 8, false);
      break;
    case 3:
      // add C_3, C_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 0, 8, 5, 8, 0, 15, 5, 15,
          true);
      break;
    case 4:
      // add B_2, B_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 6, 18, 7, 23, true);
      break;
    case 5:
      // add first rotated D_2, D_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 8, 18, 8, 23, false);
      break;
    case 6:
      // add first rotated C_3, C_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 8, 18, 8, 23, 15, 18, 15, 23,
          true);
      break;
    case 7:
      // add first rotated B_2, B_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 18, 16, 23, 17, true);
      break;
    case 8:
      // add second rotated D_2, D_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 18, 15, 23, 15, false);
      break;
    case 9:
      // add second rotated C_3, C_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 18, 8, 23, 8, 18, 15, 23, 15,
          true);
      break;
    case 10:
      // add second rotated B_2, B_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 16, 0, 17, 5, true);
      break;
    case 11:
      // add third rotated D_2, D_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 15, 0, 15, 5, false);
      break;
    case 12:
      // add third rotated C_3, C_4
      randNum = fillSlotHelper(arr, randNum, rowNum, colNum, values, previousValues, rand, 15, 0, 15, 5, 8, 0, 8, 5,
          true);
      break;
    }

    previousValues.add(randNum);
    arr[rowNum][colNum] = randNum;
  }

  /*
   * Function to help filling a slot, goes through a 'while' loop in order to fill
   * a slot (or throw an exception trying). The 'bool' describes whether or not we
   * want the number of the slot we're filling to be an element of the box(es) in
   * the 'Callable' object. (So, if our 'bool' is true, we DO want it do be an
   * element of the box, and so if it's NOT an element (ie, !elementOf()), we
   * throw it out and try again.)
   */
  public static int fillSlotHelper(Integer[][] arr, Integer randNum, int rowNum, int colNum, ArrayList<Integer> values,
      ArrayList<Integer> previousValues, Random rand, int firstRow, int firstCol, int lastRow, int lastCol,
      boolean bool) {
    // while conditions are broken, find a new random number until we run out of
    // options (throw an exception)
    if (bool) {
      while (!elementOf(arr, randNum, firstRow, firstCol, lastRow, lastCol)
          || !checkSlotGeneral(arr, randNum, rowNum, colNum)) {
        values.remove(randNum);
        previousValues.add(randNum);
        randNum = values.get(rand.nextInt(values.size()));
      }
    } else {
      while (elementOf(arr, randNum, firstRow, firstCol, lastRow, lastCol)
          || !checkSlotGeneral(arr, randNum, rowNum, colNum)) {
        values.remove(randNum);
        previousValues.add(randNum);
        randNum = values.get(rand.nextInt(values.size()));
      }
    }

    // if 'randNum' escaped the 'while' loop, it should be valid!
    return randNum;
  }

  public static int fillSlotHelper(Integer[][] arr, Integer randNum, int rowNum, int colNum, ArrayList<Integer> values,
      ArrayList<Integer> previousValues, Random rand, int firstRow, int firstCol, int lastRow, int lastCol,
      int firstRow2, int firstCol2, int lastRow2, int lastCol2, boolean bool) {
    // while conditions are broken, find a new random number until we run out of
    // options (throw an exception)
    if (bool) {
      while (!(elementOf(arr, randNum, firstRow, firstCol, lastRow, lastCol)
          || elementOf(arr, randNum, firstRow2, firstCol2, lastRow2, lastCol2))
          || !checkSlotGeneral(arr, randNum, rowNum, colNum)) {
        values.remove(randNum);
        previousValues.add(randNum);
        randNum = values.get(rand.nextInt(values.size()));
      }
    } else {
      while ((elementOf(arr, randNum, firstRow, firstCol, lastRow, lastCol)
          || elementOf(arr, randNum, firstRow2, firstCol2, lastRow2, lastCol2))
          || !checkSlotGeneral(arr, randNum, rowNum, colNum)) {
        values.remove(randNum);
        previousValues.add(randNum);
        randNum = values.get(rand.nextInt(values.size()));
      }
    }

    // if 'randNum' escaped the 'while' loop, it should be valid!
    return randNum;
  }

  /*
   * Function to fill a subsection of the 2d array, applying all rules and
   * backtracking when necessary
   */
  public static boolean fill2dArraySection(Integer[][] arr, int[][] path, int start, int finish,
      ArrayList<ArrayList<Integer>> previousValues) {
    int current = start;
    // keep track of iterations
    int i = 0;

    // keeping going until every slot is filled (or, if we go over 5000000
    // iterations, we give up)
    while (current <= finish && i <= 1000000) {
      try {
        fillSlot(arr, path[current][0], path[current][1], path[current][2], previousValues.get(current));
        current++;
      } catch (Exception e) {
        arr[path[current][0]][path[current][1]] = null;
        previousValues.get(current).clear();
        current--;
      }

      i++;
    }

    return current > finish;
  }

  public static void main(String[] args) {
    // initialize the 2d array (every slot starts as 'null') ([row][column])
    Integer[][] arr = new Integer[24][24];

    // initialize the array that will hold the path for filling slots
    // (an array of coordinate pairs)
    // Also provides a 'mark' for each slot, noting which special rules should apply
    int[][] path = new int[(24 * 24) - 24][3];

    // keep track of the previous values for each element
    ArrayList<ArrayList<Integer>> previousValues = new ArrayList<>((24 * 24) - 24);

    // add initial values to the 2d array
    initializeSlots(arr);

    // add values to the path array
    initializePath(path);

    // prepare the storage of previous values
    initializePreviousValues(previousValues);

    // try a bunch of times,
    // if 'fill2dArraySection' (for the whole array) doesn't finish in 5000000
    // iterations, we scrap the attempt and try again, until we are successful
    int i = 0;
    while (!fill2dArraySection(arr, path, 0, 24 * 24 - 24 - 1, previousValues)) {
      if (i % 10 == 0) {
        print(arr);
        System.out.println();
      }
      i++;
      initializePreviousValues(previousValues);
      arr = new Integer[24][24];
      initializeSlots(arr);
    }

    // fill2dArraySection(arr, path, 0, 551, previousValues);
    print(arr);
  }
}
