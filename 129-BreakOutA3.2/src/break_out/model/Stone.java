package break_out.model;

import java.awt.Rectangle;

import break_out.Constants;

/**
 * Diese Klasse representiert die Steine im Spiel
 * 
 * @author Jan Erik Riede 675875
 * @author Lorenzo Dal Molin 678115
 * 
 * Gruppe 129
 * Erstellt in Aufgabenteil 4
 */
public class Stone {
	
	/**
	 *  Dekleration der Variable type  welche den Typ des Steins beschreibt
	 */
	private long type;
	
	/**
	 * Dekleration von der Variable Pos die die Postion der Steine beschreibt
	 */
	private Position pos;
	
	/**
	 * Die Deklaeration der Variable hitBox die die Hitbox der Steine beschreibt
	 */
	private Rectangle hitBox;
	
	/**
	 * Der Konstrukot benoetigt ein Long um die Positon und Typ der Steine zu speichern
	 * @param type Der Typ der Steine
	 * @param pos Die Position der Steine 
	 */
	public Stone(long type, Position pos) {
		this.type = type;
		this.pos = pos;
		
		//erstellt eine neue Hitbox fuer die Steine		
		this.hitBox = new Rectangle(
				(int)pos.getX(), 
				(int)pos.getY(),
				(int)Constants.STONE_WIDTH,
				(int)Constants.STONE_HEIGHT);
				
	}
	
	/**
	 * Setter fuer den typ der Steine
	 * @param type der neue Steintyp
	 */
	public void setType(long type) {
		this.type = type;
	}
	
	/**
	 * Setter fuer die Positon der Steine
	 * @param pos die neue Steinposition
	 */
	public void setPosition(Position pos) {
		this.pos = pos;
	}

	
	/**
	 * getter fuer den Typ der Steine 
	 * @return type der typ der Steine 
	 */
	public long getType() {
		return type;
	}
	
	/**
	 * Getter fuer die Postion der Steine
	 * @return pos die Positon der Steine 
	 */
	public Position getPosition() {
		return pos;
	}
	
	/**
	 * Getter for the stones hitBox
	 * @return hitBox The stones hitBox
	 */
	public Rectangle getHitBox() {
		return hitBox;
	}
	
	
	
}
