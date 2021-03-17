package it.polito.tdp.messages;

public abstract class Message
{
	private final String messageText;
	
	public Message(String messageText)
	{
		this.messageText = messageText;
	}
	
	public String printMessage() { return this.messageText; }
	public abstract boolean isError();
	public abstract boolean isGameOver();
}
