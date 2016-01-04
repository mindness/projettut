import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;

public class ListSommet extends JPanel implements ListSelectionListener{

	private DefaultListModel model;
	private JScrollPane pane;
	private JList listeSommet;
	
	public ListSommet(){
		model = new DefaultListModel();
    	listeSommet = new JList(model);
		listeSommet.addListSelectionListener(this);
		listeSommet.setPreferredSize(new Dimension(200,500) );
   		pane = new JScrollPane(listeSommet);
   		pane.setPreferredSize(new Dimension(200,500) );
		add(pane);
	}

	public void valueChanged(ListSelectionEvent e) {
		
	}
	
	public void majList(ArrayList<Sommet> alSommet){
		model.removeAllElements();
		for(Sommet sommet : alSommet)
			model.addElement( "Sommet " + sommet.getNom() );
	}
}