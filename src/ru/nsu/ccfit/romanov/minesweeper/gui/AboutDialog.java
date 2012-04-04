package ru.nsu.ccfit.romanov.minesweeper.gui;

import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import ru.nsu.ccfit.romanov.minesweeper.GameEventListener;

/**
 * Диалоговое окно, отображающее информацию о программе
 * @author Dmitriy Romanov
 */
public class AboutDialog extends JDialog {

    private final GameEventListener controllerListenner;
    private final JLabel label1 = new JLabel("  Mineswepeer");
    private final JLabel label2 = new JLabel("by Romanov Dmitriy");
    private final JLabel label3 = new JLabel("dima_rom0991@mail.ru");

    public AboutDialog(Frame owner, GameEventListener controllerListenner) {
        super(owner);
        this.controllerListenner = controllerListenner;
        setLayout(new GridLayout(3, 1));

        add(label1);
        add(label2);
        add(label3);

        setLocation(owner.getX()+50, owner.getY()+50);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
