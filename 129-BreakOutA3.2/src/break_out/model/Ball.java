package break_out.model;
import java.awt.Rectangle;
import java.util.List;

import break_out.Constants;

/**
 * Diese Klasse beschreibt das verhalten des Balles 
 * @author Jan Erik Riede 675875, Lorenzo Dal Molin 678115 
 * Gruppe 129
 */

public class Ball {
	
	/**
	 * Dekleration der Variable pos vom Typ position
	 */
	private Position pos;
	/**
	 * Der Index der getroffenen Steine in der Matrix 
	 */
	private Position hitStoneIndex;
	
	/**
	 * Dekleration der Variable dircetion vom Typ Vektor2D
	 */
	private Vector2D direction;
	/**
	 * Die Hitbox des Balles 
	 */
	private Rectangle hitBox;
	
	/**
	 * Kostruktoren für die Position und Richtung des Balls
	 * sowie normierung seines Richtungsvektors
	 * Kontruktor fuer die Hitbox des Balles 
	 * @param pos Postion des Balles  
	 * @param direction Richtung des Balles 
	 * @param hitStoneIndex Beschreibt ob der Ball getroffen hat 
	 */
	public Ball(Position pos, Vector2D direction) {
		this.pos = pos;
		this.direction = direction;
		this.direction.rescale();
		// Erzeugt einen neuen Winkel fuer den Ball 
		this.hitBox = new Rectangle(
				(int)pos.getX(),
				(int)pos.getY(),
				(int)Constants.BALL_DIAMETER,
				(int)Constants.BALL_DIAMETER);
		//Erzeugt die neue Steinposition
		this.hitStoneIndex = new Position(0,0);
	}
	
	/**
	 * Rueckgabe der Ballposition
	 * @return pos der wert der Ball Position 
	 */
	public Position getPosition() {
		return pos;
	}
	/**
	 * Getter fuer die getroffenen Steine in der Matrix
	 * @return hitStoneIndex ob der Ball getroffen wurde 
	 */
	public Position getHitStoneIndex() {
		return hitStoneIndex;
	}
	
	/**
	 * Rueckgabe des Richtungsvektors des Balls 
	 * @return direction fuer den Richtungsvektor des Balles 
	 */
	public Vector2D getDirection() {
		return direction;
	}
	/**
	 * Getter fuer die Hitbox des Balles 
	 * @return hitbox Die Hitbox des Balles 
	 */
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	
	/**
	 * Boolean abfrage ob der Ball das Paddle beruerht  
	 * @param p in abheanigkeit von dem Ball welcher das Paddle beruehrt 
	 * @return hit rueckgabe ob der Ball das Padlle getroffen hat oder nicht 
	 */
	public boolean hitsPaddle(Paddle p) {
		//boolean abfrage für paddle berührung
		boolean hit = false;
		//Initialisierung und berrechnung der Ballposition
		Position ballCenter = new Position(pos.getX()+(Constants.BALL_DIAMETER/2), pos.getY()-(Constants.BALL_DIAMETER/2));
		//Initialisierung und berrechnung der Paddleposition
		Position paddleCenter = new Position(p.getPosition().getX()+(Constants.PADDLE_WIDTH/2), p.getPosition().getY()-(Constants.PADDLE_HEIGHT/2));
		
		//falls der mittelpunkt des Balles das Paddle beruerht 
		if (ballCenter.getY()+Constants.BALL_DIAMETER/2 > paddleCenter.getY() - Constants.PADDLE_HEIGHT/2
			&& Math.abs((paddleCenter.getX() - ballCenter.getX())) < Constants.PADDLE_WIDTH/2) {
			//falls hit wahr ist prallt der Ball  ab
			hit = true;
			//der Ball prallt vom Paddle ab  	  
			 reflectOnPaddle(p);
		}
		return hit;
	}
	/**
	 * Boolean abfrage ob der Ball die Steine beruhert 
	 * @param stones Variable  der Steine
	 * @return hit ob der Ball getroffen hat 
	 */
	public boolean hitsStone(List<List<Stone>> stones) {
		boolean hit = false;
		for (int y = 0; y < stones.size(); y++) {
			for (int x = 0; x < stones.get(y).size(); x++) {
				if(stones.get(y).get(x) != null) {
					if(hitBox.intersects(stones.get(y).get(x).getHitBox())) {
						hit = true;
						hitStoneIndex.setX(x);
						hitStoneIndex.setY(y);
						}
					}
				}
			}
		return hit;
	}
	/**
	 * Methode zum ermittlen der neuen Ballposition so wie der Hitbox
	 */
	public void updatePosition() {
		pos.setX(pos.getX()+direction.getDx());
        pos.setY(pos.getY()+direction.getDy()); 
        hitBox.setLocation((int)pos.getX(), (int)pos.getY());
	}
	
	/**
	 * Methode zum verhalten an der Wandbruehrung
	 */
	public void reactOnBorder() {
		
		// hier das Abprallverhalten des Balls an den vier Waenden implementieren
    	
		
		// Falls der Ball gegen die Rechte Wand kommt prallt er nach links
        if (pos.getX() > Constants.SCREEN_WIDTH-Constants.BALL_DIAMETER){
        	direction.setDx(-direction.getDx());
        	pos.setX(Constants.SCREEN_WIDTH-Constants.BALL_DIAMETER);
                
        }
    
        // Falls der Ball gegen die linke Grenze kommt prallt er nach rechts ab
        if (pos.getX() < Constants.BALL_DIAMETER-Constants.BALL_DIAMETER){
        	direction.setDx(-direction.getDx());
        	pos.setX(Constants.BALL_DIAMETER-Constants.BALL_DIAMETER);
        }
      
       // Falls der Ball gegen die untere Grenze kommt prallt er nach oben ab
        if (pos.getY() > Constants.SCREEN_HEIGHT-Constants.BALL_DIAMETER){
        	direction.setDy(-direction.getDy());
        	pos.setY(Constants.SCREEN_HEIGHT-Constants.BALL_DIAMETER);
        }
      
        // Falls der Ball gegen die obere Grenze kommt prallt er nach unten ab
        if (pos.getY() < Constants.BALL_DIAMETER-Constants.BALL_DIAMETER){
        	direction.setDy(-direction.getDy());
        	pos.setY(Constants.BALL_DIAMETER-Constants.BALL_DIAMETER);
        }
        
		
	}
	
	/**
	 * Abfrage ob der Ball das Paddle beruehrt.
	 * dabei wird ein neuer Vektor erzeugt in abheaningkeit von den Konstanten des Paddles 
	 * zusaetzlich wird bei dem Abprall verhalten der Vektor welcher vom Paddle ausgeht skaliert
	 * @param paddle beschreibt die positon des Paddles 
	 */
	public void reflectOnPaddle(Paddle paddle) {
		Position ballCenter = new Position(pos.getX()+(Constants.BALL_DIAMETER/2), pos.getY()+(Constants.BALL_DIAMETER/2));
		Position paddleCenter = new Position(paddle.getPosition().getX()+(Constants.PADDLE_WIDTH/2), paddle.getPosition().getY()+(Constants.PADDLE_HEIGHT/2) + 3*Constants.PADDLE_HEIGHT);
		direction = new Vector2D(paddleCenter, ballCenter);
		direction.rescale();
	}
	/**
	 * Void welches Abfragt wie sich der Ball bei beruerung mit den Steinen verhalten soll 
	 * @param hitStone Der Stein an dem der Ball abprallt 
	 */
	public void reactOnStone(Stone hitStone) {
		// falls der Ball die linke Seite der Steine trifft 
		if (pos.getX() + Constants.BALL_DIAMETER/2 < hitStone.getPosition().getX()) {
			direction.setDx(-direction.getDx());
		}
		// falls der Ball die rechte Seite der Steine trifft 
		else if (pos.getX() + Constants.BALL_DIAMETER/2 > hitStone.getPosition().getX()+Constants.STONE_WIDTH) {
			direction.setDx(-direction.getDx());
		}
		// falls der Ball die obere Seite der Steine trifft 
		else if (pos.getY() + Constants.BALL_DIAMETER/2 < hitStone.getPosition().getY()) {
			direction.setDy(-direction.getDy());
		}
		// falls der Ball die untere Seite der Steine trifft 
		else if (pos.getY() + Constants.BALL_DIAMETER/2 > hitStone.getPosition().getY()+Constants.STONE_HEIGHT) {
			direction.setDy(-direction.getDy());
		}
	}
}
	