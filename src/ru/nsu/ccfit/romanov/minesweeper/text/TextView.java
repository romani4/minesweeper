package ru.nsu.ccfit.romanov.minesweeper.text;

import java.util.Scanner;
import ru.nsu.ccfit.romanov.minesweeper.Controller;
import ru.nsu.ccfit.romanov.minesweeper.GameEventListener;
import ru.nsu.ccfit.romanov.minesweeper.Model;
import ru.nsu.ccfit.romanov.minesweeper.ModelSettings;
import ru.nsu.ccfit.romanov.minesweeper.View;

/**
 * Text Interface
 * @author Dmitriy Romanov
 */
public class TextView implements View,Runnable {

    private Model model;
    private GameEventListener controllerListenner;
    
    /**
     * Уже запустили процедуру считывания из входного потока?
     */
    private boolean started = false;
    /**
     * Флаг оповещающий, что пора выходить
     */
    private boolean exitFlag = false;

    public TextView() {
    }

    public void setController(Controller controller) {
        controllerListenner = controller.getListener();
    }

    public void setModel(Model model) {
        this.model = model;

    }
    

    public void run() {
        do {
            typeCommand();
        } while (!exitFlag);
    }

    /**
     * Показать справку об управлении
     */
    private void showHelp() {
        System.out.println("Commands: \"o\" or \"open\" x y - open a cell");
        System.out.println("          \"f\" or \"flag\" x y - set flag to a cell");
        System.out.println("          \"n\" or \"newgame\" x y n - start a new game");
        System.out.println("          \"q\" or \"e\" or \"quit\" or \"exit\" - exit");
        System.out.println("          \"v\" or \"ver\" - about minesweeper");
        System.out.println("          \"h\" or \"help\" - show this help");
    }

    /**
     * Ввод и обработка команды
     */
    private void typeCommand() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print(">> ");
        String cmd = inputScanner.next();
        if (cmd.equals("o") || cmd.equals("open")) {
            int x = inputScanner.nextInt();
            int y = inputScanner.nextInt();
            System.out.println("<< open " + x + " " + y);
            controllerListenner.dig(x - 1, y - 1);
        } else if (cmd.equals("f") || cmd.equals("flag")) {
            int x = inputScanner.nextInt();
            int y = inputScanner.nextInt();
            System.out.println("<< flag " + x + " " + y);
            controllerListenner.placeFlag(x - 1, y - 1);
        } else if (cmd.equals("v") || cmd.equals("ver")) {
            System.out.println("<< ver");
            controllerListenner.about();
        } else if (cmd.equals("h") || cmd.equals("help")) {
            System.out.println("<< help");
            showHelp();
        } else if (cmd.equals("n") || cmd.equalsIgnoreCase("newgame")) {
            ModelSettings settings = new ModelSettings();
            settings.setxSize(inputScanner.nextInt());
            settings.setySize(inputScanner.nextInt());
            settings.setMines(inputScanner.nextInt());
            System.out.println("<< newgame " + settings.getxSize() + " " + settings.getySize() + " " + settings.getMines());
            controllerListenner.newGame(settings);
        } else if (cmd.equals("q") || cmd.equals("e") || cmd.equals("quit") || cmd.equals("exit")) {
            controllerListenner.exit();
        }else if (cmd.equals("")){
        } else {
            System.out.println("Unknown command \"" + cmd + "\"! Type \"h\" for help");
        }
    }

    public void redraw() {
        System.out.print("   ");
        for (int x = 0; x < model.X_SIZE; x++) {
            System.out.print(" " + (x + 1));
            if (x + 1 < 10) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (int y = 0; y < model.Y_SIZE; y++) {
            if (y + 1 < 10) { // для выравнивания по левому краю
                System.out.print(" ");
            }
            System.out.print((y + 1) + " ");
            for (int x = 0; x < model.X_SIZE; x++) {
                Model.ViewInfo info = model.getViewInfo(x, y);
                switch (info) {
                    case CLOSED:
                        System.out.print("[ ]");
                        break;
                    case MINE:
                        System.out.print("[x]");
                        break;
                    case FLAG:
                        System.out.print("[^]");
                        break;
                    case EMPTY:
                        System.out.print("   ");
                        break;
                    case ONE:
                        System.out.print(" 1 ");
                        break;
                    case TWO:
                        System.out.print(" 2 ");
                        break;
                    case THREE:
                        System.out.print(" 3 ");
                        break;
                    case FOUR:
                        System.out.print(" 4 ");
                        break;
                    case FIVE:
                        System.out.print(" 5 ");
                        break;
                    case SIX:
                        System.out.print(" 6 ");
                        break;
                    case SEVEN:
                        System.out.print(" 7 ");
                        break;
                    case EIGTH:
                        System.out.print(" 8 ");
                        break;
                    default:
                        throw new RuntimeException("Redraw error. Unknown cell type");
                }

            }
            System.out.println();
        }
        refreshTimer();
        if (!started) {
            new Thread(this).start();
            started = true;
        }
    }

    public void lose() {
        System.out.println("You loose!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public void win() {
        System.out.println("You win!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public void exit() {
        System.out.println("Game closed!");
        exitFlag = true;
    }

    public void about() {
        System.out.println("Minesweeper by Romanov Dmitriy (dima_rom0991@mail.ru)");
    }

    public void putRecordName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    private void refreshTimer() {
        Float t = new Float(model.getTimer());
        t /= 1000;
        System.out.println("Time: " + t);
    }


}
