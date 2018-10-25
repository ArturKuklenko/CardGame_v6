package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import graphic.interfaces.AddPlayer;
import graphic.interfaces.GUI;
import model.SimplePlayer;
import model.interfaces.Player;

public class AddPlayerController implements ActionListener {
	GUI gui;
	AddPlayer add;
	int index = 1;
	public AddPlayerController(AddPlayer add, GUI gui) {
		this.gui = gui;
		this.add = add;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = add.getName();
		int points = 0;
		int bet = 0;
		boolean correctInput = false;
		try {
			 points = Integer.parseInt(add.getPoints());
			 bet = Integer.parseInt(add.getBet());
			 correctInput = true;
		}catch(Exception e31) {
			JFrame jfd = new JFrame();
			JOptionPane.showMessageDialog(jfd, "incorrect input - points or bet");
			correctInput = false;
		}
		if(points<bet) {
			JFrame jfd = new JFrame();
			JOptionPane.showMessageDialog(jfd, "Player can't place bet larger than he have points");
			correctInput = false;
		}
		//String playerID, String playerName, int points
		if(correctInput) {
		String strIndex = ""+index;
		Player newPlayer = new SimplePlayer(strIndex, name, points);
		newPlayer.placeBet(bet);
		correctInput = gui.addPlayer(newPlayer);
		if(correctInput) {
		JFrame jfd = new JFrame();
		JOptionPane.showMessageDialog(jfd, "Player "+name+" was added");
		index++;
		}
		}
	}
}
