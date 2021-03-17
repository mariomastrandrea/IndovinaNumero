package it.polito.tdp.model;
import static java.lang.Integer.max;

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
		this.gioco.appendText(String.format("\nIl numero %d e' TROPPO BASSO!", tentativo));
		this.gioco.setLowerBound(max(this.tentativo+1, this.gioco.getLowerBound()));
		return false;
	}

	@Override
	public boolean isValid() 
	{
		return true;
	}
	
}
