package break_out.model;
/**
 * Diese Klasse representiert die Steine im Spiel
 * @author Jan Erik Riede 675875
 * @author Lorenzo Dal Molin 678115
 *Gruppe 129
 */

public class Stone {
	
	private int type;
	
	/**
	 * konsturktor fuer die Steine
	 * @param type Der Typ der Steine
	 */
	public Stone (int type) {
		this.type = type;
	}
	
	/**
	 * Deklaration fuer den Typ der Steine 
	 * @return type der Steine 
	 */
	public int getType() {
		return type;
	}
	
	
}
