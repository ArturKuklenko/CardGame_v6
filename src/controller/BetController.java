package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import graphic.interfaces.GUI;
import graphic.interfaces.SetBet;
import model.interfaces.Player;

public class BetController implements ActionListener {
	SetBet bet;
	GUI gui;
	public BetController(SetBet bet, GUI gui) {
		this.bet = bet;
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//String Ae = e.getActionCommand();
		if(gui==null) {
			JFrame jfd = new JFrame();
			JOptionPane.showMessageDialog(jfd, "null");
		}
		Player[] GuiPl = gui.getPlayers();
		for(Player player : GuiPl) {
			if(bet.getPlayerName().equals(player.getPlayerName())) {
				int betInt = 0;
				try {
				betInt = Integer.parseInt(bet.getBet());
				}catch(Exception e34) {}
				if(player.getPoints()>=betInt) {
					gui.setBet(player, betInt);
					JFrame jfd = new JFrame();
					JOptionPane.showMessageDialog(jfd, "Bet was set");
				}else {
					JFrame jfd = new JFrame();
					JOptionPane.showMessageDialog(jfd, "Bet is too big");
				}
				
			}
		}
		
		
	}

}
