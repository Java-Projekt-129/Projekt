package break_out.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import break_out.Constants;
import net.miginfocom.swing.MigLayout;

/**
 * The field represents the board of the game. All components are on the board
 * 
 * @author dmlux, modified by iSchumacher, modifed by Jan Erik Riede 675875, Lorenzo Dal Molin 675115 
 * 
 */
public class Field extends JPanel {

	/**
	 * Automatic generated serial version UID
	 */
	private static final long serialVersionUID = 2434478741721823327L;

	/**
	 * The connected view object
	 */
	private View view;

	/**
	 * The background color
	 */
	private Color background;

	/**
	 * The constructor needs a view
	 * 
	 * @param view The view of this board
	 */
	public Field(View view) {
		super();

		this.view = view;
		this.background = new Color(177, 92, 107);

		setFocusable(true);

		// Load settings
		initialize();
	}

	/**
	 * Initializes the settings for the board
	 */
	private void initialize() {
		// creates a layout
		setLayout(new MigLayout("", "0[grow, fill]0", "0[grow, fill]0"));
	}

	/**
	 * Change the background color
	 * @param color The new color
	 */
	public void changeBackground(Color color) {
		background = color;
		repaint();
	}
	
	/**
	 * Die Methode wird zum Zeichnen / Neuzeichnen des Spielfeldes aufgerufen, dazu werden z. B. Hintergrundfarbe, 
	 * Ballfarbe usw.angegeben und die einzelnen Methoden zum Zeichnen wie drawBall(g2) aufgerufen.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		double w = Constants.SCREEN_WIDTH;
		double h = Constants.SCREEN_HEIGHT;

		// Die Abmessungen des Spielfeldes bestimmen
		setPreferredSize(new Dimension((int) w, (int) h));
		setMaximumSize(new Dimension((int) w, (int) h));
		setMinimumSize(new Dimension((int) w, (int) h));
		
		// Die Detailreichheit der Grafik festlegen
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Die Hintergrundfarbe aendern
		g2.setColor(background);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// Die Ballfarbe aendern
		g2.setColor(new Color(200, 200, 200));
		
		// Die Methode zum Ballanimieren starten 
		drawBall(g2);
		//Das Paddle zeichnen 
		drawPaddle(g2);
		//Das Grid zeichnen 
		drawGrid(g2);
		//Die Steine zeichenn 
		drawStones(g2);
		//Den Score zeichnen 
		drawScore(g2);
				
		
		
		 		
	}
	

	/**
	 * Zeichnet den Ball, greift dabei ueber das ihm bekannte view-Objekt auf das zugehoerige Game-Objekt und 
	 * darueber auf das Level-Objekt zu, um dortige Methoden zu nutzen
	 */
	private void drawBall(Graphics2D g2) {
		g2.fillOval((int) view.getGame().getLevel().getBall().getPosition().getX(),
				(int) view.getGame().getLevel().getBall().getPosition().getY(),
				(int) (Constants.BALL_DIAMETER),
				(int) (Constants.BALL_DIAMETER));
	
	}
	/**
	 *Zeichnet das Paddle und greift ueber das bekannte view objekt auf die Methode zu
	 *@param g2  fuer das zeichen des Paddles 
	 */
	
	private void drawPaddle(Graphics2D g2) {
		g2.fillRoundRect((int) view.getGame().getLevel().getPaddle().getPosition().getX(),
				(int) view.getGame().getLevel().getPaddle().getPosition().getY(),
				(int) (Constants.PADDLE_WIDTH),
				(int) (Constants.PADDLE_HEIGHT),
				20,
				20);
	}
	/**
	 * zeichnet das Raster und speichert die Werte als Array
	 * @param g2 fuer das zeichen des Rasters
	 */
	private void drawGrid(Graphics2D g2) {
		int squareHeight = (int)Constants.SCREEN_HEIGHT/Constants.SQUARES_Y;
		int squareWidth = (int) Constants.SCREEN_WIDTH/Constants.SQUARES_X;
		
		for (int i = 1; i < Constants.SQUARES_X; i++) {
			g2.drawLine(squareWidth*i, 0, squareWidth*i, (int) Constants.SCREEN_HEIGHT);	
		}
		
		for (int i = 1; i < Constants.SQUARES_Y; i++) {
			g2.drawLine(0, squareHeight*i, (int) Constants.SCREEN_WIDTH, squareHeight*i);	
		}
	}
	/**
	 * Zeichnet die Steine mit der for Schleife und ließt das Muster aus 
	 * @param g2 fuer das zeichnen der Steine 
	 */
	private void drawStones(Graphics2D g2) {

		//durchlauft die Steineliste um die Objekte zu rednern 
		for (int y = 0; y < view.getGame().getLevel().getStones().size(); y++) {
			for (int x = 0; x < view.getGame().getLevel().getStones().get(y).size(); x++) {
				
				//der Reader stoppt nur wenn der wert 1 erreicht wird nicht null 
				if (view.getGame().getLevel().getStones().get(y).get(x) != null) {
					if (view.getGame().getLevel().getStones().get(y).get(x).getType() == 1) {
						//fuellt den stein in anhengingkeit der postion und des types 
						g2.fillRect((int)view.getGame().getLevel().getStones().get(y).get(x).getPosition().getX(),
									(int)view.getGame().getLevel().getStones().get(y).get(x).getPosition().getY(),
									(int)Constants.STONE_WIDTH,
									(int)Constants.STONE_HEIGHT);
					}
				}
			}
		}
	}
	/**
	 * Zeichnet den Spielstandt mit hilfe des View objektes 
	 * @param g2 Graphics g2 rendert das Objekt 
	 */
	private void drawScore(Graphics2D g2) {
		g2.setColor(new Color(0, 255, 255));
		g2.drawString("SCORE: " + view.getGame().getLevel().getScore(), (int)Constants.SCREEN_WIDTH/2-25, (int)Constants.SCREEN_HEIGHT/2+5);
	}
	
}
