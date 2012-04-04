package ru.nsu.ccfit.romanov.minesweeper.gui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import ru.nsu.ccfit.romanov.minesweeper.GameEventListener;

/**
 * Диалоговое окно, отображающее сообщение, о том что пользователь проиграл
 * @author Dmitriy Romanov
 */
public class LoseDialog extends JDialog {

    private final JLabel label1 = new JLabel("You loose!!!");
    private final JButton newGameButton = new JButton("NewGame");
    private final JButton exitButton = new JButton("Exit");
    private final GameEventListener controllerListenner;
    private final Frame owner;

    public LoseDialog(Frame owner, GameEventListener controllerListenner) {
        super(owner);
        this.controllerListenner = controllerListenner;
        this.owner = owner;

        setLayout(new GridLayout(3, 1));


        add(label1);
        add(newGameButton);
        add(exitButton);

        setLocation(owner.getX()+50, owner.getY()+50);

        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        exitButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        LoseDialog.this.controllerListenner.exit();
                        dispose();
                    }
                });

        newGameButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        new NewGameDialog(LoseDialog.this.owner, LoseDialog.this.controllerListenner);
                        dispose();
                    }
                });
    }
}
