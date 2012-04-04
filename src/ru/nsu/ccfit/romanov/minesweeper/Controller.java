package ru.nsu.ccfit.romanov.minesweeper;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Контроллер
 * @author Dmitriy Romanov
 */
public class Controller {

    private Model model;
    private List<View> views = new LinkedList<View>();

    public Controller() {
        model = new Model(new ModelSettings(), this);
    }

    /**
     * Добавляет вид в список видов и отрисовывает
     * @param view
     */
    public void addView(View view) {
        if(null == model){
            throw new RuntimeException("Invalid MVC use");
        }
        view.setController(this);
        view.setModel(model);
        views.add(view);
        view.redraw();
    }

    /**
     * Обработчик игровых событий
     * @see GameEventListener
     */
    private GameEventListener listener = new GameEventListener() {

        public void dig(int x, int y) {
            model.open(x, y);
        }

        public void placeFlag(int x, int y) {
            model.setFlag(x, y);
        }

        public void newGame(ModelSettings settings) {
            for (View view : views) {
                view.exit();
            }

            /* new model */
            model = new Model(settings, Controller.this);

            /* new views list (copy old views type) */
            List<View> oldViews = views;
            views = new LinkedList<View>();
            for (View oldView : oldViews) {
                View newView = null;
                try {
                    newView = oldView.getClass().newInstance();
                } catch (InstantiationException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                addView(newView);
            }
        }

        public void win() {
            for (View view : views) {
                view.win();
            }
        }

        public void lose() {
            for (View view : views) {
                view.lose();
            }
        }

        public void exit() {
            for (View view : views) {
                view.exit();
            }
        }

        public void about() {
            for (View view : views) {
                view.about();
            }
        }

        public void addRecord(String name) {
            model.addRecord(name);
        }

        public void recordsTable() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void redraw() {
            for (View view : views) {
                view.redraw();
            }
        }
    };

    public GameEventListener getListener() {
        return listener;
    }
}
