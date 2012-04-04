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

/**
 * Диалоговое окно, позволяющее пользователю добавить рекорд
 * @author Dmitriy Romanov
 */
public class AddRecordDialog extends JDialog {

    private final JLabel label1 = new JLabel("Put your name ");
    private final JTextField nameField = new JTextField("guest");
    private final JButton okButton = new JButton("Ok");
    private final JButton cancelButton = new JButton("Cansel");
    private final GameEventListener controllerListenner;
    private final Frame owner;

    public AddRecordDialog(Frame owner, GameEventListener controllerListenner) {
        super(owner);
        this.controllerListenner = controllerListenner;
        this.owner = owner;

        setLayout(new GridLayout(2, 2));

        add(label1);
        add(nameField);
        add(okButton);
        add(cancelButton);

        setLocation(owner.getX()+50, owner.getY()+50);
        pack();
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AddRecordDialog.this.controllerListenner.addRecord(nameField.getText());
                dispose();
            }
        });
    }
}
