package com.sabri.jeu;

public class Chrono implements Runnable {

	private final int Pause=5;
	@Override
	public void run() {
		while(Main.scene.finDuJeu==false) {
			Main.scene.xFond--;
			Main.scene.repaint();
			try {
				Thread.sleep(this.Pause);
			} catch (InterruptedException e) {
				
			}
		}
		
	}
}
