package ru.nsu.ccfit.romanov.minesweeper.gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import ru.nsu.ccfit.romanov.minesweeper.GameEventListener;

/**
 * Менюшка
 * @author Dmitriy Romanov
 */
public class MenuBar extends JMenuBar {

    private final JMenu gameMenu = new JMenu("Game");
    private final JMenu helpMenu = new JMenu("Help");
    private final JMenuItem newGameItem = new JMenuItem("New game");
    private final JMenuItem recordsItem = new JMenuItem("Records table");
    private final JMenuItem exitItem = new JMenuItem("Exit");
    private final JMenuItem aboutItem = new JMenuItem("About");
    private final GameEventListener controllerListenner;
    private final Frame owner;

    public MenuBar(Frame owner, GameEventListener controllerListenner) {
        super();
        this.owner = owner;
        this.controllerListenner = controllerListenner;
        gameMenu.add(newGameItem);
        gameMenu.add(recordsItem);
        gameMenu.add(exitItem);

        helpMenu.add(aboutItem);

        add(gameMenu);
        add(helpMenu);

        newGameItem.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        NewGameDialog newGameDialog = new NewGameDialog(MenuBar.this.owner, MenuBar.this.controllerListenner);
                    }
                });

        exitItem.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        MenuBar.this.controllerListenner.exit();
                    }
                });

        aboutItem.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        MenuBar.this.controllerListenner.about();
                    }
                });

    }
}
