package com.br.enginejava.main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TelaDeMenu extends JFrame {
	  private JPanel panel;
	    private JButton btnNovoJogo;
	    private JButton btnCreditos;
	    private JButton btnSair;

	    public TelaDeMenu() {
	        panel = new JPanel();
	        btnNovoJogo = new JButton("Novo Jogo");
	        btnCreditos = new JButton("Créditos");
	        btnSair = new JButton("Sair");

	        panel.add(btnNovoJogo);
	        panel.add(btnCreditos);
	        panel.add(btnSair);

	        add(panel);

	        setTitle("Segunda Tela");
	        setSize(300, 200);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setResizable(false);
	    }

	    
}
