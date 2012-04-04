package ru.nsu.ccfit.romanov.minesweeper.gui;

import ru.nsu.ccfit.romanov.minesweeper.resources.Resources;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import ru.nsu.ccfit.romanov.minesweeper.Model.ViewInfo;

/**
 * Компонент объединяющий кнопки
 * @author Dmitriy Romanov
 */
public class ButtonsPlace extends JComponent {

    public final int X_SIZE;
    public final int Y_SIZE;
    private final JButton[][] buttons;

    public ButtonsPlace(int xSize, int ySize) {
        super();
        X_SIZE = xSize;
        Y_SIZE = ySize;
        setLayout(new GridLayout(Y_SIZE, X_SIZE));
        buttons = new JButton[X_SIZE][Y_SIZE];

        for (int y = 0; y < Y_SIZE; y++) {
            for (int x = 0; x < X_SIZE; x++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setIcon(Resources.getIcon(ViewInfo.CLOSED));

                add(buttons[x][y]);
            }
        }
    }

    public JButton getButtons(int x, int y) {
        return buttons[x][y];
    }
}
