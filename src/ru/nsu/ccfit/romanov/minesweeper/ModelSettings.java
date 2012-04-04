package ru.nsu.ccfit.romanov.minesweeper;

/**
 *
 * @author Dmitriy Romanov
 */
public final class ModelSettings {

    public static final int MIN_MINES = 5;
    public static final int MAX_MINES = 500;
    public static final int MIN_SIZE = 5;
    public static final int MAX_SIZE = 75;


    private int xSize;
    private int ySize;
    private int mines;

    public ModelSettings() {
        this.xSize = 9;
        this.ySize = 9;
        this.mines = 10;
    }

    public int getMines() {
        return mines;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {       
        return ySize;
    }

    public void setMines(int mines) {
        if (mines < MIN_MINES) {
            mines = MIN_MINES;
        } else if (mines > MAX_MINES) {
            mines = MAX_MINES;
        }
        // чтобы оставалось пространство хотя бы на один выстрел
        if (mines > (xSize * ySize - 9)) {
            mines = xSize * ySize - 9;
        }
        this.mines = mines;
    }

    public void setxSize(int xSize) {
        if (xSize < MIN_SIZE) {
            xSize=MIN_SIZE;
        }else if (xSize > MAX_SIZE) {
            xSize = MAX_SIZE;
        }
        this.xSize = xSize;
    }

    public void setySize(int ySize) {
        if (ySize < MIN_SIZE) {
            ySize=MIN_SIZE;
        }else if (ySize > MAX_SIZE) {
            ySize = MAX_SIZE;
        }
        this.ySize = ySize;
    }
}
