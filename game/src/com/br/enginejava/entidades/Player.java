package com.br.enginejava.entidades;

import java.awt.image.BufferedImage;

public class Player extends Entity {
	
	public boolean right , left , down, up;
	public double speed = 1.5;
	

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
	}
	
	public void tick() {
		if(right)
			x+=speed;
		if(left)
			x-=speed;
		if(down)
			y+=speed;
		if(up)
			y-=speed;
	}
	public void render() {
		
	}

}
