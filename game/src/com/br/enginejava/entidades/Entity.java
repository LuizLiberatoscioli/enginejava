package com.br.enginejava.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.br.enginejava.main.Game;
import com.br.enginejava.mundo.Camera;

public class Entity {
	
	public static BufferedImage terra = Game.sprite.getSprite(0, 0, 16, 16);
	public static BufferedImage mato = Game.sprite.getSprite(16, 0, 16, 16);
	public static BufferedImage empty = Game.sprite.getSprite(16, 32, 16, 16);
	public static BufferedImage ceu = Game.ceu.getSprite(0, 0, 1471, 400);
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height,BufferedImage sprite ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}

	public int getX() {
		return (int)x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return (int)y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public void tick() {
		
	}
	public void render(Graphics g) {
		g.drawImage(sprite , this.getX()-Camera.x, this.getY()-Camera.y, null);
	}

}
