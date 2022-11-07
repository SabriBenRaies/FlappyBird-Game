package com.sabri.jeu;

import javax.swing.JFrame;

public class Fenetre extends JFrame {

	public Fenetre() {
		super();
		this.proprietesFenetre();
		this.setVisible(true);
	}

	public Fenetre(String str) {
		super(str);
		this.proprietesFenetre();
		this.setVisible(true);
	}

	private void proprietesFenetre() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(300, 425);
		this.setSize(1000, 1275);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setContentPane(Main.scene);
	}

}
