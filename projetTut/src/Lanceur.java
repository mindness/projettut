import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import javax.swing.event.*;
//Ca lance mes couilles cest tout omfg
public class Lanceur extends JFrame implements ActionListener
{	
	private JMenuBar mb = new JMenuBar();

	private JMenu fichier, ouvrir;
	private String nom;
	//private JFileChooser open, enregistrer;	
	private JMenuItem nouveau, enreg, enregSous, quitter, fichTxt, fichSav;

	private JMenu outils;
	private JMenuItem agrandirZoom, reduireZoom;

	private JMenuItem apd;

	private JToolBar tb;
	private JButton save,zoomPlus,zoomMoins, ajoutSommet, suppSommet;

	private ListSommet listSommet;
	
	private GraphPanel graphPanel;
	private JScrollPane pane;
	
	public Lanceur(){
		setTitle("Lanceur");
		setLocation(50,30);
		setSize(800,500);
		
		/* Début Création JMenuBar */
			//Création du menu Fichier
		fichier = new JMenu ("Fichier");
		nouveau = new JMenuItem ("Nouveau");
		fichier.add(nouveau);
		ouvrir = new JMenu ("Charger/Ouvrir");
		fichTxt = new JMenuItem("Depuis un fichier texte");
		ouvrir.add(fichTxt);
		fichSav = new JMenuItem("Depuis une sauvegarde");
		ouvrir.add(fichSav);		
		fichier.add(ouvrir);
		fichier.addSeparator();
		enreg = new JMenuItem ("Enregistrer");
		fichier.add(enreg);
		enregSous = new JMenuItem ("Enregistrer Sous");
		fichier.add(enregSous);
		fichier.addSeparator();
		quitter = new JMenuItem ("Quitter");
		fichier.add(quitter);
		mb.add(fichier);
		
		//Création du menu Outils
		outils = new JMenu ("Outils");
		agrandirZoom = new JMenuItem ("Zoom +");
		outils.add(agrandirZoom);
		reduireZoom = new JMenuItem ("Zoom -");
		outils.add(reduireZoom);
		mb.add(outils);
		
			//Création de menu A propos de...
		apd = new JMenuItem ("A propos de...");
		mb.add(apd);
		
		setJMenuBar(mb);
		/* Fin Création JMenuBar */
		
		/* Début Création JToolBar */
		tb = new JToolBar();
		tb.setFloatable(false);
		save = new JButton( new ImageIcon("img/save.gif") );
		save.setToolTipText("Sauvegarder");
		tb.add(save);
		zoomPlus = new JButton( new ImageIcon("img/zoomPlus.gif") );
		zoomPlus.addActionListener(this);
		zoomPlus.setToolTipText("Zoom (+)");
		tb.add(zoomPlus);
		zoomMoins = new JButton( new ImageIcon("img/zoomMoins.gif") );
		zoomMoins.addActionListener(this);
		zoomMoins.setToolTipText("Zoom (-)");
		tb.add(zoomMoins);
		ajoutSommet = new JButton (new ImageIcon("img/ajoutS.gif"));
		ajoutSommet.addActionListener(this);
		tb.add(ajoutSommet);
		suppSommet = new JButton (new ImageIcon("img/suppS.gif"));
		suppSommet.addActionListener(this);
		tb.add(suppSommet);
		add(tb,"North");
		/* Fin Création JToolBar */
		
		/*Début Création JList des Sommets*/
		listSommet = new ListSommet();
		add(listSommet,"West");
		/*Fin Création JList des Sommets*/
		
		/*Début Création JPanel de dessin*/
		graphPanel = new GraphPanel(listSommet);
		add(graphPanel);
		/*Fin Création JPanel de dessin*/

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Fermeture de l'application avec clic sur la croix rouge
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==ajoutSommet)
			graphPanel.ajouterSommet();
		if(e.getSource()==zoomPlus)
			graphPanel.majRadius(10);
		if(e.getSource()==zoomMoins)
			graphPanel.majRadius(-10);
	}

	public static void main(String[] args){
		new Lanceur();
	}
}