package graphic;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AddPlayerController;
import graphic.interfaces.AddPlayer;
import graphic.interfaces.GUI;
import model.interfaces.Player;

public class AddPlayerImpl implements AddPlayer {
	JTextField textName;
	JTextField textPoints;
	JTextField betPoints;
	GUI gui;

	public AddPlayerImpl(Player[] players, GUI gui) {
		this.gui = gui;
		AddPlayerController addController = new AddPlayerController(this, gui);
		String[] labels = { "Player name: ", "Player points: ", "Player bet: ", "" };
		int numPairs = labels.length;
		/*String[] playerNames = new String[players.length];
		int count = 0;
		for (Player pl : players) {
			playerNames[count] = pl.getPlayerName();
			count++;
		}*/
		// Create and populate the panel.
		GridLayout layout = new GridLayout(4, 2);
		JPanel p = new JPanel(layout);

		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			l.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
			p.add(l);
			if (i == 0) {
				textName = new JTextField(10);
				l.setLabelFor(textName);
				p.add(textName);
			} else if (i == 1) {
				textPoints = new JTextField(10);
				l.setLabelFor(textPoints);
				p.add(textPoints);
			} else if (i == 2) {
				betPoints = new JTextField(10);
				l.setLabelFor(betPoints);
				p.add(betPoints);
			} else if (i == 3) {
				JButton submit = new JButton("Submit");
				submit.addActionListener(addController);
				l.setLabelFor(submit);
				p.add(submit);
			}

		}

		JFrame frame = new JFrame("Add player");

		// Set up the content pane.
		p.setOpaque(true); // content panes must be opaque
		frame.setContentPane(p);
		frame.setLocation(350, 350);
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public String getName() {
		return textName.getText();
	}

	public String getPoints() {
		return textPoints.getText();
	}
	
	public String getBet() {
		return betPoints.getText();
	}

}
