package com.br.enginejava.main;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Introducao {

    private JFrame frame;
    private JLabel label;

    public Introducao() {
        frame = new JFrame("Introdução");
        try {
            URL imageUrl = new URL("file:///C:/Users/Luiz/git/enginejava/res/abertura.png");
            label = new JLabel(new ImageIcon(imageUrl));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(label);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void exibirIntroducao() {
        frame.setVisible(true);
    }

    public void fecharIntroducao() {
        frame.dispose();
    }
    public boolean isVisible() {
        return frame.isVisible();
    }

}