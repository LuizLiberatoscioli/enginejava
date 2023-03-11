package com.br.enginejava.mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.br.enginejava.main.Game;

public class Mundo {
	
	public static int WIDTH , HEIGHT;
	public Tile[] tiles;
	

	public Mundo(String path) {
		try {
			BufferedImage level = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[level.getWidth() * level.getHeight()];
			tiles = new Tile[level.getWidth() * level.getHeight()];
			WIDTH = level.getWidth();
			HEIGHT = level.getHeight();
			level.getRGB(0, 0, level.getWidth(), level.getHeight(), pixels, 0, level.getWidth());
			
			/*
			 * for (int i = 0 ; i < pixels.length ; i++) { if (pixels [i] == 0xFFdf7126) {
			 * // verificacao de cor System.out.println("cor laranja"); }
			 * 
			 * }
			 */
			
			for (int x = 0 ; x < level.getWidth(); x++) {    // fazendo verificacoes
				for(int y = 0 ; y < level.getHeight(); y++) {
					int pixelAtual = pixels[x + (y * level.getWidth())];
					tiles [x + (y* WIDTH)] = new Empty(x* 16, y*16, Tile.empty);
					if(pixelAtual == 0xFF3f3f74) {
						//player
						Game.player.setX(x*16);
						Game.player.setY(y*16);
					}else if (pixelAtual == 0xFF663931) {
						//terra
						tiles [x + (y* WIDTH)] = new Terra(x* 16, y*16, Tile.terra);
					}else if (pixelAtual == 0xFF4b692f) {
						//mato
						tiles [x + (y* WIDTH)] = new Terra(x* 16, y*16, Tile.mato);
					}else if (pixelAtual == 0xFFffffff) {
						//empty
						tiles [x + (y* WIDTH)] = new Empty(x* 16, y*16, Tile.empty);
					}

				}
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	public void render(Graphics g) {
		int xi = Camera.x/16;				// tamanho q deve ser renderizado xi= x inicial xf = x final
		int yi = Camera.y/16;
		int xf = xi + (Game.WIDTH/16);
		int yf = yi + (Game.HEIGHT/16);
		
		for (int x = xi ; x < xf ; x++ ) {
			for (int y = yi ; y < yf ; y++ ) {
				if (x < 0 || y <0 || x >= WIDTH || y >= HEIGHT) 
					continue;
				Tile tile = tiles [x + (y*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
