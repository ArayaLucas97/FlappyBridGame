package FlappyBridMain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Obstaculo {
	private int x, y, alto, ancho, speed;
	boolean passed, esInferior;
	private Image imagen;
	public Obstaculo(int x, int y,int alto, int ancho, int speed,boolean passed,boolean esInferior, Image imagen) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.speed = 2;
		this.passed = false;
		this.esInferior = esInferior;
		this.imagen = imagen;
	}
	
	public void dibujar(Graphics g) {
	    if (imagen != null) {
	        g.drawImage(imagen, x, y, alto, ancho, null);
	    } else {
	        g.setColor(Color.GREEN);
	        g.fillRect(x, y, alto, ancho);
	    }
	}
	
	public boolean esInferior() {
	    return esInferior;
	}

	public void actualizar() {
		x = x - speed; 
	}
	public boolean fueraDePantalla() {
		return x+ancho<0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,alto,ancho);
	}
	
	public int getX() {
		return x;
	}
	
	public int getAncho(){
		return ancho;
	}
	public void setAncho(int ancho) {
	    this.ancho = ancho;
	}	
	
	public int getY() {
	    return y;
	}

}