package break_out.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import break_out.model.Game;
import break_out.view.Field;
import break_out.view.StartScreen;
import break_out.view.View;

/**
 * Der Controller bearbeitet die Eingaben der Tasten und verwaltet die Updates am Model
 * 
 * @author dmlux, modified by I. Schumacher, modified by Jan Erik Riede 675875, modified by Lorenzo Dal Molin 678115
 * 
 */
public class Controller implements ActionListener, KeyListener {

    /**
     * Das Spiel als Model ist mit dem Controller verbunden 
     */
    private Game game;

    /**
     * Das View ist verbunden mit dem Spiel
     */
    private View view;

    
    /**
     * Das Konsturktor erwartet View.
     * 
     * @param view welches zum Contoller verbunden ist
     */
    public Controller(View view) {
        this.view = view;

        // Assigning the listeners
        assignActionListener();
        assignKeyListener();
    }

    /**
     * Der Controller ließt alle Bouttons aus view 
     * dabei ist dieser Contoller ein Actionlistner jedemals wenn ein
     * button gedrückt wird bekommt dieser Contoller ein Action Event
     */
    private void assignActionListener() {
        // Get the start screen to add this controller as action
        // listener to the buttons.
        view.getStartScreen().addActionListenerToStartButton(this);
        view.getStartScreen().addActionListenerToQuitButton(this);
    }
    
    /**
     * Mit dieser Methode fügt sich der Contoller als Keylistner hinzu
     * jedemals wenn ein button gedrückt wird bekommt dieser Contoller ein KeyEvent.
     */
    private void assignKeyListener() {
        // Get the field to add this controller as KeyListener
        view.getField().addKeyListener(this);
    }

    /**
     * Jedesmal wenn der Nutzer einen Button anklickt, erhaelt der Actionlistener (dieser Controller) 
     * ein Actionevent und startet diese Methode
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Der Start-Screen wird fuer den Zugriff auf seine Methoden vom bekannten View-Objekt besorgt.
        StartScreen startScreen = view.getStartScreen();
        
        // Der Startbutton wurde angeklickt
        if (startScreen.getStartButton().equals(e.getSource())) {
            // Der Name des Spielers wird von der Eingabe geholt
            String playersName = startScreen.getPlayersName();
            playersName = playersName.trim();
            if (playersName.length() < 1) {
                // Ist der Name leer, ist er ungueltig und eine Fehlermeldung wird angezeigt
                startScreen.showError("Der Name ist ungÃ¼ltig");
            } else {    
            	// Neues Game-Objekt erzeugen und 
    	        game = new Game(this);
    	        // dem View-Objekt bekanntgeben
    	        view.setGame(game);
            }
        }

        // Der Spielendebutton wurde angeklickt
        else if (startScreen.getQuitButton().equals(e.getSource())) {
            System.exit(0);
        }
    }
 
    /**
     * Methode des KeyListeners
     */
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    /**
     * Methode des KeyListeners
     */
    @Override
    public void keyPressed(KeyEvent e) {
    	
    	// Startet oder Stoppt den Ball wenn Leertaste gedrückt wird
    	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        	if (game.getLevel().ballWasStarted()) {
        		game.getLevel().stopBall();
        	}
        	
        	else if (!game.getLevel().ballWasStarted()) {
        		game.getLevel().startBall();
        	}
        }
    	
    	// Setzt die Paddle bewegung nach Links wenn die Linkepfeiltaste gedrueckt wird
    	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    		game.getLevel().getPaddle().setDirection(-1);
    	}
    	
    	// Setzt die Paddle bewegung nach Rechts wenn die Rechtepfeiltaste gedrueckt wird
    	if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    		game.getLevel().getPaddle().setDirection(1);
    	}
    	
    }

    /**
     * Methode des KeyListeners
     */
    @Override
    public void keyReleased(KeyEvent e) {
    	
    	// Stoppt die Paddle Bewegunng wenn die Tasten losgelassen werden
    	if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
    		game.getLevel().getPaddle().setDirection(0);
    	}
    }

    
    /**
     * Mit dieser Methode erfolgt das Umschalten vom Spielfeld zum StartScreen
     */
    public void toStartScreen() {
    	view.showScreen(StartScreen.class.getName());
    	view.getStartScreen().requestFocusInWindow();
    }
    
    /**
     * Mit dieser Methode erfolgt das Umschalten vom StartScreen zum Spielfeld
     */
    public void toPlayground() {
    	view.showScreen(Field.class.getName());
    	view.getField().requestFocusInWindow();
    }
   
}
