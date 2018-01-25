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
 * @author dmlux, modified by iSchumacher
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
		//Wichtig fuer Aufgaben
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
		drawPaddle(g2);
		drawGrid(g2);
		drawStones(g2);
		
		
		
		
		 		
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
	 *@param g2
	 *Zeichnet das Paddle und greift ueber das bekannte view obejekt auf die Methode zu
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
	 * @param g2
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
	 * @param g2
	 */
	private void drawStones(Graphics2D g2) {
		
		int stoneHeight = (int)Constants.SCREEN_HEIGHT/Constants.SQUARES_Y;
		int stoneWidth = (int) Constants.SCREEN_WIDTH/Constants.SQUARES_X;
		
			for (int y = 0; y < view.getGame().getLevel().getStones().length; y++) {
				for (int x = 0; x < view.getGame().getLevel().getStones()[y].length; x++) {
					
					if (view.getGame().getLevel().getStones()[y][x].getType()==1) {
					g2.fillRect(x*stoneWidth, y*stoneHeight, stoneWidth, stoneHeight);
					}
				}
			}
		}
	
}
