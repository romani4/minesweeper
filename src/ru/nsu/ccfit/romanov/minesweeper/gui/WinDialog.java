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
 * Диалоговое окно, отображающее сообщение, о том что пользователь победил
 * @author Dmitriy Romanov
 */
public class WinDialog extends JDialog {

    private final JLabel label1 = new JLabel("You win!!!");
    private final JButton newGameButton = new JButton("NewGame");
    private final JButton exitButton = new JButton("Exit");
    private final JButton addRecordButton = new JButton("Add record");
    private final GameEventListener controllerListenner;
    private final Frame owner;

    public WinDialog(Frame owner, GameEventListener controllerListenner) {
        super(owner);
        this.controllerListenner = controllerListenner;
        this.owner = owner;

        setLayout(new GridLayout(4, 1));


        add(label1);
        add(addRecordButton);
        add(newGameButton);
        add(exitButton);

        setLocation(owner.getX()+50, owner.getY()+50);

        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        exitButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        WinDialog.this.controllerListenner.exit();
                        dispose();
                    }
                });

        newGameButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        new NewGameDialog(WinDialog.this.owner, WinDialog.this.controllerListenner);
                        dispose();
                    }
                });

        addRecordButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        // new AddRecordDialog(WinDialog.this.owner, WinDialog.this.controllerListenner);
                    }
                });
    }
}
