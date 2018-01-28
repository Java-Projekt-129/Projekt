package break_out.model;
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
	 * Dekleration der Variable dircetion vom Typ Vektor2D
	 */
	private Vector2D direction;
	
	/**
	 * Kostruktoren f�r die Position und Richtung des Balls
	 * sowie normierung seines Richtungsvektors
	 * @param pos Postion des Balles  
	 * @param direction Richtung des Balles 
	 */
	public Ball(Position pos, Vector2D direction) {
		this.pos = pos;
		this.direction = direction;
		this.direction.rescale();
	}
	
	/**
	 * Rueckgabe der Ballposition
	 * @return pos 
	 */
	public Position getPosition() {
		return pos;
	}
	
	/**
	 * Rueckgabe des Richtungsvektors des Balls 
	 * @return direction
	 */
	public Vector2D getDirection() {
		return direction;
	}
	
	/**
	 * Boolean abfrage ob der Ball das Paddle beruerht  
	 * @param p in abheanigkeit von dem Ball welcher das Paddle beruehrt 
	 * @return hit rueckgabe ob der Ball das Padlle getroffen hat oder nicht 
	 */
	public boolean hitsPaddle(Paddle p) {
		//boolean abfrage f�r paddle ber�hrung
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
	 * Methode zum ermittlen der neuen Ballposition 
	 */
	public void updatePosition() {
		pos.setX(pos.getX()+direction.getDx());
        pos.setY(pos.getY()+direction.getDy());  
	}
	
	/**
	 * Methode zum verhalten an der Wandbruehrung
	 */
	public void reactOnBorder() {
		
		// hier das Abprallverhalten des Balls an den vier Waenden implementieren
    	
		
		// Falls der Ball gegen die Rechte Wand kommt prallt er nach links
        if (pos.getX() > Constants.SCREEN_WIDTH-Constants.BALL_DIAMETER){
        	direction.setDx(-direction.getDx()); 
        }
    
        // Falls der Ball gegen die linke Grenze kommt prallt er nach rechts ab
        if (pos.getX() < Constants.BALL_DIAMETER-Constants.BALL_DIAMETER){
        	direction.setDx(-direction.getDx());
        }
      
       // Falls der Ball gegen die untere Grenze kommt prallt er nach oben ab
        if (pos.getY() > Constants.SCREEN_HEIGHT-Constants.BALL_DIAMETER){
        	direction.setDy(-direction.getDy());
        }
      
        // Falls der Ball gegen die obere Grenze kommt prallt er nach unten ab
        if (pos.getY() < Constants.BALL_DIAMETER-Constants.BALL_DIAMETER){
        	direction.setDy(-direction.getDy());
        }
        
		
	}
	
	/**
	 * Abfrage ob der Ball das Paddle beruehrt.
	 * dabei wird ein neuer Vektor erzeugt in abheaningkeit von den Konstanten des Paddles 
	 * zusaetzlich wird bei dem Abprall verhalten der Vektor welcher vom Paddle ausgeht skaliert
	 * 
	 * @param paddle beschreibt die positon des Paddles 
	 */
	public void reflectOnPaddle(Paddle paddle) {
		Position ballCenter = new Position(pos.getX()+(Constants.BALL_DIAMETER/2), pos.getY()-(Constants.BALL_DIAMETER/2));
		Position paddleCenter = new Position(paddle.getPosition().getX()+(Constants.PADDLE_WIDTH/2), paddle.getPosition().getY()-(Constants.PADDLE_HEIGHT/2) + 3*Constants.PADDLE_HEIGHT);
		direction = new Vector2D(paddleCenter, ballCenter);
		direction.rescale();
	}
	
}
	