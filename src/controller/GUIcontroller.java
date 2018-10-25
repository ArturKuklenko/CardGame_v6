package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import graphic.interfaces.GUI;

public class GUIcontroller implements ActionListener {
	GUI gui;
	public GUIcontroller(GUI gui) {
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String Ae = e.getActionCommand();
		gui.setPlayerDetails(Ae);
		
	}

}
