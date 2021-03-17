package it.polito.tdp.inputs;

import it.polito.tdp.messages.ErrorMessage;
import it.polito.tdp.messages.Message;

public class OverflowInput implements InputType
{
	private final int nmax;
	
	public OverflowInput(int nmax)
	{
		this.nmax = nmax;
	}
	
	@Override
	public boolean isValid() {	return false; }

	@Override
	public Message getMessage()
	{
		String error = String.format("\n* Errore: input troppo corto/lungo!\n  Inserisci un numero da 1 a %d *", nmax);
		return new ErrorMessage(error);
	}

}
