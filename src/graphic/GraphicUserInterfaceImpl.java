package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import controller.AddController;
import controller.GUIcontroller;
import controller.StartGameController;
import graphic.interfaces.GUI;
import model.CardsOfPlayerImpl;
import model.ScoreCountImpl;
import model.interfaces.CardsOfPlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.ScoreCount;

@SuppressWarnings("serial")
public class GraphicUserInterfaceImpl extends JPanel implements GUI {
	JMenu menuPlayers;
	ButtonGroup playersgroup;
	JToolBar toolbarScoreBar;
	List<ScoreCount> LabelScores = new ArrayList<>(0);
	JTextArea textArea;
	JLabel LabelPlayerName;
	JLabel LabelPlayerPoints;
	JLabel LabelPlayerBet;

	Image imageClubs = null;
	Image imageDiamonds = null;
	Image imageHearts = null;
	Image imageSpades = null;
	List<CardsOfPlayer> cardsOfPlayers = new ArrayList<>(3);
	List<PlayingCard> cardsToPaint;

	boolean startGame = false;
	GUIcontroller listener = new GUIcontroller(this);
	AddController addlistener = new AddController(this);
	StartGameController StartGamelistener = new StartGameController(this);
	Player[] players = new Player[] {};
	String SelectedPlayer = "";
	//GameEngine
	GameEngine engine;
	/*Constructor*/
	public GraphicUserInterfaceImpl() {
		getImages();
		GridLayout mainLayout = new GridLayout(2, 3);
		this.setLayout(mainLayout);
		this.setSize(new Dimension(200, 200));
		this.setLocation(300, 300);

		JToolBar toolbarPlayerName = new JToolBar();
		JToolBar toolbarStatusBar = new JToolBar();
		toolbarScoreBar = new JToolBar();
		JToolBar toolbarEmpty1 = new JToolBar();
		JToolBar toolbarEmpty2 = new JToolBar();
		JToolBar toolbarEmpty3 = new JToolBar();
		this.textArea = new JTextArea("", 5, 50);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		JScrollPane sbrText = new JScrollPane(textArea);
		LabelPlayerName = new JLabel("");
		LabelPlayerPoints = new JLabel("points: ");
		LabelPlayerBet = new JLabel("bet: ");	
		//}

		toolbarPlayerName.setSize(200, 200);
		toolbarStatusBar.setSize(200, 200);
		toolbarScoreBar.setSize(200, 200);
		toolbarEmpty1.setSize(200, 200);
		toolbarEmpty2.setSize(200, 200);
		toolbarEmpty3.setSize(200, 200);
		toolbarPlayerName.setFloatable(false);
		toolbarStatusBar.setFloatable(false);
		toolbarScoreBar.setFloatable(false);
		toolbarEmpty1.setFloatable(false);
		toolbarEmpty2.setFloatable(false);
		toolbarEmpty3.setFloatable(false);

		toolbarEmpty1.setVisible(false);
		toolbarEmpty2.setVisible(false);
		toolbarEmpty3.setVisible(false);

		BoxLayout BoxLayoutToolbar1 = new BoxLayout(toolbarPlayerName, BoxLayout.PAGE_AXIS);
		BoxLayout BoxLayoutToolbar3 = new BoxLayout(toolbarScoreBar, BoxLayout.PAGE_AXIS);

		toolbarPlayerName.setLayout(BoxLayoutToolbar1);
		toolbarScoreBar.setLayout(BoxLayoutToolbar3);
		toolbarPlayerName.add(LabelPlayerName);
		toolbarPlayerName.add(LabelPlayerPoints);
		toolbarPlayerName.add(LabelPlayerBet);
		toolbarStatusBar.add(sbrText);

		JLabel label = new JLabel("House score: ");
		label.setName("House");
		label.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));
		LabelScores.add(new ScoreCountImpl(label, 0));
		for(ScoreCount scoreCount:LabelScores) {
			if(scoreCount.getLabel().getName().equals("House")) {
				toolbarScoreBar.add(scoreCount.getLabel());
			}
		}

		this.add(toolbarPlayerName);
		this.add(toolbarStatusBar);
		this.add(toolbarScoreBar);
		this.add(toolbarEmpty1);
		this.add(toolbarEmpty2);
		this.add(toolbarEmpty3);
		JFrame jf = new JFrame();
		jf.setTitle("Assignment 2");
		jf.add(this);
		jf.setJMenuBar(createMenuBar());
		jf.setSize(600, 400);
		jf.setLocation(300, 300);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*The function below create menu only*/
	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu;
		JRadioButtonMenuItem rbMenuItem;
		JMenuItem menuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menuPlayers = new JMenu("Players");
		menuPlayers.setMnemonic(KeyEvent.VK_A);
		menuPlayers.getAccessibleContext().setAccessibleDescription("Players chooser");
		menuBar.add(menuPlayers);

		playersgroup = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("House");
		rbMenuItem.setMnemonic(KeyEvent.VK_3);
		rbMenuItem.addActionListener(listener);
		playersgroup.add(rbMenuItem);
		menuPlayers.add(rbMenuItem);

		// Build second menu in the menu bar.
		menu = new JMenu("Add");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("This menu adding players");

		menuItem = new JMenuItem("Place Bet", KeyEvent.VK_4);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		menuItem.addActionListener(addlistener);
		menu.add(menuItem);
		menuItem = new JMenuItem("Add Player", KeyEvent.VK_5);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		menuItem.addActionListener(addlistener);
		menu.add(menuItem);
		menuBar.add(menu);
		
		
		menu = new JMenu("Start menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("This menu start game");

		menuItem = new JMenuItem("Start game", KeyEvent.VK_S);
		//menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		menuItem.addActionListener(StartGamelistener);
		menu.add(menuItem);
		//StartGamelistener
		menuBar.add(menu);
		return menuBar;
	}
	/*Calls when repaint() function was called*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cardAxisX = 80;
		if (cardsToPaint != null) {
			if (cardsToPaint.size() > 0) {
				for (PlayingCard card : cardsToPaint) {
					Color color = Color.BLUE;
					String cardValue = card.getValue().toString();
					Image ActualImage = null;
					switch (card.getSuit().toString()) {
					case "HEARTS":
						color = Color.RED;
						ActualImage = imageHearts;
						break;
					case "CLUBS":
						color = Color.BLACK;
						ActualImage = imageClubs;
						break;
					case "SPADES":
						color = Color.BLACK;
						ActualImage = imageSpades;
						break;
					case "DIAMONDS":
						color = Color.RED;
						ActualImage = imageDiamonds;
						break;
					}
					paintCard(g, ActualImage, color, cardValue, cardAxisX, 230);
					cardAxisX = cardAxisX + 80;
				}
			}
		}
	}
	/*Painting cards*/
	public void paintCard(Graphics g, Image image, Color color, String name, int x, int y) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 70, 100);
		g.drawImage(image, x + 15, y + 20, 40, 60, null);
		g.setColor(color);
		g.drawString(name, x + 15, y + 15);
		g.drawString(name, x + 15, y + 95);
	}
	/*GetImages starts only only once to get images from files*/
	public void getImages() {
		try {
			File fileClubs = new File("./resources/" + "clubs.jpg");
			File fileDiamonds = new File("./resources/" + "diamonds.jpg");
			File fileHearts = new File("./resources/" + "hearts.jpg");
			File fileSpades = new File("./resources/" + "spades.jpg");
			imageClubs = ImageIO.read(fileClubs);
			imageDiamonds = ImageIO.read(fileDiamonds);
			imageHearts = ImageIO.read(fileHearts);
			imageSpades = ImageIO.read(fileSpades);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/*This function used only for gui, it desids which card we should show for current player */
	public void setPlayerDetails(String currentPlayer) {
		this.SelectedPlayer = currentPlayer;
		boolean cardsSet = false;
		for(Player pl: players) {
			if (pl.getPlayerName().equals(currentPlayer)) {
				LabelPlayerName.setText(pl.getPlayerName());
				String points = "points: " + pl.getPoints();
				LabelPlayerPoints.setText(points);
				String bet = "bet: " + pl.getBet();
				LabelPlayerBet.setText(bet);
			}
		}
		if ("House".equals(currentPlayer)) {
			LabelPlayerName.setText("House");
			String points = "points:  0";
			LabelPlayerPoints.setText(points);
			String bet = "bet: 0";
			LabelPlayerBet.setText(bet);
		}

		for (int i = 0; i < cardsOfPlayers.size(); i++) {
			if (cardsOfPlayers.get(i).getPlayer().getPlayerName().equals(currentPlayer)) {
				String points = "points: " + cardsOfPlayers.get(i).getPlayer().getPoints();
				LabelPlayerPoints.setText(points);
				String bet = "bet: " + cardsOfPlayers.get(i).getPlayer().getBet();
				LabelPlayerBet.setText(bet);
				setPaintCards(cardsOfPlayers.get(i).getPlayerCards());
				// repaint();
				cardsSet = true;
			}
		}
		if (cardsSet == false) {
			setPaintCards(null);
		}
		repaint();
	}

	public void setPaintCards(List<PlayingCard> cardsToPaint) {
		this.cardsToPaint = cardsToPaint;
	}
	/*
	 * Gui collect information which card owns to player inside CardsOfPlayerImpl
	 * */
	public void addPlayerCard(Player player, PlayingCard card) {

		if (cardsOfPlayers.contains(new CardsOfPlayerImpl(player))) {
			cardsOfPlayers.get(cardsOfPlayers.indexOf(new CardsOfPlayerImpl(player))).addPlayerCard(card);
		} else {
			CardsOfPlayerImpl newCardPlayer = new CardsOfPlayerImpl(player);
			newCardPlayer.addPlayerCard(card);
			cardsOfPlayers.add(newCardPlayer);
		}
		setPlayerDetails(this.SelectedPlayer);
	}
	/*This function every time updating scores in GUI and also collect them*/
	public void setScore(String playerName, int Pscore) {
		
		for(ScoreCount scoreCount:LabelScores) {
			if(scoreCount.getLabel().getName().equals(playerName)) {
				int score = scoreCount.getScore() + Pscore;
				scoreCount.setScore(score);
				scoreCount.setLabelText(scoreCount.getLabel().getName()+" score: "+score);
			}
		}
		
	}
	/*This function works just like logging*/
	public void addTextToTextArea(String str) {
		textArea.append(str + "\n");
	}
	/*finishGame this function calls by CallbackGUI when house finished*/
	public void finishGame(GameEngine engine) {
		int Max = 0;
		StringBuilder builderStr = new StringBuilder();
		ArrayList<String> winners = new ArrayList<>();
		for(ScoreCount scoreCount:LabelScores) {
				if(Max<scoreCount.getScore()){
				Max = scoreCount.getScore();
			}
		}
		for(ScoreCount scoreCount:LabelScores) {
			if(Max==scoreCount.getScore()){
				winners.add(scoreCount.getLabel().getName());
				builderStr.append(scoreCount.getLabel().getName()+" ");
			}
		}
		builderStr.append(" win with result "+Max);
		JFrame jfd = new JFrame();
		JOptionPane.showMessageDialog(jfd, builderStr);
		setPlayerPointsInGUIArray(winners);
	}
	/*After game finished we add changed points info in our array of players*/
	public void setPlayerPointsInGUIArray(ArrayList<String> winners) {
		for(Player player : players) {
			if(winners.contains(player.getPlayerName())) {
				int points = player.getPoints()+(player.getBet()*2);
				player.setPoints(points);
			}else {
				int points = player.getPoints()-(player.getBet());
				player.setPoints(points);
			}
		}
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public void setBet(Player player, int bet ) {
		for(Player pl: players) {
			if(pl.getPlayerName().equals(player.getPlayerName())) {
			pl.placeBet(bet);
			}
		}
	}
	/*
	 * Add player to Player[] inside gui 
	 * */
	public boolean addPlayer(Player player) {
		if(player.getPlayerName().equals("House")) {
			JFrame jfd = new JFrame();
			JOptionPane.showMessageDialog(jfd,
					"You can't add player with name House");
			return false;
		}
		for(Player pl: players) {
			if(pl.getPlayerName().equals(player.getPlayerName())) {
				JFrame jfd = new JFrame();
				JOptionPane.showMessageDialog(jfd, "Player "
				+player.getPlayerName()+" already exist");
				return false;
			}
		}
		int newLength = players.length+1;
		Player[] pl = new Player[newLength];
		int count = 0;
		for(;count<players.length; count++) {
			pl[count] = players[count];
		}
		if(count == (pl.length-1)) {
			pl[count] = player;
		}
		count++;
		if(count == (pl.length-1)) {
			pl[count] = player;
		}
		players = pl;
		/*if(engine != null) {
			engine.addPlayer(player);
		}*/
		addPlayerToMenu(player.getPlayerName());
		return true;
	}
	public void addPlayerToMenu(String playerName) {
		JRadioButtonMenuItem rbMenuItem;
		rbMenuItem = new JRadioButtonMenuItem(playerName);
		rbMenuItem.setMnemonic(KeyEvent.VK_3);
		rbMenuItem.addActionListener(listener);
		//group.add(rbMenuItem);
		playersgroup.add(rbMenuItem);
		menuPlayers.add(rbMenuItem);
		JLabel label = new JLabel(playerName+" score: ");
		label.setName(playerName);
		label.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));
		LabelScores.add(new ScoreCountImpl(label, 0));
		for(ScoreCount scoreCount:LabelScores) {
			if(scoreCount.getLabel().getName().equals(playerName)) {
				toolbarScoreBar.add(scoreCount.getLabel());
			}
		}
	}
	public GameEngine getEngine() {
		return engine;
	}
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}

	public boolean getStartGame() {
		return startGame;
	}

	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}

	public void setSelectedPlayer(String selectedPlayer) {
		SelectedPlayer = selectedPlayer;
	}
	
}
