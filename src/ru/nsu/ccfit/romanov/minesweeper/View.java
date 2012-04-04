package ru.nsu.ccfit.romanov.minesweeper;

/**
 *
 * @author Dmitriy Romanov
 */
public interface View {

    void setModel(Model model);

    void setController(Controller controller);

    /**
     * Обновиться
     */
    void redraw();

    /**
     * Отобразить поражение
     */
    void lose();

    /**
     * Отобразить победу
     */
    void win();

    /**
     * Зактыть вид
     */
    void exit();

    /**
     * Отобразить информацию о программе
     */
    void about();

    /**
     * Отобразить поле ввода для рекорда
     */
    void putRecordName();

}
