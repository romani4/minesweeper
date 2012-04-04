package ru.nsu.ccfit.romanov.minesweeper.resources;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import ru.nsu.ccfit.romanov.minesweeper.Model;

/**
 * Иконки
 * @author Dmitriy Romanov
 */
public class Resources {
    public static final String path ="./src/ru/nsu/ccfit/romanov/minesweeper/resources/";
//    public static final String path ="./img/";


    private static final Icon closed = new ImageIcon( path + "closed.png" );
    private static final Icon empty = new ImageIcon( path + "empty.png" );
    private static final Icon one = new ImageIcon( path + "one.png" );
    private static final Icon two = new ImageIcon( path + "two.png" );
    private static final Icon three = new ImageIcon( path + "three.png" );
    private static final Icon four = new ImageIcon( path + "four.png" );
    private static final Icon five = new ImageIcon( path + "five.png" );
    private static final Icon six = new ImageIcon( path + "six.png" );
    private static final Icon seven = new ImageIcon( path + "seven.png" );
    private static final Icon eigth = new ImageIcon( path + "eigth.png" );
    private static final Icon flag = new ImageIcon( path + "flag.png" );
    private static final Icon mine = new ImageIcon( path + "mine.png" );

    public static final Icon getIcon( Model.ViewInfo info ){
        switch(info){
            case ONE:
                return one;
            case TWO:
                return two;
            case THREE:
                return three;
            case FOUR:
                return four;
            case FIVE:
                return five;
            case SIX:
                return six;
            case SEVEN:
                return seven;
            case EIGTH:
                return eigth;
            case CLOSED:
                return closed;
            case FLAG:
                return flag;
            case MINE:
                return mine;
            case EMPTY:
            default:
                return empty;
        }
        
    }

}
