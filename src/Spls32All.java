import java.util.Random;
import java.util.ArrayList;
import java.io.PrintWriter;

/*
 Program to find a 24x24 array with special qualities. 
 This is done iteratively; slots (individual positions in the 2d array) are filled
 one by one along a specified path (see 'path' array) based on generating random integers and
 checking to see if those integers fulfill the requirements of that particular slot. If no valid integers can be found,
 (i.e., if the algorithm runs into a dead end), the algorithm backtracks to previous slots, changes values,
 and tries again. Additionally, the 'path' array also stores a "mark" for each slot, which gives information
 about what special rules that slot needs to follow.
 */
public class Spls32All {
  /*
   * Function to assist with printing the 2d array
   */
  public static void printLongLine(PrintWriter pen) {
    pen.println("-------------------------------------------");
  }

  /*
   * Function to print the 2d array in a sensible way
   */
  public static void print(PrintWriter pen, Integer[][] arr) {
    /*
    printLongLine(pen);
    for (int i = 0; i < 6; i++) {
      pen.print("|");
      for (int j = 0; j < 6; j++) {
        pen.printf(" %4d |", arr[i][j]);
      }
      pen.println();
      printLongLine(pen);
    }
    */

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        pen.printf(" %4d ", arr[i][j]);
      }
      pen.println();
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
    for (int i = 2; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
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

    k = initializePathHelper(path, 0, 0, 1, 5, k, 0);
    k = initializePathHelper(path, 2, 3, 3, 5, k, 0);
    k = initializePathHelper(path, 4, 0, 5, 5, k, 0);
  }

  public static void initializePreviousValues(ArrayList<ArrayList<Integer>> previousValues) {
    previousValues.clear();

    for (int i = 0; i < (6 * 6) - 6; i++) {
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
    for (int i = 0; i < 6; i++) {
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
    for (int i = 0; i < 6; i++) {
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
    int boxRow = (rowNum / 2) * 2; // integer division truncates in Java
    int boxCol = (colNum / 3) * 3;
    // now, look for slots in the box that match 'num'
    // NOTE: we don't need to check slots of the same row/column, as we already did
    // this earlier
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 3; j++) {
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
    boxRow = (rowNum / 3) * 3; // integer division truncates in Java
    boxCol = (colNum / 2) * 2;
    // now, look for slots in the box that match 'num'
    // NOTE: we don't need to check slots of the same row/column, as we already did
    // this earlier
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
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
    for (int i = 0; i < 6; i++) {
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

  public static void main(String[] args) throws Exception {
    // initialize the array that will hold the path for filling slots
    // (an array of coordinate pairs)
    // Also provides a 'mark' for each slot, noting which special rules should apply
    int[][] path = new int[(6 * 6) - 6][3];

    // add values to the path array
    initializePath(path);

    // initialize the 2d array (every slot starts as 'null') ([row][column])
    Integer[][] arr = new Integer[6][6];

    // add initial values to the 2d array
    initializeSlots(arr);

    // keep track of the previous values for each element
    ArrayList<ArrayList<Integer>> previousValues = new ArrayList<>((6 * 6) - 6);

    // prepare the storage of previous values
    initializePreviousValues(previousValues);

    // try a bunch of times,
    // if 'fill2dArraySection' (for the whole array) doesn't finish in 5000000
    // iterations, we scrap the attempt and try again, until we are successful

    PrintWriter pen = new PrintWriter("arrayLog.txt", "UTF-8");

    int startIndex = 0; // 0 the first time, then start from the last box every other time
    for (int i = 1; i <= 1000000; i++) {
      // fill the array
      try {
      fill2dArraySection(arr, path, startIndex, 6 * 6 - 6 - 1, previousValues); 
      } catch (Exception e) {
        break;
      }

      // record the array
      //pen.println(i);
      print(pen, arr);
      pen.println();

      // prepare the next iteration-- make sure we're finding unique SPLS(2,3)s
      previousValues.get(previousValues.size() - 1).add(arr[5][5]);
      arr[5][5] = null;
      startIndex = 6 * 6 - 6 - 1;
    }

    pen.close();
  }
}
