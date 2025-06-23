package FlappyBridMain;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class RunGameFlappyBird extends JFrame{
	private JPanel panel;
	private Bird bird;
	private Timer timer;
	private Boolean paused;
	private ArrayList<Obstaculo> obstaculos;
	private int contadorObstaculos;
	private int intervaloObstaculo = 70;
	private int score = 0;
	private ImageIcon fondo = new ImageIcon(getClass().getResource("/media/background.png"));
 
	public RunGameFlappyBird(){
		//configuracion de la ventana
		setTitle  ("FlappyBird");
		setSize (400,500);
		setLocationRelativeTo(null); //centrar pantalla
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Pausa Boolean	
		paused = false;
		//Lista de Obstaculos
		obstaculos = new ArrayList<>();
		//Iniciar Juego
		starGame();
		
		setVisible(true);
	}
	void starGame() {
		menuEndGame();
		bird = new Bird();
		panel = new JuegoPanel();
		add(panel);//agrega el panel al JFrame
		
		
		timer = new Timer(30, e -> gameLoop());
		timer.start();
		
		keyConfigure();
		generarObstaculo();
	}
	private void gameLoop() {
		bird.actualizar();
		score++;
		
	    for (int i = 0; i < obstaculos.size(); i++) {
	        Obstaculo obstaculo = obstaculos.get(i);
	        obstaculo.actualizar();
	        
	        if (obstaculo.fueraDePantalla()) {
	            obstaculos.remove(i);
	            i--;
	        }
	    }	    
	    contadorObstaculos++;
	    
	    if(contadorObstaculos >= intervaloObstaculo) {
	    	generarObstaculo();
	    	contadorObstaculos = 0;
	    }
	    
	    if(bird.colisionaCon(obstaculos)) {
	    	timer.stop();
	    	menuEndGame();
	    }
	    
	    if(!bird.fueraPantalla1()) {
	    	timer.stop();
	    	menuEndGame();
	    }
	    
	    if(!bird.fueraPantalla2()) {
	    	timer.stop();
	    	menuEndGame();
	    }
	    
	    panel.repaint();		
	}	
	
	public void pauseGame(){
		paused = true;
		timer.stop();
	}
	
	public void resumeGame() {
		paused = false;
		timer.start();
	}
	
	private void menuEndGame() {
		JFrame menuEnd = new JFrame();
		menuEnd.setSize(200,150);
		menuEnd.setLocationRelativeTo(this);
		menuEnd.setLayout(new GridBagLayout());
		
		JButton restartButton = new JButton("Restar");
		JButton exitButton = new JButton("Exit");
		
		restartButton.addActionListener(e->{
			menuEnd.dispose();
			restartGame();
		});
		exitButton.addActionListener(e->{
			System.exit(0);
		});
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(5,5,5,5);
		menuEnd.add(restartButton,gbc);		
		gbc.gridy=1;
		menuEnd.add(exitButton, gbc);
		
		menuEnd.setVisible(true);
	}
	
	public void restartGame() {
		score = 0;
		bird = new Bird();
		obstaculos.clear();
		timer.start();
	}
	
	public void generarObstaculo() {
		int gap = 100; //separacion entre obstaculo
		int alturaSuperior = (int) (Math.random()*150)+50;
		int alturaInferior = 500 - alturaSuperior - gap;
		
		obstaculos.add(new Obstaculo(400, 0, 50, alturaSuperior, 2));
		obstaculos.add(new Obstaculo(400, 500 - alturaInferior, 50, alturaInferior, 2));
	
	}
	
	public void keyConfigure(){
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();    
        //Elevar
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "elevar");
        actionMap.put("elevar", new AbstractAction() {
        	 @Override
             public void actionPerformed(ActionEvent e) {
                   bird.elevar();        
        	 }
         });
        //pause
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "pause");
        actionMap.put("pause", new AbstractAction() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(paused) {
        			resumeGame();
        		}else {
        			pauseGame();
        		}
        	}
        }); 
	}
    // Clase interna personalizada para el dibujo
	class JuegoPanel extends JPanel{
		@Override
        protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			// Dibujar fondo
		    g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			//Dibujar Entidades
			bird.dibujar(g);
			for(Obstaculo obstaculo : obstaculos) {
				obstaculo.dibujar(g);
			}
			//Puntaje
			g.setColor(Color.BLACK);
			g.drawString("Score: "+score,10,20);
	
		}
	}
	public static void main (String[]args) {
		new Main();
	}
}