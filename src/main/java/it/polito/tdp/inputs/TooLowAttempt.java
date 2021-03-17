package it.polito.tdp.inputs;
import static java.lang.Integer.max;

import it.polito.tdp.messages.*;
import it.polito.tdp.model.GiocoIndovinaNumero;

public class TooLowAttempt implements ValidNumberInput
{
	private final GiocoIndovinaNumero gioco;
	private final int tentativo;
		
	
	public TooLowAttempt(GiocoIndovinaNumero gioco, int tentativo)
	{
		this.gioco = gioco;
		this.tentativo = tentativo;
	}
	
	@Override
	public boolean isCorrect()
	{
		this.gioco.setLowerBound(max(this.tentativo+1, this.gioco.getLowerBound()));
		return false;
	}

	@Override
	public boolean isValid() { return true;	}
	
	@Override
	public Message getMessage()
	{
		String lowerAttempt = String.format("\nIl numero %d e' TROPPO BASSO!", tentativo);
		return new BadAttemptMessage(lowerAttempt);
	}
	
}
