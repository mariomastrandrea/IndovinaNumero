package it.polito.tdp.inputs;

import it.polito.tdp.messages.*;

public class TooLowNumberInput implements InputType
{
	private final int tentativo;
	private final int nmax;
	
	//numero troppo basso per il gioco
	public TooLowNumberInput(int tentativo, int nmax)
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
			String.format("\n* Errore: il numero %d e' TROPPO BASSO!\n  Inserisci un numero da 1 a %d *", tentativo, nmax);
		
		return new ErrorMessage(error);
	}

}
