package model.interfaces;

import java.util.List;

public interface CardsOfPlayer {
	public Player getPlayer();
	public void setPlayer(Player player);
	public List<PlayingCard> getPlayerCards();
	public void setPlayerCardsToEmpty();
	public void addPlayerCard(PlayingCard playerCard);
	public String toString();
	public boolean equals(Object o);
}
