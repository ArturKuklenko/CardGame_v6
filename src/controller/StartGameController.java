package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import graphic.interfaces.GUI;
import model.interfaces.Player;

public class StartGameController implements ActionListener {
	GUI gui;
	public StartGameController(GUI gui) {
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Player[] pls = gui.getPlayers();
		try {
		if(pls[0]!=null) {
			gui.setSelectedPlayer(pls[0].getPlayerName());
			gui.setStartGame(true);
		}
		}catch(Exception e1) {
			JFrame jfd = new JFrame();
			JOptionPane.showMessageDialog(jfd, "You can't start game without at least one player");
		}
	}
}
