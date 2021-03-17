package it.polito.tdp.model;

public class NoNumberInput implements InputType
{
	private final GiocoIndovinaNumero gioco;
	private final String wrongInput;
	
	
	public NoNumberInput(GiocoIndovinaNumero gioco, String wrongInput)
	{
		this.gioco = gioco;
		this.wrongInput = wrongInput;
	}
	
	@Override
	public boolean isValid()
	{
		this.gioco.appendText(String.format("\nErrore in input \"%s\": devi inserire un numero intero da 1 a %d!", 
												wrongInput, this.gioco.getNmax()));
		return false;  //input errato
	}
}
