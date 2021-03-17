package it.polito.tdp.inputs;

import it.polito.tdp.messages.*;

public class TooHighNumberInput implements InputType
{
	private final int tentativo;
	private final int nmax;
	
	//numero troppo elevato per il gioco
	public TooHighNumberInput(int tentativo, int nmax)
	{
		this.tentativo = tentativo;
		this.nmax = nmax;
	}

	@Override
	public boolean isValid() { return false; } //input errato

	@Override
	public Message getMessage()
	{
		String error =
			String.format("\n* Errore: il numero %d e' TROPPO ALTO!\n  Inserisci un numero da 1 a %d *", tentativo, nmax);
		
		return new ErrorMessage(error);
	}
}
