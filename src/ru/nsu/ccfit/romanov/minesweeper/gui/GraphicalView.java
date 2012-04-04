package ru.nsu.ccfit.romanov.minesweeper.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import ru.nsu.ccfit.romanov.minesweeper.Model;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import ru.nsu.ccfit.romanov.minesweeper.Controller;
import ru.nsu.ccfit.romanov.minesweeper.View;
import ru.nsu.ccfit.romanov.minesweeper.GameEventListener;
import ru.nsu.ccfit.romanov.minesweeper.resources.Resources;

/**
 * Graphical User Interface
 * @author Dmitriy Romanov
 */
public class GraphicalView implements View {

    /**
     * Ширина экрана
     */
    public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /**
     * Высота экрана
     */
    public static final int SCREEN_HEIGTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private Model model;
    private GameEventListener controllerListenner;
    
    private final JFrame frame = new JFrame("Mineswepeer");
    private MenuBar menuBar;
    private ButtonsPlace buttonsPlace;
    private final JLabel timeLabel = new JLabel("0");
    private final Timer timer = new Timer(1000 / 24, new ActionListener() {//fps 24

            public void actionPerformed(ActionEvent e) {
                refreshTimer();
            }
    });

    public GraphicalView() {
        frame.setLayout(new BorderLayout());
    }

    /**
     * Подключает модель к виду, извлекая из неё информацию для отрисовки
     * @param model модель
     */
    public void setModel(Model model) {
        this.model = model;
        frame.setSize(model.X_SIZE * 32, model.Y_SIZE * 32 + 50);
        try{
            int xPos = new Random().nextInt(SCREEN_WIDTH-frame.getWidth());
            int yPos = new Random().nextInt(SCREEN_HEIGTH-frame.getHeight());
            frame.setLocation(xPos , yPos );
        }catch(IllegalArgumentException ex){
            frame.setLocation(0 , 0);
        }
        
        buttonsPlace = new ButtonsPlace(model.X_SIZE, model.Y_SIZE);
        for (int y = 0; y < model.Y_SIZE; y++) {
            for (int x = 0; x < model.X_SIZE; x++) {
                buttonsPlace.getButtons(x, y).addMouseListener(new ButtonMouseListenner(x, y, controllerListenner));
            }
        }
        frame.add(buttonsPlace, BorderLayout.CENTER);
        frame.add(timeLabel, BorderLayout.PAGE_END);
        frame.setVisible(true);
        
        timer.start();
    }

    public void setController(Controller controller) {
        this.controllerListenner = controller.getListener();
        menuBar = new MenuBar(frame, controllerListenner);
        frame.setJMenuBar(menuBar);
        frame.addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(WindowEvent e) {
                        controllerListenner.exit();
                    }
                });
        
    }

    public void redraw() {
        for (int y = 0; y < model.Y_SIZE; y++) {
            for (int x = 0; x < model.X_SIZE; x++) {
                JButton currentButton = buttonsPlace.getButtons(x, y);
                Icon icon = Resources.getIcon(model.getViewInfo(x, y));
                if (!currentButton.getIcon().equals(icon)) {
                    currentButton.setIcon(icon);
                }
            }
        }
    }

    public void lose() {
        new LoseDialog(frame, controllerListenner);
    }

    public void exit() {
        timer.stop();
        frame.dispose();
    }

    public void about() {
        new AboutDialog(frame, controllerListenner);
    }

    public void win() {
        new WinDialog(frame, controllerListenner);
    }

    public void putRecordName() {
        new AddRecordDialog(frame, controllerListenner);
    }

    private void refreshTimer() {
        Float t = new Float(model.getTimer());
        t /= 1000;
        timeLabel.setText(t.toString());
    }
}
