
package break_out.model;

import break_out.Constants;

/**
 * Diese Klasse beschreibt das verhalten der Ballbewegung 
 * 
 * @author Jan Erik Riede 675875
 * @author Lorenzo Dal Molin 678115 
 * 
 * Abgabegruppe 129
 * Erstellt in Aufgabenteil 2
 */
public class Vector2D {
	
	/**
	 * Dekleration der variable dx welche die Richtung auf der x Achse beschreibt
	 */
	private double dx;
	
	/**
	 * Dekleration der variable dy welche die Richtung auf der y Achse beschreibt
	 */
	private double dy;
	
	/** Konstuktor fuer die Koordinaten mit dx und dy
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
	 *  Methode f�r die Rueckgabe von dx
	 * @return dx der x Wert auf dem Spielfeld 
	 */
	public double getDx() {
		return dx;
	}
	
	/**
	 * Methode f�r die Rueckgabe von dy
	 * @return dy der y Wert auf dem Spielfeld 
	 */
	public double getDy() {
		return dy;
	}
	
	/**
	 * setter fuer dx
	 * @param dx der x Wert auf dem Spielfeld 
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}
	
	/**
	 * Setter fuer dy  
	 * @param dy der y Wert auf dem Spielfeld 
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}
	
	/**
	 * Funktion fuer das Normalisieren des Vektors 
	 */
	public void rescale() {
		
		 //Variable laenge welche dem Betrag eines Vektors entspricht
		double length = Math.sqrt(Math.pow(dx,2)+Math.pow(dy, 2));
		
		// normierung von dx
		dx = dx/length;
		
		// normierung von dy
		dy = dy/length;
		
		// den wert von dx mit der Ballgeschwindigkeit multipliziert
		dx = dx * Constants.BALL_SPEED;
		
		// den wert von dy mit der Ballgeschwindigkeit multipliziert
		dy = dy * Constants.BALL_SPEED;
	}
}
