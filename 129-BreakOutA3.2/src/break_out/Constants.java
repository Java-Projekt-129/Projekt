package break_out;

import java.awt.Color;

/**
 * A class that contains all constant values to configure the game
 * 
 * @author dmlux,modified by I. Schumacher. modified by Jan Erik Riede 675875, modified by Lorenzo Dal Molin 675115 
 * 
 */
public class Constants {

    /**
     * The screen width in pixels
     */
    public static final double SCREEN_WIDTH = 800;
    
    /**
     * The screen height in pixels
     */
    public static final double SCREEN_HEIGHT = 625;
    
    /**
     * the application name
     */
    public static final String APP_TITLE = "iBreakOut";

    /**
     * Debugging flag for special rendering hints
     */
    public static final boolean DEBUG_MODE = false;

    /**
     * The background color for the game menu
     */
    public static final Color BACKGROUND = new Color(52, 152, 219);

    /**
     * Amount of columns for blocks
     */
    public static final int SQUARES_X = 20;

    /**
     * Amount of the rows
     */
    public static final int SQUARES_Y = 25;
    
    /**
     * The paddle width in pixels
     */
    public static final double PADDLE_WIDTH = 70;
    
    /**
     * The paddle height in pixels
     */
    public static final double PADDLE_HEIGHT = 15;
    
    /**
     * The ball diameter in pixels
     */
    public static final double BALL_DIAMETER = 15;
       
    /**
     * The paddle speed
     */
    public static final double DX_MOVEMENT = 4.5;
    
    /**
     * The ball speed
     */
    public static final double BALL_SPEED = 1.20;
    /**
     * Die X Postion der Steine berechnet im Mittelpunkt 
     */
    public static final int STONE_OFFSET_X = 2;
    
    /**
     * Die Y Psotion der Steine berechnet im Mittelpunkt 
     */
    public static final int STONE_OFFSET_Y = 2;
    
    /**
     * Die breite der Steine
     */
    public static final double STONE_WIDTH = Constants.SCREEN_WIDTH/Constants.SQUARES_X-Constants.STONE_OFFSET_X*2;
    
    /**
     * Die hoehe der Steine 
     */
    public static final double STONE_HEIGHT = Constants.SCREEN_HEIGHT/Constants.SQUARES_Y-Constants.STONE_OFFSET_Y*2;
    
}
