package com.br.enginejava.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.br.enginejava.main.Game;
import com.br.enginejava.mundo.Camera;

public class Inimigo extends Entity{
	
	public double speed = 0.5;
	public int movimentacao = 1;
	public int frames = 0 , maxFrames =7 ,index =0 , maxIndex = 1 ; // animacao
	public int maskx= 0 , masky = 0, maskw = 16 , maskh = 16;
	public int life = 5, maxlife = 9;
	
	public BufferedImage[] inimigo;
	

	public Inimigo(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		inimigo = new BufferedImage[2]; // sempre 1 mais q o maxindex
		
		for(int i =0; i<2 ; i++) {  //frames de animacao
			inimigo[i] = Game.sprite.getSprite(112 + (i*16), 0, 16, 16);
			
		}
		
	}
	public void tick() {
		
		if(life <= 0 ) {
			
		}
		
		for (int i = 0 ; i < Game.inimigo.size(); i++) {
			System.out.println("Vida: "+ life+ "indice: "+i);
			
		}
		
		if(!colisao((int)x, (int)(y+1)))  {
			y+=2;
		}
		
		if (Game.player.getX() < this.getX()  && !colisao((int)(x-speed), this.getY())) {
			x-=speed;
		}
		
		if (Game.player.getX() < this.getX() && !colisao((int)(x+speed), this.getY())) {
			x+=speed;
		}
		
		if(movimentacao == 1) {
			frames++;
			if (frames == maxFrames) {
				index++;   //roda mais um index da animacao
				frames = 0;
				if(index > maxIndex) 
					index =0;
				
			}
			
		}
	}
	public void render(Graphics g) {
		g.drawImage(inimigo[index], this.getX()-Camera.x,this.getY()-Camera.y, null);
	}
	
	public boolean colisao(int nextx, int nexty) {
		Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
		for (int i = 0; i < Game.entidades.size(); i++) {
			Entity entidade = Game.entidades.get(i);
			if (entidade instanceof Solido) {
				Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, maskw, maskh);
				if (player.intersects(solido)) {
					return true;
				}
			}
		}

		return false;

	}

}
