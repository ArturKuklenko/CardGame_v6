package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {
	private Player house;
	private Collection<GameEngineCallback> engineCallbacks;
	private Collection<Player> playersList;
	// change new LinkedList<>(); to generateDeck()
	// Deque<PlayingCard> cards
	Deque<PlayingCard> cards = new LinkedList<>();
	GameEngineImpl ThisGameEngine = this;

	public GameEngineImpl() {
		generateDeck();
		playersList = new ArrayList<Player>();
		house = new SimplePlayer("0", "House", 0);
		playersList.add(house);
		engineCallbacks = new ArrayList<GameEngineCallback>();
		shuffleDeck();
	}

	@Override
	public void dealPlayer(Player player, int delay) {

		int handTotal = 0;
		while (handTotal <= BUST_LEVEL) {
			//placeBet(player, player.getPoints() / 5);
			try {
				Thread.sleep(delay);
				/* That is my adding */
				PlayingCard card = null;
				if (cards.size() > 0) {
					synchronized (ThisGameEngine) {
						card = cards.pop();
					}
				}

				if (cards.size() == 0) {
					shuffleDeck();
				}
				if (card != null) {
					if ((handTotal + card.getScore()) <= BUST_LEVEL) {
						handTotal += card.getScore();
						Iterator<GameEngineCallback> iterator = engineCallbacks.iterator();
						while (iterator.hasNext()) {
							GameEngineCallback callback = (GameEngineCallback) iterator.next();
							callback.nextCard(player, card, ThisGameEngine);
						}

					} else {
						player.setResult(handTotal);
						Iterator<GameEngineCallback> iterator = engineCallbacks.iterator();
						while (iterator.hasNext()) {
							GameEngineCallback callback = (GameEngineCallback) iterator.next();
							callback.bustCard(player, card, ThisGameEngine);
							callback.result(player, player.getResult(), ThisGameEngine);
							if(player.getPlayerName().equals("House")) {
								callback.houseResult(player.getResult(), ThisGameEngine);
							}
						}
						break;
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void dealHouse(int delay) {
		dealPlayer(house, delay);
		// I commented this
		// shuffleDeck();
	}

	@Override
	public void addPlayer(Player player) {
		if(playersList.contains(player)==false) {
			playersList.add(player);
		}

		/*for (int i = 0; i < playersList.size(); i++) {
			if (((ArrayList<Player>) playersList).get(i) != player) {
				playersList.add(player);

			}
		}
		if (playersList.size() == 0) {
			playersList.add(player);
		}*/
	}

	@Override
	public Player getPlayer(String id) {
		for (Player player : playersList) {
			if (player.getPlayerId().equals(id)) {
				return player;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		return playersList.remove(player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		engineCallbacks.add(gameEngineCallback);

	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return engineCallbacks.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		/*Collection<Player> collection = new ArrayList<Player>();
		for (Player player : playersList) {
			collection.add(player);
		}*/
		return playersList;
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		if (bet > 0 || bet < player.getBet()) {
			return player.placeBet(bet);
		}
		return false;

	}

	@Override
	public Deque<PlayingCard> getShuffledDeck() {
		return cards;
	}

	// Delete Static
	// Creates a standard deck of 52 cards
	private void generateDeck() {
		// ArrayList<PlayingCard> deck = new ArrayList<>();
		this.cards = new LinkedList<>();
		for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {
			for (PlayingCard.Value value : PlayingCard.Value.values()) {
				// deck.add(new PlayingCardImpl(suit, value));
				// for a while
				cards.add(new PlayingCardImpl(suit, value));
			}

		}
		// return deck;
	}

	// shuffles the created deck
	private void shuffleDeck() {
		LinkedList<PlayingCard> cardsList = new LinkedList<>(this.cards);
		Deque<PlayingCard> NewcardsList = new LinkedList<>();

		int Min = 0;
		int randomPlayercard = 0;

		while (cardsList.size() != 0) {
			int Max = cardsList.size() - 1;
			randomPlayercard = Min + (int) (Math.random() * ((Max - Min) + 1));
			PlayingCard currentcard = cardsList.get(randomPlayercard);
			cardsList.remove(randomPlayercard);
			NewcardsList.addLast(currentcard);
		}
		this.cards = new LinkedList<>();
		this.cards.addAll(NewcardsList);
	}

	/*
	 * private Deque<PlayingCard> shuffleDeck(Deque<PlayingCard> argumentCards) {
	 * LinkedList<PlayingCard> cardsList =new LinkedList<>(argumentCards);
	 * Deque<PlayingCard> NewcardsList = new LinkedList<>();
	 * 
	 * int Min = 0; int randomPlayercard = 0;
	 * 
	 * while(cardsList.size() != 0) { int Max = cardsList.size()-1; randomPlayercard
	 * = Min + (int) (Math.random() * ((Max - Min) + 1)); PlayingCard currentcard =
	 * cardsList.get(randomPlayercard); cardsList.remove(randomPlayercard);
	 * NewcardsList.addLast(currentcard); } return NewcardsList; }
	 */

}
