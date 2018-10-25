package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard 
{
	Suit cardSuit;
	Value cardValue;
	
	public PlayingCardImpl(Suit suit, Value value)
	{
		this.cardSuit = suit;
		this.cardValue = value;
	}

	@Override
	public Suit getSuit() 
	{
		return this.cardSuit;
	}

	@Override
	public Value getValue() 
	{
		return this.cardValue;
	}

	@Override
	public int getScore() 
	{
		int score = 0;
		if (this.getValue() == Value.ACE)
		{
			score = 1;
		}
		if (this.getValue() == Value.TWO)
		{
			score = 2;
		}
		if (this.getValue() == Value.THREE)
		{
			score = 3;
		}
		if (this.getValue() == Value.FOUR)
		{
			score = 4;
		}
		if (this.getValue() == Value.FIVE)
		{
			score = 5;
		}
		if (this.getValue() == Value.SIX)
		{
			score = 6;
		}
		if (this.getValue() == Value.SEVEN)
		{
			score = 7;
		}
		if (this.getValue() == Value.EIGHT)
		{
			score = 8;
		}
		if (this.getValue() == Value.NINE)
		{
			score = 9;
		}
		if (this.getValue() == Value.JACK || this.getValue() == Value.QUEEN || this.getValue() == Value.KING || this.getValue() == Value.TEN)
		{
			score = 10;
		}
			
		return score;
		
	}
	
	@Override
	public boolean equals(PlayingCard card) 
	{
		return equals((Object) card);
	}
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (getClass() != obj.getClass() || (obj == null))
			return false;
		PlayingCard other = (PlayingCardImpl) obj;
		if (cardSuit != other.getSuit())
			return false;
		return true;
		
	}
	@Override
	public String toString() {
		return cardSuit +" "+cardValue;
	}
}
