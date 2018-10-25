package model;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Player;
import model.interfaces.CardsOfPlayer;
import model.interfaces.PlayingCard;

public class CardsOfPlayerImpl implements CardsOfPlayer {
	private Player player;
	List<PlayingCard> playerCards= new ArrayList<>();
	
	public CardsOfPlayerImpl(Player player) {
		super();
		this.player = player;
	}
	@Override
	public Player getPlayer() {
		return player;
	}
	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}
	@Override
	public List<PlayingCard> getPlayerCards() {
		return playerCards;
	}
	@Override
	public void setPlayerCardsToEmpty() {
		this.playerCards = new ArrayList<>();
	}
	@Override
	public void addPlayerCard(PlayingCard playerCard) {
		this.playerCards.add(playerCard);
	}
	@Override
	public String toString() {
		return player.getPlayerName()+" ";
	}
	@Override
	public boolean equals(Object o) {
		CardsOfPlayer  cardsOfPlayer =  (CardsOfPlayer) o;
		return player.getPlayerName().equals(cardsOfPlayer.getPlayer().getPlayerName());
	}
}
