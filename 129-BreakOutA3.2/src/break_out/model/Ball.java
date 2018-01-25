
package break_out.model;
import break_out.Constants;

/**
 * @author Jan Erik Riede 675875, Lorenzo Dal Molin 678115 
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
	 * @param pos
	 * @param direction
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
	 * @return hit
	 */
	public boolean hitsPaddle(Paddle p) {
		//boolean abfrage
		boolean hit = false;
		//Initialisierung und berrechnung der Ballposition
		Position ballCenter = new Position(pos.getX()+(Constants.BALL_DIAMETER/2), pos.getY()-(Constants.BALL_DIAMETER/2));
		//Initialisierung und berrechnung der Paddleposition
		Position paddleCenter = new Position(p.getPosition().getX()+(Constants.PADDLE_WIDTH/2), p.getPosition().getY()-(Constants.PADDLE_HEIGHT/2));
		
		//if 
		if (ballCenter.getY()+Constants.BALL_DIAMETER/2 > paddleCenter.getY() - Constants.PADDLE_HEIGHT/2
			&& Math.abs((paddleCenter.getX() - ballCenter.getX())) < Constants.PADDLE_WIDTH/2) {
			hit = true;
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
    	/**
    	 *  Falls der Ball gegen die Rechte Wand kommt 
    	 */
        if (pos.getX() > Constants.SCREEN_WIDTH-Constants.BALL_DIAMETER){
        	direction.setDx(-direction.getDx()); 
        }
      /**
       * Falls der Ball gegen die linke Grenze kommt 
       */
        if (pos.getX() < Constants.BALL_DIAMETER-Constants.BALL_DIAMETER){
        	direction.setDx(-direction.getDx());
        }
      /**
       * Abfrage fuer die untere Grenze 
       */
        if (pos.getY() > Constants.SCREEN_HEIGHT-Constants.BALL_DIAMETER){
        	direction.setDy(-direction.getDy());
        }
      /**
       * Abfrage fuer die obere Grenze
       */
        if (pos.getY() < Constants.BALL_DIAMETER-Constants.BALL_DIAMETER){
        	direction.setDy(-direction.getDy());
        }
        
		
	}
	/**
	 *@param paddle
	 *Abfrage ob der Ball das Paddle beruehrt.
	 *dabei wird ein neuer Vektor erzeugt in abheaningkeit von den Konstanten des Paddles 
	 *zusaetzlich wird bei dem Abprall verhalten der Vektor welcher vom Paddle ausgeht skaliert
	 *
	 */
	public void reflectOnPaddle(Paddle paddle) {
		Position ballCenter = new Position(pos.getX()+(Constants.BALL_DIAMETER/2), pos.getY()-(Constants.BALL_DIAMETER/2));
		Position paddleCenter = new Position(paddle.getPosition().getX()+(Constants.PADDLE_WIDTH/2), paddle.getPosition().getY()-(Constants.PADDLE_HEIGHT/2) + 3*Constants.PADDLE_HEIGHT);
		direction = new Vector2D(paddleCenter, ballCenter);
		direction.rescale();
	}
	
}
	