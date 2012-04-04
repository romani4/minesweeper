package ru.nsu.ccfit.romanov.minesweeper;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitriy Romanov
 */
public class Model {

    public final int X_SIZE;
    public final int Y_SIZE;
    public final int MINES;
    
    /**
     * Клетки поля
     */
    private final Field[][] fields;

    /**
     * Первая ли раскопка
     */
    private boolean firstTry = true;
    
    /**
     * Время начала игры
     */
    private long startTime = 0;

    /**
     * Время завершения игры
     */
    private long stopTime = 0;

    /**
     * Заблокирована ли модель(после проигрыша или победы модель блокируется и изменить её невозможно)
     */
    private boolean blocked = false;

    /**
     * Сколько полей уже отрыто
     */
    private int opened = 0;

    /**
     * связь модели с контроллером
     */
    private final GameEventListener controllerListenner;

    /**
     * Таблица рекордов
     */
    private Records records;
    

    /**
     *
     * @param xSize количество столбцов
     * @param ySize количество строк
     * @param mines заминированных клеток
     */
    public Model(ModelSettings settings, Controller controller) {
        controllerListenner = controller.getListener();
        try {
            records = new Records("records.dat");
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (settings.getxSize() < 0 || settings.getySize() < 0 || (settings.getMines() > (settings.getxSize() * settings.getySize()) - 9)) {
            throw new InvalidParameterException("xSize<0 or ySize<0 or too mush mines");
        }
        X_SIZE = settings.getxSize();
        Y_SIZE = settings.getySize();
        MINES = settings.getMines();

        // не минируем поле (оставляем это до первых раскопок)
        fields = new Field[X_SIZE][Y_SIZE];
        for (int y = 0; y < Y_SIZE; y++) {
            for (int x = 0; x < X_SIZE; x++) {
                fields[x][y] = new Field();
            }
        }
    }

    /**
     * Расставить мины, в позицию (firstX,firstY) не поставится мина.
     * Так сделано, чтобы избежать подрыва при первом-же открытии клетки
     * @param firstX координата x
     * @param firstY координата y
     */
    private void placeMines(int firstX, int firstY) {
        if (firstTry) { // если копаем в первый раз
            firstTry = false;
            
            final Random rnd = new Random();
            int minesPlaced = 0;
            
            while (minesPlaced < MINES) {
                int x = rnd.nextInt(X_SIZE);
                int y = rnd.nextInt(Y_SIZE);
                if ( (x - 1 <= firstX && firstX <= x + 1) &&
                     (y - 1 <= firstY && firstY <= y + 1) ) {
                    continue; // первым выстрелом нельзя подорваться на мине
                }
                Field currentField = fields[x][y];
                if (currentField.isMined()) {
                    continue; // две мины на одну клетку - это уж слишком )
                }
                currentField.setMined(true);
                minesPlaced++;
            }

            // подсчёт мин вокруг
            for (int y = 0; y < Y_SIZE; y++) {
                for (int x = 0; x < X_SIZE; x++) {
                    int minesAround = 0;
                    Field field = fields[x][y];

                    // извиняюсь, лень было проверять границы массива(просто игнорируем это дело) =)
                    try {
                        if (fields[x - 1][y - 1].isMined()) {
                            minesAround++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (fields[x - 1][y].isMined()) {
                            minesAround++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (fields[x - 1][y + 1].isMined()) {
                            minesAround++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }

                    try {
                        if (fields[x][y - 1].isMined()) {
                            minesAround++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (fields[x][y + 1].isMined()) {
                            minesAround++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }

                    try {
                        if (fields[x + 1][y - 1].isMined()) {
                            minesAround++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (fields[x + 1][y].isMined()) {
                            minesAround++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    try {
                        if (fields[x + 1][y + 1].isMined()) {
                            minesAround++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    }
                    field.setMinesAround(minesAround);
                }
            }
            startTime = System.currentTimeMillis();
        }
    }

    /**
     * Глубина рекурсивного вызова open(x,y)
     * Нужна чтобы каждый раз не обновлять вид
     */
    private int recursionDepth = 0;

    /**
     * Раскопать клетку (x,y)
     * @param x координата x
     * @param y координата y
     */
    public void open(int x, int y) {
        if (!blocked) {
            placeMines(x, y);

            final Field field;
            try {
                field = fields[x][y];
            } catch (ArrayIndexOutOfBoundsException ex) {
                return;
            }
            if (field.isOpened()) {
                return;
            }
            if (field.isMined()) {
                lose();
                return;
            }

            recursionDepth++;
            if (0 == field.getMinesAround()) { // смотрим всё вокруг
                field.setOpened(true);
                if (x > 0) {
                    open(x - 1, y);
                }
                if (y > 0) {
                    open(x, y - 1);
                }
                if (y < Y_SIZE - 1) {
                    open(x, y + 1);
                }
                if (x < X_SIZE - 1) {
                    open(x + 1, y);
                }
            }
            opened++;
            field.setOpened(true);

            recursionDepth--;
            if (0 == recursionDepth) {
                controllerListenner.redraw();
            }
            if ((X_SIZE * Y_SIZE - opened) == MINES) {
                stopTime = System.currentTimeMillis();
                blocked = true;
                controllerListenner.win();
                controllerListenner.redraw();
            }
        }

    }

    /**
     * Пометить клетку (x,y)
     * @param x координата x
     * @param y координата y
     */
    public void setFlag(int x, int y) {
        if (!blocked) {
            Field field = fields[x][y];
            if (field.isOpened()) {
                return;
            }
            if (field.isFlagPlaced()) {
                field.setFlagPlaced(false);
            } else {
                field.setFlagPlaced(true);
            }
            controllerListenner.redraw();
        }
    }

    private void lose() {
        stopTime = System.currentTimeMillis();
        blocked = true;
        for (int x = 0; x < X_SIZE; x++) {
            for (int y = 0; y < Y_SIZE; y++) {
                Field field = fields[x][y];
                if (field.isMined()) {
                    field.setOpened(true);
                }
            }
        }
        controllerListenner.lose();
        controllerListenner.redraw();
    }

    public enum ViewInfo {

        CLOSED,
        MINE,
        FLAG,
        EMPTY,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGTH
    }

    public ViewInfo getViewInfo(int x, int y) {
        Field field = fields[x][y];

        if (field.isFlagPlaced() && !field.isOpened()) {
            return ViewInfo.FLAG;
        }

        if (!field.isOpened()) {
            return ViewInfo.CLOSED;
        }

        if (field.isMined()) {
            return ViewInfo.MINE;
        }

        switch (field.getMinesAround()) {
            case 1:
                return ViewInfo.ONE;
            case 2:
                return ViewInfo.TWO;
            case 3:
                return ViewInfo.THREE;
            case 4:
                return ViewInfo.FOUR;
            case 5:
                return ViewInfo.FIVE;
            case 6:
                return ViewInfo.SIX;
            case 7:
                return ViewInfo.SEVEN;
            case 8:
                return ViewInfo.EIGTH;
            case 0:
            default:
                return ViewInfo.EMPTY;
        }
    }

    public void addRecord(String name) {
        ModelSettings settings = new ModelSettings();
        settings.setxSize(X_SIZE);
        settings.setySize(Y_SIZE);
        settings.setMines(MINES);
        records.addRecord(name, getTimer(), settings);
        try {
            records.save();
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getTimer() {
        if (0 == startTime) {
            return 0;
        }
        if (blocked) {
            return stopTime - startTime;
        }
        return System.currentTimeMillis() - startTime;
    }
}
