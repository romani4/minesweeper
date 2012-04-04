package ru.nsu.ccfit.romanov.minesweeper.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import ru.nsu.ccfit.romanov.minesweeper.GameEventListener;

/**
 * Обработчик событий для каждой кнопки
 * @author Dmitriy Romanov
 */
public class ButtonMouseListenner extends MouseAdapter {

    private final int x;
    private final int y;
    private final GameEventListener controllerListenner;

    public ButtonMouseListenner(int x, int y, GameEventListener controllerListenner) {
        this.x = x;
        this.y = y;
        this.controllerListenner = controllerListenner;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (MouseEvent.BUTTON1 == e.getButton()) {
            controllerListenner.dig(x, y);
        } else if (MouseEvent.BUTTON3 == e.getButton()) {
            controllerListenner.placeFlag(x, y);
        }
    }
}
