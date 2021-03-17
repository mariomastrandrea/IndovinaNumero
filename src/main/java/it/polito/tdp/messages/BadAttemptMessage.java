package it.polito.tdp.messages;

public class BadAttemptMessage extends Message
{
	public BadAttemptMessage(String messageText) { super(messageText); }

	@Override
	public boolean isError() { return false; }

	@Override
	public boolean isGameOver() { return false; }

}
