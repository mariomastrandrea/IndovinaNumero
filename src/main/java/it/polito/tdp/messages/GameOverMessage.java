package it.polito.tdp.messages;

public class GameOverMessage extends Message
{
	public GameOverMessage(String gameOverText) { super(gameOverText); }

	@Override
	public boolean isError() { return false; }

	@Override
	public boolean isGameOver() { return true; }

}
