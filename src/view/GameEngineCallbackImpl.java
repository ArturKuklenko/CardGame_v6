package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import graphic.interfaces.GUI;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private final Logger logger = Logger.getLogger(this.getClass().getName());
   GUI gui;
   public GameEngineCallbackImpl(GUI gui)
   {
      // FINE shows dealing output, INFO only shows result
      logger.setLevel(Level.FINE);
      this.gui = gui;
   }

   @Override
   public void nextCard(Player player, PlayingCard card, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
      logger.log(Level.FINE, String.format(player.getPlayerName()+ " was dealt" + "Suit: " + card.getSuit()+ "Value: " +card.getValue())+ "Score: " +card.getScore());
      // TODO: complete this method to log results
   }

   @Override
   public void result(Player player, int result, GameEngine engine)
   {
      // final results logged at Level.INFO
      logger.log(Level.INFO, String.format(player.getPlayerName()+ "' s result is " + result));
      // TODO: complete this method to log results
   }

@Override
public void bustCard(Player player, PlayingCard card, GameEngine engine) 
{
	logger.log(Level.FINE, String.format(player.getPlayerName() + " has busted with the card "+ "Suit: " + card.getSuit() + "Value: " + card.getValue() + "Score: " + card.getScore()));
}

@Override
public void nextHouseCard(PlayingCard card, GameEngine engine) 
{	
	logger.log(Level.FINE, String.format("The house was dealt: " + "Suit: " + card.getSuit() + "Value: " + card.getValue() + "Score: " + card.getScore()));
}

@Override
public void houseBustCard(PlayingCard card, GameEngine engine) 
{
	logger.log(Level.FINE, String.format("The house has busted: " + "Suit: " + card.getSuit() + "Value: " + card.getValue() + "Score: " + card.getScore()));
}


@Override
public void houseResult(int result, GameEngine engine) 
{
	try {
		Thread.currentThread();
		Thread.sleep(1500);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	StringBuffer str = new StringBuffer("Final Results:\n");
	logger.log(Level.INFO, "House's final result: "+ result);
	for(Player player: engine.getAllPlayers()){
		str.append("Player id: "+player.getPlayerId()+", name: "+player.getPlayerName()+", points: "+player.getPoints()).append('\n');
	}
	logger.log(Level.INFO, String.format(str.toString()));
}
}
