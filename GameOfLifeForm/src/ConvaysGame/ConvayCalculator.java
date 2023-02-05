package ConvaysGame;

import java.util.Arrays;
import java.util.List;

public class ConvayCalculator {

    /*
     * поле 128 на 128 клеток
     * всё что за полем считается пустым но там ничего не расчитывается
     *
     * массив одномерный
     * 0 1 2 3 4
     * 5 6 7 8 9
     * формулы клеток x - location of cell y - side length(128)
     * Upper = x - 128
     * lower = x + 128
     * diags = x +- 128+- 1
     * */
    List<Integer> oldArray = Arrays.asList(new Integer[16384]);
    List<Integer> newArray = Arrays.asList(new Integer[16384]);

    /*
    *   Any live cell with fewer than two live neighbours dies, as if by underpopulation.
        Any live cell with two or three live neighbours lives on to the next generation.
        Any live cell with more than three live neighbours dies, as if by overpopulation.
        Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
    * */
    public void resetCells() {
        for (int i = 0; i < newArray.size(); i++) {
            newArray.set(i, 0);
            oldArray.set(i, 0);
        }
    }

    public List<Integer> makeOneStep() {
        for (int i = 0; i < 16384; i++) {
            int cnt = getRightNeighbor(i) + getLeftNeighbor(i) +
                    getLowerNeighbor(i) + getUpperNeighbor(i) +
                    getLowerRightNeighbor(i) + getUpperRightNeighbor(i) +
                    getLowerLeftNeighbor(i) + getUpperLeftNeighbor(i);
            if (cnt < 2) {
                newArray.set(i, 0);
            } else if (cnt > 3) {
                newArray.set(i, 0);
            } else if (cnt == 3) {
                newArray.set(i, 1);
            } else if (cnt == 2) {
                newArray.set(i, oldArray.get(i));
            }

        }
        for (int i = 0; i < 16384; i++) {
            oldArray.set(i, newArray.get(i));

        }
        System.out.println("step");
        // oldArray.replaceAll(newArray);
        // oldArray=newArray;
        return newArray;
    }

    public void addCell(int x_cord, int y_cord) {
        int arr_addr = x_cord + y_cord * 128;
        oldArray.set(arr_addr, 1);
    }

    public void removeCell(int x_cord, int y_cord) {
        int arr_addr = x_cord + y_cord * 128;
        oldArray.set(arr_addr, 0);
        newArray.set(arr_addr, 0);
    }

    private int getRightNeighbor(int i) {
        if (i % 128 == 127) {
            return 0;
        } else {
            return oldArray.get(i + 1);
        }

    }

    private int getLeftNeighbor(int i) {
        if (i % 128 == 0) {
            return 0;
        } else {
            return oldArray.get(i - 1);
        }
    }

    private int getUpperNeighbor(int i) {
        if (i < 128) {
            return 0;
        } else {
            return oldArray.get(i - 128);
        }
    }

    private int getLowerNeighbor(int i) {
        if (i + 128 >= 16384) {
            return 0;
        } else {
            return oldArray.get(i + 128);
        }
    }

    private int getUpperRightNeighbor(int i) {
        if (i % 128 == 127 || i < 128) {
            return 0;
        } else {
            return oldArray.get(i - 128 + 1);
        }
    }

    private int getUpperLeftNeighbor(int i) {
        if (i % 128 == 0 || i < 128) {
            return 0;
        } else {
            return oldArray.get(i - 128 - 1);
        }
    }

    private int getLowerRightNeighbor(int i) {
        if (i % 128 == 127 || i + 128 >= 16384) {
            return 0;
        } else {
            return oldArray.get(i + 128 + 1);
        }
    }

    private int getLowerLeftNeighbor(int i) {
        if (i % 128 == 0 || i + 128 >= 16384) {
            return 0;
        } else {
            return oldArray.get(i + 128 - 1);
        }
    }

    public ConvayCalculator() {
        for (int i = 0; i < newArray.size(); i++) {
            newArray.set(i, 0);
            oldArray.set(i, 0);
        }
    }

    public List<Integer> getNewArray() {
        return newArray;

    }

    public List<Integer> getOldArray() {
        return oldArray;
    }
}
