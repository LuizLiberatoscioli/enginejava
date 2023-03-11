package com.br.enginejava.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.br.enginejava.mundo.Camera;

public class Spritsheet {

	public BufferedImage spritsheet;
	
	public Spritsheet (String path) {
		try {
			spritsheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public BufferedImage getSprite(int x, int y , int width , int height) {
		return spritsheet.getSubimage(x-Camera.x,y-Camera.y,width , height);
	}
}
