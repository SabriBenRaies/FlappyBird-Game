package com.sabri.objets;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tuyau {

	// Variables
	private int largeur;
	private int hauteur;
	private int x;
	private int y;
	private String strimage;
	private ImageIcon IcoTuyau;
	private Image imgTuyau;

	// Constructeur
	public Tuyau(int x, int y, String strimage) {
		this.largeur=50;
		this.hauteur=300;
		this.x=x;
		this.y=y;
		this.strimage=strimage;
		this.IcoTuyau=new ImageIcon("images/"+this.strimage);
		this.imgTuyau=this.IcoTuyau.getImage();
	}

	public int getX() {
		return x;
	}

	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public Image getImgTuyau() {
		return imgTuyau;
	}
	
	
}
