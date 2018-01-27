
package break_out.model;
import break_out.Constants;
/**
 * @author Jan Erik Riede 675875 
 * @author Lorenzo Dal Molin 678115 
 * Abgabegruppe 129
 */

public class Paddle {
	private Position pos;
	
	/**
	 * Methode fuer die Position des Paddles 
	 * @param pos welcher die Postion des Paddles beschreibt
	 */
	public Paddle(Position pos) {
		this.pos = pos;
	}
	
	public Position getPosition() {
		return pos;
	}
	
	/**
	 * Deklaration der Position des Paddels
	 */
	private int direction;
	public void setDirection(int dir) {
		direction = dir;
	}
	public int getDirection() {
		return direction;
	}
	public void updatePosition() {
		if (direction == -1) {
			moveLeft();
		}
		
		else if (direction == 1) {
			moveRight();
		}
	}
	
	/**
	 * Methode fuer die Paddlebewegung 
	 */
	public void moveLeft() {
		pos.setX(pos.getX()-Constants.DX_MOVEMENT);
	}
	public void moveRight() {
		pos.setX(pos.getX()+Constants.DX_MOVEMENT);
	}
	public void reactOnBorder() {
		
    	// Falls das Paddle die Linke Wand beruert
        if (pos.getX() < 0){
        	direction = 0;
        	moveRight();
        }
        
        // Falls das Paddle die rechte Wand beruert	
        if (pos.getX() > Constants.SCREEN_WIDTH-Constants.PADDLE_WIDTH){
        	direction = 0; 
        	moveLeft();
        	}
	}  
}
