package it.polito.tdp.model;

public class TooHighNumberInput implements InputType
{
	private final GiocoIndovinaNumero gioco;
	private final int tentativo;
	
	
	public TooHighNumberInput(GiocoIndovinaNumero gioco, int tentativo)
	{
		this.gioco = gioco;
		this.tentativo = tentativo;
	}

	@Override
	public boolean isValid()
	{
		//numero troppo elevato per il gioco
		this.gioco.appendText(String.format("\nErrore: il numero %d e' TROPPO ALTO! Inserisci un numero da 1 a %d", 
												tentativo, this.gioco.getNmax()));
		return false; //input errato
	}
}
