package com.sabri.jeu;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.sabri.objets.Tuyau;
import com.sabri.personnage.FlappyBird;

public class Scene extends JPanel {

	// Variables
	private ImageIcon icoBandeFond;
	private Image imgBandFond;
	private final int LARGEUR_BANDE_FOND = 140;
	public int xFond;
	public Tuyau tuyauHaut1;
	public Tuyau tuyauBas1;
	public Tuyau tuyauHaut2;
	public Tuyau tuyauBas2;
	public Tuyau tuyauHaut3;
	public Tuyau tuyauBas3;

	private final int DISTANCE_TUYAUX = 250;
	private final int ECART_TUYAUX = 120;

	private int xTuyaux;

	private Random hasard;

	public FlappyBird flappyBird;
	
	public boolean finDuJeu;

	// Constructeur

	public Scene() {
		super();
		this.icoBandeFond = new ImageIcon("images/bandeFondEcran.png");
		this.imgBandFond = this.icoBandeFond.getImage();
		this.xFond = 0;
		
		
		this.xTuyaux = 400;
		this.tuyauHaut1 = new Tuyau(this.xTuyaux, -150, "tuyauHaut.png");
		this.tuyauBas1 = new Tuyau(this.xTuyaux, 250, "tuyauBas.png");
		this.tuyauHaut2 = new Tuyau(this.xTuyaux + this.DISTANCE_TUYAUX, -100, "tuyauHaut.png");
		this.tuyauBas2 = new Tuyau(this.xTuyaux + this.DISTANCE_TUYAUX, 300, "tuyauBas.png");
		this.tuyauHaut3 = new Tuyau(this.xTuyaux + 2 * (this.DISTANCE_TUYAUX), -80, "tuyauHaut.png");
		this.tuyauBas3 = new Tuyau(this.xTuyaux + 2 * (this.DISTANCE_TUYAUX), 320, "tuyauBas.png");

		this.flappyBird = new FlappyBird(100, 150, "images/oiseau1.png");

		this.finDuJeu=false;
		
		hasard = new Random();
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new Clavier());

		Thread ChronoEcran = new Thread(new Chrono());
		ChronoEcran.start();
	}

	private void deplacementFond(Graphics g) {
		if (this.xFond == -this.LARGEUR_BANDE_FOND) {
			this.xFond = 0;
		}
		g.drawImage(imgBandFond, this.xFond, 0, null);
		g.drawImage(imgBandFond, this.xFond + LARGEUR_BANDE_FOND, 0, null);
		g.drawImage(imgBandFond, this.xFond + LARGEUR_BANDE_FOND * 2, 0, null);
		g.drawImage(imgBandFond, this.xFond + LARGEUR_BANDE_FOND * 3, 0, null);

	}

	private void deplacementTuyau(Graphics g) {
		// tuyaux 1
		this.tuyauHaut1.setX(this.tuyauHaut1.getX() - 1);
		this.tuyauBas1.setX(this.tuyauHaut1.getX());
		if (this.tuyauHaut1.getX() == -100) {
			this.tuyauHaut1.setX(this.tuyauHaut3.getX() + this.DISTANCE_TUYAUX);
			this.tuyauBas1.setX(this.tuyauHaut1.getX());
			this.tuyauHaut1.setY(-100 - 10 * this.hasard.nextInt(18));
			this.tuyauBas1.setY(this.tuyauHaut1.getY() + this.tuyauHaut1.getHauteur() + this.ECART_TUYAUX);
		}
		g.drawImage(this.tuyauHaut1.getImgTuyau(), this.tuyauHaut1.getX(), this.tuyauHaut1.getY(), null);
		g.drawImage(this.tuyauBas1.getImgTuyau(), this.tuyauBas1.getX(), this.tuyauBas1.getY(), null);

		// tuyaux 2
		this.tuyauHaut2.setX(this.tuyauHaut2.getX() - 1);
		this.tuyauBas2.setX(this.tuyauHaut2.getX());
		if (this.tuyauHaut2.getX() == -100) {
			this.tuyauHaut2.setX(this.tuyauHaut1.getX() + this.DISTANCE_TUYAUX);
			this.tuyauBas2.setX(this.tuyauHaut2.getX());
			this.tuyauHaut2.setY(-100 - 10 * this.hasard.nextInt(18));
			this.tuyauBas2.setY(this.tuyauHaut2.getY() + this.tuyauHaut2.getHauteur() + this.ECART_TUYAUX);
		}
		g.drawImage(this.tuyauHaut2.getImgTuyau(), this.tuyauHaut2.getX(), this.tuyauHaut2.getY(), null);
		g.drawImage(this.tuyauBas2.getImgTuyau(), this.tuyauBas2.getX(), this.tuyauBas2.getY(), null);

		// tuyaux 3
		this.tuyauHaut3.setX(this.tuyauHaut3.getX() - 1);
		this.tuyauBas3.setX(this.tuyauHaut3.getX());
		if (this.tuyauHaut3.getX() == -100) {
			this.tuyauHaut3.setX(this.tuyauHaut2.getX() + this.DISTANCE_TUYAUX);
			this.tuyauBas3.setX(this.tuyauHaut3.getX());
			this.tuyauHaut3.setY(-100 - 10 * this.hasard.nextInt(18));
			this.tuyauBas3.setY(this.tuyauHaut3.getY() + this.tuyauHaut2.getHauteur() + this.ECART_TUYAUX);
		}
		g.drawImage(this.tuyauHaut3.getImgTuyau(), this.tuyauHaut3.getX(), this.tuyauHaut3.getY(), null);
		g.drawImage(this.tuyauBas3.getImgTuyau(), this.tuyauBas3.getX(), this.tuyauBas3.getY(), null);
	}

	private boolean collisionFlappy(){
        boolean finDuJeu = false;
		// proche tuyau1
		if(this.flappyBird.getX() + this.flappyBird.getLargeur() > this.tuyauBas1.getX() - 20 && 
				this.flappyBird.getX() < this.tuyauBas1.getX() + this.tuyauBas1.getLargeur() + 20){
			finDuJeu = this.flappyBird.collision(this.tuyauBas1);
			if(finDuJeu == false){finDuJeu = this.flappyBird.collision(this.tuyauHaut1);}
		}else{
			// proche tuyau2
			if(this.flappyBird.getX() + this.flappyBird.getLargeur() > this.tuyauBas2.getX() - 20 && this.flappyBird.getX() < this.tuyauBas2.getX() + this.tuyauBas2.getLargeur() + 20){
				finDuJeu = this.flappyBird.collision(this.tuyauBas2);
				if(finDuJeu == false){finDuJeu = this.flappyBird.collision(this.tuyauHaut2);}			
		    }else{
				// proche tuyau3
			    if(this.flappyBird.getX() + this.flappyBird.getLargeur() > this.tuyauBas3.getX() - 20 && this.flappyBird.getX() < this.tuyauBas3.getX() + this.tuyauBas3.getLargeur() + 20){
			    	finDuJeu = this.flappyBird.collision(this.tuyauBas3);
				    if(finDuJeu == false){finDuJeu = this.flappyBird.collision(this.tuyauHaut3);}
			}else{
				// contact avec le plafond ou le sol
				if(this.flappyBird.getY() < 0 || this.flappyBird.getY() + this.flappyBird.getHauteur() > 355){finDuJeu = true;}else{finDuJeu = false;}
		        }
		    }
	    }
		return finDuJeu;
	}
	
	public void paintComponent(Graphics g) {
		this.deplacementFond(g);
		this.deplacementTuyau(g);
		this.finDuJeu=this.collisionFlappy();
		this.flappyBird.setY(this.flappyBird.getY()+1);
		g.drawImage(this.flappyBird.getImgOiseau(), this.flappyBird.getX(), this.flappyBird.getY(), null);
		
	}
}
