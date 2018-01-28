package break_out.model;

import break_out.Constants;
import break_out.controller.JSONReader;

/**
 * This object contains information about the running game
 * 
 * @author dmlux
 * @author I. Schumacher
 * modified by 	Jan Erik Riede - 675875
 * modified by 	Lorenzo Dal Molin - 678115
 * Gruppe 129
 */
public class Level extends Thread {

    /**
     * The game to which the level belongs 
     */
    private Game game;
	 
    /**
   	 * The number of the level
   	 */
    private int levelnr;
       
    /**
	 * The score of the level
	 */
    private int score;
    
    
    // Hier die Variablen direction.getDx() und direction.getDy() deklarieren, bitte an den JavaDoc-Kommentar denken, siehe andere Variablendeklarationen
    
    
    /**
     * Ball auf false gesetzt damit erst mit Space das Spiel gestartet wird
     */
    private boolean ballWasStarted = false;
    
    /**
     * Instanzierung des Balls
     */
    private Ball ball;
    
    /**
     * Instanzierung des Paddles
     */
    private Paddle paddle;
    
    /**
     * Instanzierung der Steine mit begrentztem Array
     * sind in einem rechteck von 25 * 20 feldern angeornet
     */
    private Stone[][] stones = new Stone[25][20];
  
        
    /**
     * Der Konstruktor instanziiert einen neuen Level:
     * @param game Das zugehoerige Game-Objekt
     * @param levelnr Die Nummer des zu instanziierenden Levels
     * @param score Der bisher erreichte Scorewert
     */
    public Level(Game game, int levelnr, int score) {
    	this.game = game;
    	this.levelnr = levelnr ;
    	this.score = score;
        loadLevelData(levelnr);
        ball = new Ball(new Position((Constants.SCREEN_WIDTH-(Constants.BALL_DIAMETER))/2, Constants.SCREEN_HEIGHT-(Constants.BALL_DIAMETER)-Constants.PADDLE_HEIGHT), new Vector2D(1.0,1.0));
        paddle = new Paddle(new Position(Constants.SCREEN_WIDTH/2-Constants.PADDLE_WIDTH/2, Constants.SCREEN_HEIGHT-(Constants.PADDLE_HEIGHT)));
    }

    
    /**
     * Getter fuer den ball
     * @return ball
     */
    
    public Ball getBall() {
        return ball;
    }
    /**
     * Getter fuer das Paddle
     * @return Paddle
     */
    public Paddle getPaddle() {
    	return paddle;
    }
    /**
     * Getter fuer die Steine
     * @return Stones
     */
    public Stone[][] getStones(){
    	return stones;
    }
       
    /**
     * Setzt ballWasStarted auf true, d.h. der Ball "startet", 
     * weil so die bedingten Anweisungen in der while-Schleife der run-Methode ausgefuehrt werden.
     */
    public void startBall() {
        ballWasStarted = true;
    }

    /**
     * Setzt ballWasStarted auf false, d.h. der Ball "pausiert", weil so die bedingten Anweisungen in der while-Schleife 
     * der run-Methode nicht ausgefuehrt werden.
     */
    public void stopBall() {
        ballWasStarted = false;
    }
    
    /**
     * Liefert den booleschen Wert der Variablen ballWasStarted
     * @return ballWasStarted True, wenn sich der Ball bewegt, sonst false
     */
    public boolean ballWasStarted() {
        return ballWasStarted;
    }


    /**
     * Diese Methode enthaelt die Threadlogik, d.h. hier wird festgelegt, was im Thread ablaeuft.
     */
    public void run() {
    	/**
    	 * game informiert den observer dass das Spiel gestartet wird
    	 */
    	game.notifyObservers();
    		
    	/**
    	 *  Endlosschleife fuer den Ablauf
    	 */
    	while (true) {
    		/**
    		 *  wenn ballWasStarted wahr ist (d.h. der Ball bewegt sich) werden die Methoden updatePostion
    		 *  ,reactOnBoarder hitsPaddle ausgefuert  
    		 * 
    		 */
	        if (ballWasStarted) {
	            //Der Ball wird auf seine Postition ueberprueft
	        	ball.updatePosition();
	            // Der Ball wird auf sein abprallverhalten mit der Wand ueberpruft
	            ball.reactOnBorder();
	            //* Der Ball wird auf das Abprallverhalten am Paddle ueberpruft
	            ball.hitsPaddle(paddle);
	            // Wenn der Ball das Padlle beruehrt wird der Observer benachrichtigt
	            game.notifyObservers();
	            //Das Paddle updatet seine Position
	            paddle.updatePosition();
	            //Das Stopverhalten des Paddles
	            paddle.reactOnBorder();
	            
	                
	        }
	        // Der Thread pausiert kurz
	        try {
	            Thread.sleep(4);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
    	}   
    }
   
    
    
    /**
    * Zugriff auf die der Levelnummer zugeordnete JSON-Datei
    * Der Reader fuer die JSONReader datei zum zeichnen des Steinmusters
    * @param levelnr Die Nummer X fuer die LevelX.json Datei
    * @param levelnr die Nummer des aktuellen Levels
    */
    
    private void loadLevelData(int levelnr) {
    	JSONReader reader ;
    	// laden der Datei als String 
    	String fileName="res/Level"
    			+ levelnr
    			+".json";
    	// erzeugung von neuem Json Reader
    	reader=new JSONReader(fileName);
    	//Deklaration des Arrays in dem die Steine angeorndet sind 
    	int[][] intArray = reader.getStones2DArray();   	
    	//Grenzen vom  Array gesetzt 
    	for (int i=0; i < intArray.length; i++) { 
    		for (int j=0; j < intArray[i].length; j++) {
    			// erzeugen des Arrays 
    			stones[i][j] = new Stone(intArray[i][j]);
    		}
    	}
    	
    	
    		
    }
    
}
    


	
