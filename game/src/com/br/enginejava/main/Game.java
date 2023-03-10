package com.br.enginejava.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.br.enginejava.entidades.Entity;
import com.br.enginejava.entidades.Player;
import com.br.enginejava.graficos.Spritsheet;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	public static JFrame jframe;
	private Thread thread;
	private boolean isRunning = true;
	
	private static int WIDTH = 160;
	private static int HEIGHT = 120;
	private static int SCALE = 3;
	
	private BufferedImage fundo;
	public List<Entity> entidades;
	public static Spritsheet sprite;
	public Player player;
	
	public Game() {
		addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		initFrame();
		fundo = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		entidades = new ArrayList<Entity>();	
		sprite = new Spritsheet("/sprite1.png");
		player = new Player(0, 0, 16, 16,sprite.getSprite(32, 0, 16, 16));
		entidades.add(player);
		}
	
	public void initFrame() {
		jframe = new JFrame(" Jogo ");
		jframe.add(this);
		jframe.setResizable(false);  // nao permite mudar tamanho da tela
		jframe.pack();
		jframe.setLocationRelativeTo(null); //ficar no centro da tela
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    jframe.setVisible(true);
	}

	public static void main(String[] args) {
		
		Game game = new Game();
		game.start();

	}
	
	public synchronized void start () {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	public synchronized void stop () {
		
	}
	
	public synchronized void tick () {
		for (int i =0 ; i< entidades.size(); i++ ) {
			Entity entidade = entidades.get(i);
			entidade.tick();
		}
		
	}
	public synchronized void render() {
		
		BufferStrategy buffer = this.getBufferStrategy();
		if(buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = fundo.getGraphics();
		g.setColor(new Color(20,20,20));
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		for (int i =0 ; i< entidades.size(); i++ ) {
			Entity entidade = entidades.get(i);
			entidade.render(g);
		}
		
		g = buffer.getDrawGraphics();
		g.drawImage(fundo, 0, 0, WIDTH*SCALE, HEIGHT*SCALE , null);
		buffer.show();
	
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0f;
		double ms = 1000000000/ amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ms;
			lastTime = now;
			if(delta > 1 ) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS : " + frames);
				frames = 0 ;
				timer += 1000;
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nao sera usada
		
	}

	@Override
	public void keyPressed(KeyEvent e) {  // onde esta precionado
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if (e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		}else if (e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {	// onde nao esta precionado
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if (e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		}else if (e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
	}

}
