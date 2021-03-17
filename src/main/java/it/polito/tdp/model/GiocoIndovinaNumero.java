package it.polito.tdp.model;

import java.util.*;
import it.polito.tdp.inputs.*;
import it.polito.tdp.messages.*;

public class GiocoIndovinaNumero
{
	public static GiocoIndovinaNumero singletonInstance;
	private Difficolta difficoltaPartita;
	private int NMAX; 	//numero massimo da poter indovinare
	private int TMAX; 	//numero massimo di tentativi
	private int numeroDaIndovinare;
	
	private Set<Integer> tentativiEffettuati;
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
		
	private GiocoIndovinaNumero()
	{
		this.tentativiEffettuati = new HashSet<>();
	}
	
	public int getTmax() { return this.TMAX; }
	public int getNmax() { return this.NMAX; }
	public int getNumTentativiEffettuati() { return this.tentativiEffettuati.size(); }
	public int getLowerBound() { return this.lowerBound; }
	public int getUpperBound() { return this.upperBound; }
	public void setLowerBound(int newLowerBound) { this.lowerBound = newLowerBound; }
	public void setUpperBound(int newUpperBound) { this.upperBound = newUpperBound; }
	public String printDifficolta() { return this.difficoltaPartita.toString(); }
	
	public void ricominciaPartita(Difficolta difficoltaPartita)
	{
		this.difficoltaPartita = difficoltaPartita;
		this.NMAX = difficoltaPartita.getNMAX();
		this.TMAX = difficoltaPartita.getTMAX();
		this.numeroDaIndovinare = (int)(NMAX * Math.random()) + 1; //generazione numero casuale tra 1 e NMAX
		
		this.tentativiEffettuati.clear();
		this.upperBound = difficoltaPartita.getNMAX();
		this.lowerBound = 1;
	}
	
	public Message doTentativo(String inputUtente)
	{
		InputType inputType = this.checkInput(inputUtente);
		Message message = inputType.getMessage();
		
		if(!inputType.isValid())
			return message;
		
		int tentativo = Integer.parseInt(inputUtente);	
		
		if(this.tentativiEffettuati.contains(tentativo))
		{
			String duplicateAttempt = String.format("\nErrore: numero %d gia' inserito! Prova un altro numero!", tentativo);
			return new ErrorMessage(duplicateAttempt);
		}
		
		this.tentativiEffettuati.add(tentativo);
    	ValidNumberInput validNumber = (ValidNumberInput) inputType;
    	
    	if(!validNumber.isCorrect() && this.getNumTentativiEffettuati() == this.TMAX)
    	{
    		String noMoreAttempts = String.format("\n>>> Tentativi esauriti! HAI PERSO! Il numero corretto era: %d <<<", this.numeroDaIndovinare);
    		return new GameOverMessage(noMoreAttempts);
   		}    		
    	else
    		return message;
	}

	private InputType checkInput(String inputUtente)
    {
		if(inputUtente.length() > 5)
			return new OverflowInput(this.NMAX);
		
    	//controllo formato input
    	if(!inputUtente.matches("[0-9]+")) //controlla se l'input contiene caratteri diversi da cifre numeriche 0-9
    		return new NoNumberInput(inputUtente, this.NMAX);
    	
    	//controllo sul numero in input
    	int tentativo = Integer.parseInt(inputUtente);
    	
    	if(tentativo > NMAX)
    		return new TooHighNumberInput(tentativo, this.NMAX); //numero troppo elevato per il gioco
    	else if(tentativo < 1)
       		return new TooLowNumberInput(tentativo, this.NMAX); //numero troppo basso per il gioco
    	else if(tentativo > this.numeroDaIndovinare)
    		return new TooHighAttempt(this, tentativo);
    	else if (tentativo < this.numeroDaIndovinare)
    		return new TooLowAttempt(this, tentativo);
    	else  //passati tutti i controlli
    		return new VictoryAttempt(tentativo, this.getNumTentativiEffettuati()); 
    }
	
	
	public static GiocoIndovinaNumero instance()
	{
		if(singletonInstance == null)
			singletonInstance = new GiocoIndovinaNumero();
		
		return singletonInstance;
	}
	
}
