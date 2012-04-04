package ru.nsu.ccfit.romanov.minesweeper;

import ru.nsu.ccfit.romanov.minesweeper.gui.GraphicalView;
import ru.nsu.ccfit.romanov.minesweeper.text.TextView;

/**
 * Игра реализована с использованием парадигмы MVC.
 * Поэтому игру можно легко запустить в двух режимах(графическом и тектовом).
 * Можно запустить в нескольких режимах (но не стоит запускать больше 2 текстовых режимов)
 * @author Dmitriy Romanov
 */
public class Main {

    /**
     * Показать справку по запуску ч/з команднную строку
     */
    private static void showHelp(){
        System.out.println("Minesweeper: \"/?\" or \"help\" or \"-h\" or \"-help\" or \"--help\" - show this help");
        System.out.println("             \"-t\" or \"t\" or \"text\" or \"--text\" - start game in text mod");
        System.out.println("             \"-g\" or \"g\" or \"gui\" or \"--gui\" - start game in GUI mod (as default)");
        System.out.println("You can run game in several modes");
    }

    /**
     * Разбор командной строки и выбор вида
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Controller controller = new Controller();
        
        if (args.length >= 1 ) {
            for( String arg:args ){
                if(arg.equalsIgnoreCase("-t")||arg.equalsIgnoreCase("t")||arg.equalsIgnoreCase("text")||arg.equalsIgnoreCase("--text")){
                    controller.addView(new TextView());
                } else if(arg.equalsIgnoreCase("-g")||arg.equalsIgnoreCase("g")||arg.equalsIgnoreCase("gui")||arg.equalsIgnoreCase("--gui")){
                    controller.addView(new GraphicalView());
                } else {
                    System.out.println( "Unknown argument \""+arg+"\"" );
                }
            }
        } else if (args.length == 1 && (args[0].equals("-h") || args[0].equals("-help") || args[0].equals("--help") || args[0].equals("help") || args[0].equals("/?"))) {
            showHelp();
            return;
        } else {
            controller.addView(new GraphicalView());
        }
    }
}
