package com.br.enginejava.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.br.enginejava.main.Game;
import com.br.enginejava.mundo.Camera;
import com.br.enginejava.mundo.Mundo;

public class Player extends Entity {
	
	public boolean right , left , down, up;
	public double speed = 1.5;
	public static double life = 100 , maxlife= 100;
	
	public int direita = 1 , esquerda = 0 ;
	public int direcaoAtual = direita;
	
	public int movimentacao = 0;
	public int frames = 0 , maxFrames =5 ,index =0 , maxIndex = 3 ;
	
	public BufferedImage[] playerRight;
	public BufferedImage[] playerLeft;
	

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		playerRight = new BufferedImage[4];
		playerLeft = new BufferedImage[4];
		
		for(int i =0; i<4 ; i++) {
			playerRight[i] = Game.sprite.getSprite(32 + (i*16), 0, 16, 16);
			
		}
		for(int i =0; i<4 ; i++) {
			playerLeft[i] = Game.sprite.getSprite(80 - (i*16), 16, 16, 16);
		}
	}
	
	public void tick() {
		movimentacao = 0;
		if(right) {
			x+=speed;
			movimentacao = 1;
			direcaoAtual = direita;
		}
			
		if(left) {
			x-=speed;
		    movimentacao = 1;
		    direcaoAtual = esquerda;
		}
			
		
		  if(down) y+=speed; if(up) y-=speed;
		 
		
		if(movimentacao == 1) {
			frames++;
			if (frames == maxFrames) {
				index++;   //roda mais um index da animacao
				frames = 0;
				if(index > maxIndex) 
					index =0;
				
			}
			
		}
		
		Camera.x = Camera.Clamp(this.getX() - (Game.WIDTH /2), 0, Mundo.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.Clamp(this.getY() - (Game.HEIGHT /2), 0, Mundo.HEIGHT*20 - Game.WIDTH);
		
	}
	public void render(Graphics g) {
		if(direcaoAtual == direita && movimentacao == 1 ) {
			g.drawImage(playerRight[index],this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
		if(direcaoAtual == direita && movimentacao == 0 ) {
			g.drawImage(playerRight[0],this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
		if(direcaoAtual == esquerda && movimentacao == 1 ) {
			g.drawImage(playerLeft[index],this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
		if(direcaoAtual == esquerda && movimentacao == 0 ) {
			g.drawImage(playerLeft[0],this.getX()-Camera.x,this.getY()-Camera.y,null);
		}
	}

}
