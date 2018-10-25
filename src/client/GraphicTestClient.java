package client;

import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

import graphic.GraphicUserInterfaceImpl;
import graphic.interfaces.GUI;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import validate.Validator;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;

/**
 * Simple console client for SADI assignment 2, 2018 NOTE: This code will not
 * compile until you have implemented code for the supplied interfaces!
 * 
 * You must be able to compile your code WITHOUT modifying this class.
 * Additional testing should be done by copying and adding to this class while
 * ensuring this class still works.
 * 
 * The provided Validator.jar will check if your code adheres to the specified
 * interfaces!
 * 
 * @author Caspar Ryan
 * 
 */
public class GraphicTestClient {
	private static Logger logger = Logger.getLogger("assignment2");

	public static void main(String args[]) {
		// call method in Validator.jar to test *structural* correctness
		// just passing this does not mean it actually works .. you need to test
		// yourself!
		// pass false if you want to disable logging .. (i.e. once it passes)
		Validator.validate(true);

	
		GUI mainGui = new GraphicUserInterfaceImpl();

		/*
		 * We need to wait while players adding
		 * */
		while (mainGui.getStartGame() == false) {
			Thread.currentThread();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Player[] players = mainGui.getPlayers();
		final GameEngine gameEngine = new GameEngineImpl();

		
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(mainGui));
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl(mainGui));
		// Uncomment this to DEBUG your deck of cards creation
		Deque<PlayingCard> shuffledDeck = gameEngine.getShuffledDeck();
		printCards(shuffledDeck);

		// Each player plays in his own thread
		for (Player player : players) {
			new Thread() {
				@Override
				public void run() {
					gameEngine.addPlayer(player);
					gameEngine.placeBet(player, player.getBet());
					gameEngine.dealPlayer(player, 1000);
				}
			}.start();
		}
		// I set dealHouse delay to 1200 because we need it finished the latest
		new Thread() {
			@Override
			public void run() {
				gameEngine.dealHouse(1200);
			}
		}.start();
	}

	@SuppressWarnings("unused")
	private static void printCards(Deque<PlayingCard> deck) {
		for (PlayingCard card : deck)
			logger.log(Level.INFO, card.toString());
	}
}
