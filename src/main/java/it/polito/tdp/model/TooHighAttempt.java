package it.polito.tdp.model;
import static java.lang.Integer.min;

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
		this.gioco.appendText(String.format("\nIl numero %d e' TROPPO ALTO!", tentativo));
		this.gioco.setUpperBound(min(tentativo-1, this.gioco.getUpperBound()));
		return false;
	}

	@Override
	public boolean isValid() /*******/
	{
		return true;
	}
}
