package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import graphic.AddPlayerImpl;
import graphic.SetBetImpl;
import graphic.interfaces.GUI;

public class AddController implements ActionListener {
	GUI gui;
	public AddController(GUI gui) {
		this.gui = gui;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String Ae = e.getActionCommand();
		//JFrame jfd = new JFrame();
		//JOptionPane.showMessageDialog(jfd, gui.getPlayers().length);
		if(Ae.equals("Place Bet")) {
			new SetBetImpl(gui.getPlayers(), gui);	
		}
		if(Ae.equals("Add Player")) {
			new AddPlayerImpl(gui.getPlayers(), gui);
		}

	}
}