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

import com.br.enginejava.entidades.Cenoura;
import com.br.enginejava.entidades.Ceu;
import com.br.enginejava.entidades.Entity;
import com.br.enginejava.entidades.Grama;
import com.br.enginejava.entidades.Inimigo;
import com.br.enginejava.entidades.Player;
import com.br.enginejava.graficos.Spritsheet;
import com.br.enginejava.mundo.Mundo;

public class Game extends Canvas implements Runnable, KeyListener {
	
	private Introducao introducao;

	private static final long serialVersionUID = 1L;
	public static JFrame jframe;
	private Thread thread;
	private boolean isRunning = true;

	public static int WIDTH = 240;
	public static int HEIGHT = 160;
	public static int SCALE = 4;

	private BufferedImage fundo;
	public static List<Entity> entidades;
	public static Spritsheet sprite;
	public static Mundo mundo;
	public static Player player;

	public static List<Ceu> ceuvetor;
	public static Spritsheet ceu;

	// TODO para animar fazer vetor deles
	public static List<Cenoura> cenoura;
	public static List<Inimigo> inimigo;
	public static List<Grama> grama;

	public UserInterface ui;
	
	public int level = 1, levelmaximo = 2 ;
	

	public Game() {
		
		introducao = new Introducao();
		
		addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		ui = new UserInterface();
		fundo = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entidades = new ArrayList<Entity>();
		sprite = new Spritsheet("/sprite1.png");
		ceuvetor = new ArrayList<Ceu>();
		cenoura = new ArrayList<Cenoura>();
		inimigo = new ArrayList<Inimigo>();
		grama = new ArrayList<Grama>();
		ceu = new Spritsheet("/ceusprite.png");

		player = new Player(0, 0, 16, 16, sprite.getSprite(32, 0, 16, 16));
		entidades.add(player);
		mundo = new Mundo("/level1.png");
	}

	public void initFrame() {
		jframe = new JFrame(" Jogo ");
		jframe.add(this);
		jframe.setResizable(false); // nao permite mudar tamanho da tela
		jframe.pack();
		jframe.setLocationRelativeTo(null); // ficar no centro da tela
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.exibirIntroducao(); // Chama o método para exibir a introdução
		
		try {
		    Thread.sleep(3000); // Aguarda 2 segundos (2000 milissegundos)
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		TelaDeMenu telaDeMenu = new TelaDeMenu();
	        telaDeMenu.setVisible(true);
	        
	        
		game.start();

	}
	 public void exibirIntroducao() {
	        introducao.exibirIntroducao();
	    }
	  public void fecharIntroducao() {
	        introducao.fecharIntroducao();
	    }

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {

	}

	public synchronized void tick() {
			//se matar todos enimigos passa de level
		if(inimigo.size() == 0) {
			level++;
			// terminar o mundo volta do zero
			if(level > levelmaximo) {
				level = 1;
			}
			String passar = "level"+level+".png";
			Mundo.newLevel(passar);
		}
		
		for (int i = 0; i < entidades.size(); i++) {
			Entity entidade = entidades.get(i);
			entidade.tick();
		}

		for (int i = 0; i < ceuvetor.size(); i++) {
			Ceu entidade = ceuvetor.get(i);
			entidade.tick();
		}

		for (int i = 0; i < cenoura.size(); i++) {
			Cenoura entidade = cenoura.get(i);
			entidade.tick();
		}

		for (int i = 0; i < inimigo.size(); i++) {
			Inimigo entidade = inimigo.get(i);
			entidade.tick();
		}

		for (int i = 0; i < grama.size(); i++) {
			Grama entidade = grama.get(i);
			entidade.tick();
		}

	}

	public synchronized void render() {
		// carregar o mundo preto
		BufferStrategy buffer = this.getBufferStrategy();
		if (buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = fundo.getGraphics();
		g.setColor(new Color(20, 20, 20));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// carregar o mundo
		mundo.render(g);

		// carregar o ceu
		for (int i = 0; i < ceuvetor.size(); i++) {
			Ceu entidade = ceuvetor.get(i);
			entidade.render(g);
		}

		// carregar os tales e player
		for (int i = 0; i < entidades.size(); i++) {
			Entity entidade = entidades.get(i);
			entidade.render(g);
		}

		for (int i = 0; i < cenoura.size(); i++) {
			Cenoura entidade = cenoura.get(i);
			entidade.render(g);
		}

		for (int i = 0; i < inimigo.size(); i++) {
			Inimigo entidade = inimigo.get(i);
			entidade.render(g);
		}

		for (int i = 0; i < grama.size(); i++) {
			Grama entidade = grama.get(i);
			entidade.render(g);
		}

		ui.render(g);

		g = buffer.getDrawGraphics();
		g.drawImage(fundo, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		buffer.show();

	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0f;
		double ms = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();

		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ms;
			lastTime = now;
			if (delta > 1) {
				tick();
				render();
				frames++;
				delta--;
				
				if (introducao.isVisible()) {
                    fecharIntroducao(); // Fecha a introdução assim que o jogo começa a renderizar
                }
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS : " + frames);
				frames = 0;
				timer += 1000;
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nao sera usada

	}

	@Override
	public void keyPressed(KeyEvent e) { // onde esta precionado
		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) { // onde nao esta precionado
		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}
	}

}
