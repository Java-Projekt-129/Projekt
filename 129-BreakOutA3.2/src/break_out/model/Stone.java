package break_out.model;
/**
 * @author Jan Erik Riede 675875, Lorenzo Dal Molin 
 *
 */
/**
 * Diese Klasse representiert die Steine im Spiel
 * 
 */
public class Stone {
	private int type;
	/**
	 * Konstruktor fuer die Steine
	 * @param type
	 */
	public Stone (int type) {
		this.type = type;
	}
	/**
	 * Konsturktor fuer den Typ der Steine 
	 * @return
	 */
	public int getType() {
		return type;
	}
	
	
}
