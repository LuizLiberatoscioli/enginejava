package com.br.enginejava.mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.br.enginejava.main.Game;

public class Tile {
	
	public static BufferedImage terra = Game.sprite.getSprite(0, 0, 16, 16);
	public static BufferedImage mato = Game.sprite.getSprite(16, 0, 16, 16);
	public static BufferedImage empty = Game.sprite.getSprite(16, 32, 16, 16);
	
	public int x,y ;
	public BufferedImage sprite;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,x-Camera.x,y-Camera.y, null);
	}

}
