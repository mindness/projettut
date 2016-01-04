import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;

public class Sommet extends Ellipse2D.Float{
	
	private ArrayList<Sommet> alVoisin;
	private String nom;

	public Sommet(float height, float width, float x, float y, String nom){
		super(height,width,x,y);
		this.nom = nom;
	}

	public String getNom(){ return this.nom; }

	public void ajouterVoisin(Sommet sommet){
		this.alVoisin.add(sommet);
	}
}