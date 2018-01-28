
package break_out.model;

import break_out.Constants;
/**
 * @author Jan Erik Riede 675875, Lorenzo Dal Molin 678115 Abgabegruppe 129
 */



public class Vector2D {
	
	private double dx;
	
	private double dy;
	
	/** Konstuktor für die Koordinaten
	 * @param dx fuer die X Koordinaten
	 * @param dy fuer die Y Koordianten
	 */
	public Vector2D(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * Konstruktor fuer die Postion des Balles 
	 * @param source fuer die Quellposition
	 * @param target fuer die Zielposition 
	 */
	public Vector2D(Position source, Position target) {
		//Berrechnung von dx
		dx = target.getX() - source.getX();
		//Berrechnung von dy
		dy = target.getY() - source.getY();
	}
	
	/**
	 * Rückgabe von dx
	 * @return dx
	 */
	public double getDx() {
		return dx;
	}
	
	/**
	 * Rückgabe von dy
	 * @return dy
	 */
	public double getDy() {
		return dy;
	}
	
	/**
	 * setter Methode für dx
	 * @param dx
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}
	
	/**
	 * 
	 * @param dy
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}
	/**
	 * Funktion für das Normalisieren des Vektors 
	 */
	public void rescale() {
		
		/**
		 * Variable length welche dem Betrag eines Vektors entspricht
		 */
		double length = Math.sqrt(Math.pow(dx,2)+Math.pow(dy, 2));
		// normierung von dx
		dx = dx/length;
		// normierung von dy
		dy = dy/length;
		
		dx = dx * Constants.BALL_SPEED;
		dy = dy * Constants.BALL_SPEED;
	}
}
