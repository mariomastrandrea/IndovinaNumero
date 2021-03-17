package it.polito.tdp.inputs;

import it.polito.tdp.messages.*;

public class NoNumberInput implements InputType
{ 
	private final String wrongInput;
	private final int nmax;
	
	
	public NoNumberInput(String wrongInput, int nmax)
	{
		this.wrongInput = wrongInput;
		this.nmax = nmax;
	}
	
	@Override
	public boolean isValid() { return false; }  //input errato
	
	@Override
	public Message getMessage()
	{
		String error;
		
		if(wrongInput.isEmpty())
			error = String.format("\n* Errore in input: il campo non puo' essere vuoto!\n  Inserisci un numero da 1 a %d *", nmax);
		else
			error = String.format("\n* Errore in input \"%s\": devi inserire un numero intero da 1 a %d! *", wrongInput, nmax);
				
		return new ErrorMessage(error);
	}
}
