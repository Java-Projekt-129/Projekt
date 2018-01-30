
package break_out.model;
import break_out.Constants;

/**
 * Diese Klasse beschreibt das verhalten des Paddles 
 * @author Jan Erik Riede 675875 
 * @author Lorenzo Dal Molin 678115 
 * Abgabegruppe 129
 */
public class Paddle {
	
	private Position pos;
	
	/**
	 * Methode der Position des Paddles 
	 * @param pos welcher die Postion des Paddles beschreibt
	 */
	public Paddle(Position pos) {
		this.pos = pos;
	}
	
	/**
	 * Methode zur Rueckgabe der Position des Passels
	 * @return pos die Position des Paddles
	 */
	public Position getPosition() {
		return pos;
	}
	
	
	private int direction;
	
	/**
	 * Methode die die Richtung des Paddles erzeugt
	 * @param dir beschreibt die richtung des Paddles
	 */
	public void setDirection(int dir) {
		direction = dir;
	}
	
	/**
	 * Methode zur Rueckgabe der Richtung des Paddles
	 * @return direction die richtung des Paddles 
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Methode die die Position des Paddles
	 * nach links oder Rechts aendert und die Position updated
	 */
	public void updatePosition() {
		//if else abfrage fuer richtung wenn dircetion = -1 bewegung nach links
		if (direction == -1) {
			moveLeft();
		}
		
		//else if wenn direction = 1 bewegung nach rechts
		else if (direction == 1) {
			moveRight();
		}
	}
	
	/**
	 * Methode fuer die Paddlebewegung nach links
	 */
	public void moveLeft() {
		pos.setX(pos.getX()-Constants.DX_MOVEMENT);
	}
	
	/**
	 * Methode fuer die Paddlebewegung nach rechts
	 */
	public void moveRight() {
		pos.setX(pos.getX()+Constants.DX_MOVEMENT);
	}
	
	/**
	 * Methode fuer das Verhalten des Paddles bei Wandberuehrung
	 */
	public void reactOnBorder() {
		
    	// Falls das Paddle die Linke Wand beruert wird bewegung nach links gestopt
        if (pos.getX() < 0){
        	direction = 0;
        	moveRight();
        }
        
        // Falls das Paddle die rechte Wand beruert wird bewegung nach rechts gestopt
        if (pos.getX() > Constants.SCREEN_WIDTH-Constants.PADDLE_WIDTH){
        	direction = 0; 
        	moveLeft();
        	}
	}  
}
