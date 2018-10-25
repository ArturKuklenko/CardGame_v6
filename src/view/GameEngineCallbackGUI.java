package view;

import graphic.interfaces.GUI;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;
/*I have created it*/
public class GameEngineCallbackGUI implements GameEngineCallback {
	GUI gui;
	boolean firstStart = true;
	public GameEngineCallbackGUI(GUI gui) {
		this.gui = gui;
	}
	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		if(gui == null) {
			System.out.println("gui null");
		}
		if(firstStart) {
			gui.setEngine(engine);
			firstStart = false;
		}
		gui.addPlayerCard(player, card);
		gui.setScore(player.getPlayerName(), card.getScore());
		gui.addTextToTextArea(player.getPlayerName()+" taking card");
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		gui.addTextToTextArea(player.getPlayerName()+" finished");
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		//gui.setPlayerResult(player, result, engine);
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		gui.addTextToTextArea("House taking card");
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		gui.addTextToTextArea("House finished");
		
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		gui.finishGame(engine);
	}

}
