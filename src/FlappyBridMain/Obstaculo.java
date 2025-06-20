package FlappyBridMain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstaculo {
	private int x, y, alto, ancho, speed;
	public Obstaculo(int x, int y,int alto, int ancho, int speed) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.speed = 2;
	}
	public void dibujar (Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x,y,alto,ancho);
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
}