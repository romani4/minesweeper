package ru.nsu.ccfit.romanov.minesweeper;

import java.util.EventListener;

/**
 * Обработчик игровых событий контроллера
 * @author Dmitriy Romanov
 */
public interface GameEventListener extends EventListener {

    /**
     * Открыть(копать) позицию x,y
     * @param x координата x
     * @param y координата y
     */
    void dig(int x, int y);

    /**
     * Поместить флажок на позицию x,y
     * @param x координата x
     * @param y координата y
     */
    void placeFlag(int x, int y);

    /**
     * Начать новую игру
     * @see ModelSettings
     * @param settings параметры новой игры
     */
    void newGame(ModelSettings settings);

    /**
     * Победить (известить все виды о победе)
     */
    void win();

    /**
     * Проиграть
     * @see win()
     */
    void lose();

    /**
     * Выйти
     */
    void exit();

    /**
     * Показать информацию об игре
     */
    void about();

    /**
     * Показать таблицу рекордов
     */
    void recordsTable();

    /**
     * Добавить результат текущей игры в качестве рекорда рекорд
     * @param name имя игрока взятое у вида
     */
    void addRecord(String name);

    /**
     * Отрисовать все виды
     */
    void redraw();
}
