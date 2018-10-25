package graphic;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BetController;
import graphic.interfaces.GUI;
import graphic.interfaces.SetBet;
import graphic.SetBetImpl;
import model.interfaces.Player;


public class SetBetImpl implements SetBet {
	@SuppressWarnings("rawtypes")
	JComboBox playerList;
	JTextField textField;
	GUI gui;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SetBetImpl(Player[] players, GUI gui) {
		this.gui = gui;
		BetController betController = new BetController(this, gui);
		String[] labels = {"Player name: ", "Player bet: ", " "};
        int numPairs = labels.length;
        String[] playerNames = new String[players.length];
        int count =0;
        for(Player pl : players) {
        	playerNames[count] = pl.getPlayerName();
        	count++;
        }
        //Create and populate the panel.
        GridLayout layout = new GridLayout(3, 2);
        JPanel p = new JPanel(layout);
        
        
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
        	l.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
            p.add(l);
            if(i==0) {
            	playerList = new JComboBox(playerNames);
            	//playerList.setSize(100, 30);
            	playerList.getSelectedItem();
            	l.setLabelFor(playerList);
                p.add(playerList);
            }else if(i==1){
            textField = new JTextField(10);
            l.setLabelFor(textField);
            p.add(textField);
            }else if(i==2) {
            JButton submit = new JButton("Submit");
            submit.addActionListener(betController);
            l.setLabelFor(submit);
            p.add(submit);
            }
            
            
        }

       
        JFrame frame = new JFrame("Place Bet");

        //Set up the content pane.
        p.setOpaque(true);  //content panes must be opaque
        frame.setContentPane(p);
        frame.setLocation(350, 350);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	public String getPlayerName() {
		return playerList.getSelectedItem().toString();
	}
	public String getBet() {
		return textField.getText();
	}
	
}
