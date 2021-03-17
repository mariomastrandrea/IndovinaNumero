package it.polito.tdp.inputs;
import static java.lang.Integer.min;

import it.polito.tdp.messages.*;
import it.polito.tdp.model.GiocoIndovinaNumero;

public class TooHighAttempt implements ValidNumberInput
{
	private final GiocoIndovinaNumero gioco;
	private final int tentativo;
	
	
	public TooHighAttempt(GiocoIndovinaNumero gioco, int tentativo)
	{
		this.gioco = gioco;
		this.tentativo = tentativo;
	}
	
	@Override
	public boolean isCorrect()
	{
		this.gioco.setUpperBound(min(tentativo-1, this.gioco.getUpperBound()));
		return false;
	}

	@Override
	public boolean isValid() { return true; }

	@Override
	public Message getMessage()
	{
		String higherAttempt = String.format("\nIl numero %d e' TROPPO ALTO!", tentativo);
		return new BadAttemptMessage(higherAttempt);
	}
}
