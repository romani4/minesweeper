package ru.nsu.ccfit.romanov.minesweeper;

/**
 * Клетка поля
 * @author Dmitriy Romanov
 */
class Field {

    /**
     * Открыта ли клетка?
     */
    private boolean opened = false;
    /**
     * Заминирована ли клетка?
     */
    private boolean mined = false;
    /**
     * Установлен ли флажок?
     */
    private boolean flagPlaced = false;
    /**
     * Сколько мин вокруг7
     */
    private int minesAround = 0;

    /**
     *
     */
    public Field() {
    }

    public boolean isOpened() {
        return opened;
    }

    public boolean isMined() {
        return mined;
    }

    public boolean isFlagPlaced() {
        return flagPlaced;
    }

    public int getMinesAround() {
        return minesAround;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public void setFlagPlaced(boolean flagPlaced) {
        this.flagPlaced = flagPlaced;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

    public void setMinesAround(int minesAround) {
        this.minesAround = minesAround;
    }
}
