package ru.nsu.ccfit.romanov.minesweeper.gui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import ru.nsu.ccfit.romanov.minesweeper.GameEventListener;
import ru.nsu.ccfit.romanov.minesweeper.ModelSettings;

/**
 * Диалоговое окно, позволяющее начать новую игру
 * @author Dmitriy Romanov
 */
public class NewGameDialog extends JDialog {

    private final GameEventListener controllerListenner;
    private final JLabel label1 = new JLabel("Width ("+ModelSettings.MIN_SIZE+"-"+ModelSettings.MAX_SIZE+")");
    private final JLabel label2 = new JLabel("Height ("+ModelSettings.MIN_SIZE+"-"+ModelSettings.MAX_SIZE+")");
    private final JLabel label3 = new JLabel("Mines ("+ModelSettings.MIN_MINES+"-"+ModelSettings.MAX_MINES+")");
    private final JTextField xSizeField = new JTextField("9");
    private final JTextField ySizeField = new JTextField("9");
    private final JTextField minesField = new JTextField("10");
    private final JButton okButton = new JButton("Ok");
    private final JButton cancelButton = new JButton("Cansel");

    public NewGameDialog(Frame owner, GameEventListener controllerListenner) {
        super(owner);
        this.controllerListenner = controllerListenner;
        setLayout(new GridLayout(4, 2));

        setLocation(owner.getX()+50, owner.getY()+50);
        add(label1);
        add(xSizeField);
        add(label2);
        add(ySizeField);
        add(label3);
        add(minesField);
        add(okButton);
        add(cancelButton);

        pack();
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cancelButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        okButton.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        ModelSettings set = new ModelSettings();
                        try {
                            set.setxSize(Integer.parseInt(xSizeField.getText()));
                        } catch (NumberFormatException ex) {
                            set.setxSize(9);
                        }
                        try {
                            set.setySize(Integer.parseInt(ySizeField.getText()));
                        } catch (NumberFormatException ex) {
                            set.setySize(9);
                        }
                        try {
                            set.setMines(Integer.parseInt(minesField.getText()));
                        } catch (NumberFormatException ex) {
                            set.setMines(10);
                        }
                        NewGameDialog.this.controllerListenner.newGame(set);
                        dispose();
                    }
                });

    }
}
