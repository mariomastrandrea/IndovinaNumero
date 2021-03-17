package it.polito.tdp.model;

public class VictoryAttempt implements ValidNumberInput
{
	private final GiocoIndovinaNumero gioco;
	private final int tentativo;
	private final int tentativiEffettuati;
	
	
	public VictoryAttempt(GiocoIndovinaNumero gioco, int tentativo)
	{
		this.gioco = gioco;
		this.tentativo = tentativo;
		this.tentativiEffettuati = this.gioco.getNumTentativiEffettuati();
	}

	@Override
	public boolean isCorrect()
	{
		String tentativ_ = (tentativiEffettuati == 1)? "tentativo" : "tentativi";
		this.gioco.appendText(String.format("\n*** Hai vinto con %d %s! Numero corretto: %d ***", tentativiEffettuati, tentativ_, this.tentativo));
		this.gioco.setInterfacciaFinale();
		return true;  //tentativo CORRETTO!
	}

	@Override
	public boolean isValid() 
	{
		return true;
	}
}
