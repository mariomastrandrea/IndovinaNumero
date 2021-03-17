package it.polito.tdp.inputs;

import it.polito.tdp.messages.*;

public class VictoryAttempt implements ValidNumberInput
{
	private final int tentativo;
	private final int tentativiEffettuati;
	
	
	public VictoryAttempt(int tentativo, int tentativiEffettuati)
	{
		this.tentativo = tentativo;
		this.tentativiEffettuati = tentativiEffettuati;
	}

	@Override
	public boolean isCorrect() { return true; }  //tentativo CORRETTO!

	@Override
	public boolean isValid()  {	return true; }

	@Override
	public Message getMessage()
	{
		char tentativ_ = (tentativiEffettuati == 1)? 'o' : 'i';
		String victory = String.format("\n*** Hai vinto con %d tentativ%c! Numero corretto: %d ***", 
															tentativiEffettuati, tentativ_, tentativo);
		return new GameOverMessage(victory);
	}
}
