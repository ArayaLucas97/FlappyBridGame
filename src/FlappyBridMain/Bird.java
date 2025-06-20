package FlappyBridMain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bird {
	public int x, y, alto, ancho, speed;
	public Image image;
	public Bird() {
		this.x = 20;
		this.y = 150;
		this.alto = 20;
		this.ancho = 20;
		this.speed = 2;
		this.image = image;
	}
	//DIBUJO DEL OBJ
	public void dibujar(Graphics g) {
		int drawX = x;
		if(image != null) {
			//colocar variable donde busca la imagen (desk)
		}else {
			g.setColor(Color.MAGENTA);
			g.fillRect(drawX, y, alto, ancho);
		}
	}
	//CAER
	public void actualizar() {
		y = y + speed;	
	}
	//SUBIR (SALTOS)
	public void elevar() {
		y = y - 30;		
	}
	
	public Rectangle getBounds() {
		return new Rectangle (x,y,alto,ancho);
	}
	
	public boolean colisionaCon(ArrayList<Obstaculo> obstaculos) {
	    Rectangle birdRec = getBounds();
	    for (Obstaculo obstaculo : obstaculos) {
	        if (birdRec.intersects(obstaculo.getBounds())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean fueraPantalla1() {
		return y+alto<500;
	}
	
	public boolean fueraPantalla2() {
		return y+alto>0;
	}
	
}