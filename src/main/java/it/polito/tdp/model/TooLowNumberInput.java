package it.polito.tdp.model;

public class TooLowNumberInput implements InputType
{
	private final GiocoIndovinaNumero gioco;
	private int tentativo;
	
	
	public TooLowNumberInput(GiocoIndovinaNumero gioco, int tentativo)
	{
		this.gioco = gioco;
		this.tentativo = tentativo;
	}
	
	@Override
	public boolean isValid()
	{
		//numero troppo basso per il gioco
		this.gioco.appendText(String.format("\nErrore: il numero %d e' TROPPO BASSO! Inserisci un numero da 1 a %d", 
												tentativo, this.gioco.getNmax()));
		return false; //input errato
	}

}
