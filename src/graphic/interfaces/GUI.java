package graphic.interfaces;

import java.util.ArrayList;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public interface GUI {
	public void addPlayerCard(Player player, PlayingCard card);
	public void addTextToTextArea(String str);
	public void setPlayerDetails(String ae);
	public Player[] getPlayers();
	public void setBet(Player player, int bet );
	public boolean addPlayer(Player newPlayer);
	public GameEngine getEngine();
	public void setEngine(GameEngine engine);
	public boolean getStartGame();
	public void setStartGame(boolean startGame);
	public void setScore(String playerName, int score);
	public void finishGame(GameEngine engine);
	public void setPlayerPointsInGUIArray(ArrayList<String> winners);
	public void setSelectedPlayer(String selectedPlayer);
}
