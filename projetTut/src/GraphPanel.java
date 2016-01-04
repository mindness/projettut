import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;
public class GraphPanel extends JPanel {
// test panda
	//CA MARCHE TRES BIEN OMG PANDA ROUX
     private int radius = 50;
	 private int nbSommet = 0;

     private ArrayList<Sommet> alSommet;

     private Ellipse2D dragged;
     private Point offset;
     
     private ListSommet listSommet;

     public GraphPanel(ListSommet listSommet) {
    	 this.listSommet = listSommet;
    	 
		 alSommet = new ArrayList<Sommet>();

		 addMouseListener(new MouseAdapter() {

			 public void mousePressed(MouseEvent e) {

				 for (Sommet sommet : alSommet) {

					 if (sommet.contains(e.getPoint())) {
						 dragged = sommet;
						 // Adjust for the different between the top/left corner of the
						 // sommet and the point it was clicked...
						 offset = new Point(sommet.getBounds().x - e.getX(), sommet.getBounds().y - e.getY());
						 // Highlight the clicked sommet
						 repaint();
						 break;
					 }
				 }
			 }	


			 public void mouseReleased(MouseEvent e) {
				 // Erase the "click" highlight
				 if (dragged != null)
					 repaint();
				dragged = null;
				offset = null;
			 }
		 });

		 addMouseMotionListener(new MouseAdapter() {
	
			 public void mouseDragged(MouseEvent e) {
				 if (dragged != null && offset != null) {
					 // Adjust the position of the drag point to allow for the
					 // click point offset
					 Point to = e.getPoint();
					 to.x = (int) Math.max(Math.min(to.x + offset.x, getWidth() - dragged.getWidth() / 2), 0 - dragged.getWidth() / 2);
					 to.y = (int) Math.max(Math.min(to.y + offset.y, getHeight() - dragged.getHeight() / 2), 0 - dragged.getHeight() / 2);
					 // Modify the position of the sommet...
					 Rectangle bounds = dragged.getBounds();
					 bounds.setLocation(to);
					 dragged.setFrame(bounds);
					 // repaint...
					 repaint();
				 }
			 }
		 });
     }	

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}

	public void ajouterSommet(){
		String nom = ++nbSommet + "";
		int x = (int) (Math.random() * getWidth()) - radius / 2;
		int y = (int) (Math.random() * getHeight()) - radius / 2;
		alSommet.add(new Sommet(x, y, radius, radius,nom));
		
		listSommet.majList(alSommet);
		repaint();
	}

	public void majRadius(int zoom){
		if(this.radius + zoom >= 30 && this.radius + zoom <= 100 )
			this.radius += zoom;
		for(Sommet s: alSommet){
			s.setFrame(s.getX(),s.getY(),radius,radius);
		}
		repaint();	
	}

	@Override
	protected void paintComponent(Graphics g) {
		// declaration
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g.create();
		// Draw the connecting lines first
		// This ensures that the lines are under the alSommet...
		/* - A MODIFIER ET A METTRE DANS UNE METHODE POUR LIER LES SOMMETS - */
		Point p = null;
		for (Sommet sommet : alSommet) {
			g2d.setColor(Color.BLACK);
			Point to = sommet.getBounds().getLocation();
			to.x += radius / 2;
			to.y += radius / 2;
			if (p != null)
				g2d.draw(new Line2D.Float(p, to));
			p = to;
		}

		//Dessine les sommets 
		for (Sommet sommet : alSommet){
			g2d.setColor(Color.PINK);
			g2d.fill(sommet);
			if (sommet == dragged) {
				g2d.setColor(Color.BLUE);
				g2d.draw(sommet);
			}
			g2d.setColor(Color.BLUE);

			FontMetrics fm = g.getFontMetrics();
			int textWidth = fm.stringWidth(sommet.getNom());
			int x = sommet.getBounds().x;
			int y = sommet.getBounds().y;
			int width = sommet.getBounds().width;
			int height = sommet.getBounds().height;
			g.drawString(sommet.getNom(),
			x + ((width - textWidth)) / 2,
			y + ((height - fm.getHeight()) / 2) + fm.getAscent());
		}

		g2d.dispose();

	}

}