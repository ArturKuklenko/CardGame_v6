package model;

import model.interfaces.Player;

public class SimplePlayer implements Player 
{
	private String playerID;
	private String playerName;
	//private int initialPoints;
	private int points;
	private int bet = 0;
	private int result = 0;
	
	public SimplePlayer(String playerID, String playerName, int points)
	{
		this.playerID = playerID;
		this.playerName = playerName;
		this.bet = 0;
		this.points = points;
	}
	@Override
	public String getPlayerName() 
	{
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}

	@Override
	public int getPoints() 
	{
		return points;
	}

	@Override
	public void setPoints(int points) 
	{
		this.points = points;
	}

	@Override
	public String getPlayerId() 
	{
		return playerID;
	}

	@Override
	public boolean placeBet(int bet) 
	{
		boolean verify = false;
		if (bet >= 0 && bet <= points){
		verify = true;
		this.bet = bet;
		}else{
		verify = false;
		}
		return verify;
	}

	@Override
	public int getBet() 
	{
		return this.bet;
	}

	@Override
	public void resetBet() 
	{
		bet = 0;
	}

	@Override
	public int getResult() 
	{
		return result;
	}

	@Override
	public void setResult(int result) 
	{
		this.result = result;
	}

}
