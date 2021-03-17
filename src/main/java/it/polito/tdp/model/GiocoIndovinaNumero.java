package it.polito.tdp.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.indovinaNumero.FXMLController;

public class GiocoIndovinaNumero
{
	private final FXMLController viewController;
	private Difficolta difficoltaPartita;
	private int NMAX; //numero massimo da poter indovinare
	private int TMAX; //numero massimo di tentativi
	private int numeroDaIndovinare;
	
	private List<Integer> tentativiEffettuati;
	private int upperBound;
	private int lowerBound;
	
	
	public enum Difficolta 
	{
		FACILE(50,6), MEDIO(100,6), DIFFICILE(400,7), ESTREMO(1000,8);
		
		private int numMaxDaIndovinare;
		private int numMaxTentativi;
		
		private Difficolta(int nmax, int tmax)
		{
			this.numMaxDaIndovinare = nmax;
			this.numMaxTentativi = tmax;
		}
		
		public int getNMAX() { return this.numMaxDaIndovinare; }
		public int getTMAX() { return this.numMaxTentativi; }
	};
	
	
	public GiocoIndovinaNumero(FXMLController viewController)
	{
		this.viewController = viewController;
	}
	
	public int getTmax() { return this.TMAX; }
	public int getNmax() { return this.NMAX; }
	public String getDifficolta() {	return this.difficoltaPartita.toString(); }
	public int getNumTentativiEffettuati() { return this.tentativiEffettuati.size(); }
	public int getLowerBound() { return this.lowerBound; }
	public int getUpperBound() { return this.upperBound; }
	public void setLowerBound(int newLowerBound) { this.lowerBound = newLowerBound; }
	public void setUpperBound(int newUpperBound) { this.upperBound = newUpperBound; }
	
	public void ricominciaPartita(Difficolta difficoltaPartita)
	{
		this.difficoltaPartita = difficoltaPartita;
		this.NMAX = difficoltaPartita.getNMAX();
		this.TMAX = difficoltaPartita.getTMAX();
		this.numeroDaIndovinare = (int)(NMAX * Math.random()) + 1; //generazione numero casuale tra 1 e NMAX
		
		this.tentativiEffettuati = new LinkedList<>();
		this.upperBound = difficoltaPartita.getNMAX();
		this.lowerBound = 1;
	}
	
	public void doTentativo(String inputUtente)
	{
		InputType inputType = this.checkInput(inputUtente);
		if(!inputType.isValid())
			return;
		
		int tentativo = Integer.parseInt(inputUtente);	
		
		if(this.tentativiEffettuati.contains(tentativo))
		{
			this.viewController.appendText(String.format("\nErrore: numero %d gia' inserito! Prova un altro numero!", tentativo));
			return;
		}
		
		this.tentativiEffettuati.add(tentativo);
    	this.viewController.displayTentativiRimasti(this.TMAX - this.tentativiEffettuati.size());
  
    	ValidNumberInput validNumber = (ValidNumberInput) inputType;
    	if(validNumber.isCorrect())
    		return;
    	else if(this.tentativiEffettuati.size() == this.TMAX)
    	{
   			//fine gioco
    		this.viewController.appendText(String.format("\n>>> Tentativi esauriti! HAI PERSO! Il numero corretto era: %d <<<", this.numeroDaIndovinare));
    		this.setInterfacciaFinale();
    		return;
   		}    		
    	this.viewController.displayBounds(this.lowerBound, this.upperBound);
    	if(this.upperBound == this.lowerBound)
    		this.viewController.highlightBounds();
	}

	private InputType checkInput(String inputUtente)
    {
    	//controllo formato input
    	if(!inputUtente.matches("[0-9]+")) //controlla se l'input contiene caratteri diversi da cifre numeriche 0-9
    		return new NoNumberInput(this, inputUtente);
    	
    	//controllo sul numero in input
    	int tentativo = Integer.parseInt(inputUtente);
    	
    	if(tentativo > NMAX)
    		return new TooHighNumberInput(this, tentativo); //numero troppo elevato per il gioco
    	else if(tentativo < 1)
       		return new TooLowNumberInput(this, tentativo); //numero troppo basso per il gioco
    	else if(tentativo > this.numeroDaIndovinare)
    		return new TooHighAttempt(this, tentativo);
    	else if (tentativo < this.numeroDaIndovinare)
    		return new TooLowAttempt(this, tentativo);
    	else  //passati tutti i controlli
    		return new VictoryAttempt(this, tentativo); 
    }
	
	public void appendText(String text)
	{
		this.viewController.appendText(text);
	}

	public void setInterfacciaFinale()
	{
		this.viewController.setDisableHboxTentativo(true);
		this.viewController.resetBoundsLabels();
	}
}
