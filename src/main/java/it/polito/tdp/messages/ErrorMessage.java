package it.polito.tdp.messages;

public class ErrorMessage extends Message
{
	public ErrorMessage(String errorText) { super(errorText); }
	
	@Override
	public boolean isError() { return true; }

	@Override
	public boolean isGameOver() { return false; }
}
