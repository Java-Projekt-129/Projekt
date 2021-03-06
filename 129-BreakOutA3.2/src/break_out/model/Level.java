package break_out.model;

import java.util.ArrayList;
import java.util.List;

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
 * Die Klasse wurde von der Gruppe 129 im zuge verschiedener Aufgabenteile bearbeitet
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
    /**
     * Ball auf false gesetzt damit erst mit Space das Spiel gestartet wird
     */
    private boolean ballWasStarted = false;
    /**
     * Abbruch bedingung fuer die Whileschleife 
     */
    private boolean Abbruch= false ;
    /**
     * Variablen Dekalaration fuer die Leben der Spieler 
     */
    private int lifes;
    /**
     * Instanzierung des Balls
     */
    private Ball ball;
    
    
    /**
     * Instanzierung des Paddles
     */
    private Paddle paddle;
    
    /**
     * Erstellt eine neue Steineliste 
     */
     private List<List<Stone>> stones = new ArrayList<List<Stone>>();
  
        
    /**
     * Der Konstruktor instanziiert einen neuen Level:
     * @param game Das zugehoerige Game-Objekt
     * @param levelnr Die Nummer des zu instanziierenden Levels
     * @param score Der bisher erreichte Scorewert
     */
    public Level(Game game, int levelnr, int score) {
    	
    	//Instanziiren des Game Objekts
    	this.game = game;
    	
    	//Nummer des zu instanziirenden levels
    	this.levelnr = levelnr ;
    	
    	//der bisher erreichte scorewert
    	this.score = score;
        
    	//laedt die Datei JSOnreader und speichert ihn als String
    	loadLevelData(levelnr);
        
    	// Deklariern des Ball Objektes 
        ball = new Ball(new Position((Constants.SCREEN_WIDTH-(Constants.BALL_DIAMETER))/2, Constants.SCREEN_HEIGHT-(Constants.BALL_DIAMETER)-Constants.PADDLE_HEIGHT), new Vector2D(1.0,1.0));
        
        //Deklariern des Paddle Objektes 
        paddle = new Paddle(new Position(Constants.SCREEN_WIDTH/2-Constants.PADDLE_WIDTH/2, Constants.SCREEN_HEIGHT-(Constants.PADDLE_HEIGHT)));
    }

    
    /**
     * Getter fuer den ball
     * @return ball rueckgabe des Balles 
     */
    public Ball getBall() {
        return ball;
    }
    
    /**
     * Getter fuer das Paddle
     * @return Paddle rueckgabe des Paddles 
     */
    public Paddle getPaddle() {
    	return paddle;
    }
    
    /**
     * Getter fuer die Steine
     * @return Stones ruckgabe von dem Stein 
     */
    public List<List<Stone>> getStones(){
    	return stones;
    }
    
    /**
     * Getter fuer den Score
     * @return Score der erreichte Score 
     */
    public int getScore() {
    	return score;
    }
    /**
     * �berpr�ft ob das Level zu Ende gespielt worden ist d.h alle Steine vom Feld entfernt worden sind. 
     * @return stonesCleared
     */
    public boolean stonesCleared() {
    	boolean cleared = false;
    	
    	for (int y = 0; y < stones.size(); y++) {
			for (int x = 0; x < stones.get(y).size(); x++) {
				if(stones.get(y).get(x) != null) {
					return false;
				}
			}
    	}
    	
    	return true;
    }
    /**
     * Getter fuer die Lebens anzahl 
     * @return lifes die Anzahl der Leben 
     */
    public int getLifes() {
    	return lifes;
    }
    
    /**
     * Setter fuer die leben anzahl 
     * @param lifes die neue Anzahl von leben 
     */
    public void setLifes(int lifes) {
    	this.lifes = lifes;
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
     * Diese Methode updatet den Gamescore und die Steinmatrix 
     * @param hitStoneIndex Die der getroffenen Steine in der Matrix 
     */
    public void updateStonesAndScore(Position hitStoneIndex) {
    	if(stones.get((int)hitStoneIndex.getY()).get((int)hitStoneIndex.getX())!= null){
    	stones.get((int)hitStoneIndex.getY()).set((int)hitStoneIndex.getX(), null);
    	score++;
    	}
    }


    /**
     * Diese Methode enthaelt die Threadlogik, d.h. hier wird festgelegt, was im Thread ablaeuft.
     */
    public void run() {
    	
    	 //game informiert den observer dass das Spiel gestartet wird
    	game.notifyObservers();
    		
    	//Endlosschleife fuer den Ablauf
    	while (!Abbruch) {
    		
    		//wenn ballWasStarted wahr ist wird Methoden updatePostion reactOnBoarder hitsPaddle, hitStones ausgefuhrt 
	        if (ballWasStarted) {
	        	
	        	//Der Ball wird auf seine Postition ueberprueft
	        	ball.updatePosition();
	            
	        	// Der Ball wird auf sein abprallverhalten mit der Wand ueberpruft
	            ball.reactOnBorder();
	            
	            // Der Ball wird auf das Abprallverhalten am Paddle ueberpruft
	            ball.hitsPaddle(paddle);
	            // Der Ball ueberprueft die Kollision mit der untern Wand 
	            if(ball.hitsBottom()) {
	            	stopBall();
	            	setLifes(getLifes()-1);
	            	
	            	if(getLifes() > 0) {
	            		//erstellt neues Ball Objekt 
	            		ball = new Ball(new Position((Constants.SCREEN_WIDTH-Constants.BALL_DIAMETER)/2,
	                		Constants.SCREEN_HEIGHT-(Constants.BALL_DIAMETER)-Constants.PADDLE_HEIGHT), new Vector2D(0.5, 1));
	            	}
	            	else{
		            		//beende das Level 
		            		game.getController().toStartScreen();
		            		break;
	            	
	            	}
	            
	            
	            // Wenn der Ball das Padlle beruehrt wird der Observer benachrichtigt
	            game.notifyObservers();
	            
	            // Das Paddle updatet seine Position             
	            paddle.updatePosition();
	            
	            //Das Stopverhalten des Paddles
	            paddle.reactOnBorder();
	            
	            //Abfrage ob der Ball das Paddle trifft 
	            if(ball.hitsPaddle(paddle)) {
	            	ball.reflectOnPaddle(paddle);
	            }
	            
	            //Abfrage ob der Ball die Steine trifft 
	            if(ball.hitsStone(stones)) {
	            	ball.reactOnStone(stones.get((int)ball.getHitStoneIndex().getY()).get((int)ball.getHitStoneIndex().getX())); 
	            	updateStonesAndScore(ball.getHitStoneIndex());
	            }
	            
	            //Paddle wird aufgefordert seine Postion abzufragen 
	            paddle.updatePosition();
	            //Paddle wird aufgefordert auf Wandberuerhung abzufragen
	            paddle.reactOnBorder();
	                
	        }
	        
	        //Der Thread pausiert kurz
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
    * 
    * Laedt die Datei JSOnreader und specihert ihn als String
    * 
    * @param levelnr Die Nummer X fuer die LevelX.json Datei
    * @param levelnr die Nummer des aktuellen Levels
    * 
    * @param levelnr das erreichte Level 
    */
    private void loadLevelData(int levelnr) {
    	String path = "res/Level" + levelnr + ".json";
    	JSONReader reader = new JSONReader(path);
    	
    	
    	//Erstellt ein neues Stein-Objekt nach der vorgabe des Arrays aus Json Reader . 
    	

    	//Diese for schleife sorgt fuer das zeichenen der Steine in abhangingkeit von y6
    	for(int y = 0; y < reader.getStonesListOfLists().size(); y++) {
    		//hinzufuegen der Steine zum der Array Liste 
    		stones.add(y, new ArrayList<Stone>());
    		//Diese for schleife sorgt fuer das zeichenen der Steine in abhangingkeit von x
    		for (int x = 0; x < reader.getStonesListOfLists().get(y).size(); x++) {
    			//Falls der Reader den Wert 1 erreicht stoppt die Schleife 
    			if (reader.getStonesListOfLists().get(y).get(x)==1) {
    				//Die Steine werden ins Array geladen 
    				stones.get(y).add(x, new Stone(reader.getStonesListOfLists().get(y).get(x),
    						//Die Defentiton der neuen Ballposition 
    						new Position(x*Constants.SCREEN_WIDTH/Constants.SQUARES_X+Constants.STONE_OFFSET_X,
    									 y*(int)Constants.SCREEN_HEIGHT/Constants.SQUARES_Y+Constants.STONE_OFFSET_Y)));
    			}
    			//Falls der Reader keine neuen Steine laden kann 
    			if (reader.getStonesListOfLists().get(y).get(x)==0) {
    				stones.get(y).add(x, null);
    			}
    		}
    	}
    	
    }
	  //Set the life counter
		lifes = reader.getLifeCounter();
	
	
	/**
	 * Setter for the terminated boolean
	 * Sets terminated to True (terminated)
	 */
	public void setTerminated() {
		this.terminated = true;
	}
	
	/**
	 * Setter for the terminated boolean
	 * Sets terminated to false (running)
	 */
	public void setRunning() {
		this.terminated = false;
	}

}

    
}