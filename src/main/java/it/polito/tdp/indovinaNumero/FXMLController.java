package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.messages.Message;
import it.polito.tdp.model.GiocoIndovinaNumero;
import it.polito.tdp.model.GiocoIndovinaNumero.Difficolta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class FXMLController 
{
	//nodi interfaccia grafica
	@FXML
	private ResourceBundle resources;
	    
	@FXML
	private URL location;
	
	@FXML
	private Button buttonNuovaPartita;
	
	@FXML
    private Label labelTentativiRimasti;
	
	@FXML
    private ProgressBar progressBarTentativi;
    
	@FXML
	private MenuButton menuDifficolta;

	@FXML
	private MenuItem difficoltaFacile;

	@FXML
	private MenuItem difficoltaMedio;

	@FXML
	private MenuItem difficoltaDifficile;

	@FXML
	private MenuItem difficoltaEstremo;

	@FXML
	private CheckBox modalitaAssistitaCheckBox;
	
	@FXML
    private HBox hbox_tentativo;
	
    @FXML
    private TextField txtTentativoUtente;

    @FXML
    private Button buttonProva;
    
    @FXML
    private Label minValueLabel;

    @FXML
    private Label maxValueLabel;
    
    @FXML
    private TextArea textRisultato;
    
    /********************************/
    
    private GiocoIndovinaNumero giocoModel;
    private Difficolta difficoltaNuovaPartita = Difficolta.MEDIO;	 //default
    
    public void setModel(GiocoIndovinaNumero newModel) { this.giocoModel = newModel; }
    
    @FXML
    void selectModalitaAssistita(ActionEvent event) 
    {
    	if(this.modalitaAssistitaCheckBox.isSelected())
    		this.setBoundsVisibility(true);
    	else
    		this.setBoundsVisibility(false);
    }

    @FXML
    void setDifficoltaDifficile(ActionEvent event) 
    {
    	this.menuDifficolta.setText("Difficile");
    	this.difficoltaNuovaPartita = Difficolta.DIFFICILE;
    }

    @FXML
    void setDifficoltaEstremo(ActionEvent event) 
    {
    	this.menuDifficolta.setText("Estremo");
    	this.difficoltaNuovaPartita = Difficolta.ESTREMO;
    }

    @FXML
    void setDifficoltaFacile(ActionEvent event) 
    {
    	this.menuDifficolta.setText("Facile");
    	this.difficoltaNuovaPartita = Difficolta.FACILE;
    }

    @FXML
    void setDifficoltaMedio(ActionEvent event) 
    {
    	this.menuDifficolta.setText("Medio");
    	this.difficoltaNuovaPartita = Difficolta.MEDIO;
    }
    
    @FXML
    void doNuovaPartita(ActionEvent event) 
    {    	
    	this.giocoModel.ricominciaPartita(this.difficoltaNuovaPartita);
    	
    	//gestione interfaccia iniziale
    	this.labelTentativiRimasti.setText(String.format("Tentativi rimasti: %d", this.giocoModel.getTmax()));
    	this.labelTentativiRimasti.setTextFill(Color.BLACK);
    	this.progressBarTentativi.setProgress(1.0);
    	this.progressBarTentativi.setEffect(new ColorAdjust(-0.3,0,0,0));
    	this.hbox_tentativo.setDisable(false);
    	this.textRisultato.setText(String.format("Difficolta': %s\n+++ Indovina il numero intero da 1 a %d +++", 
    												this.giocoModel.printDifficolta() ,this.giocoModel.getNmax()));
    	this.txtTentativoUtente.clear();;
    	
    	this.minValueLabel.setText(String.format("min: %d", this.giocoModel.getLowerBound()));
    	this.maxValueLabel.setText(String.format("max: %d", this.giocoModel.getUpperBound()));
    	this.minValueLabel.setTextFill(Color.BLACK);
		this.maxValueLabel.setTextFill(Color.BLACK);
    }

    @FXML
    void doTentativo(ActionEvent event) 
    {
    	String inputUtente = this.txtTentativoUtente.getText().trim();
    	Message messageToShow = this.giocoModel.doTentativo(inputUtente);
    	String messageText = messageToShow.printMessage();
		this.textRisultato.appendText(messageText);

    	if(!messageToShow.isError())
    	{
        	this.displayTentativiRimasti(this.giocoModel.getTmax() - this.giocoModel.getNumTentativiEffettuati());
        	
        	if(messageToShow.isGameOver())
        		this.setFinalWiew();
        	else
        	{
        		int lowerBound = this.giocoModel.getLowerBound();
        		int upperBound = this.giocoModel.getUpperBound();
        		this.updateBounds(lowerBound, upperBound);
            	if(lowerBound == upperBound)
            		this.highlightBounds();
        	}
    	}
    }
    
    //private methods
    private void displayTentativiRimasti(int tentativiRimasti)
    {
    	this.labelTentativiRimasti.setText(String.format("Tentativi rimasti: %d",tentativiRimasti));
    	if(tentativiRimasti == 1)
    		this.labelTentativiRimasti.setTextFill(Color.RED);
    		  
    	double percentage = (double)tentativiRimasti / this.giocoModel.getTmax();
    	this.progressBarTentativi.setProgress(percentage);
    	//change color
    	double colorPercentage = (double)(tentativiRimasti - 1.0)  / this.giocoModel.getTmax();
    	this.progressBarTentativi.setEffect(new ColorAdjust(-1.0 + 0.75*colorPercentage, 0, 0, 0));
    }

	private void updateBounds(int lowerBound, int upperBound)
	{
		this.minValueLabel.setText(String.format("min: %d",lowerBound));
		this.maxValueLabel.setText(String.format("max: %d", upperBound));
	}

	private void highlightBounds()
	{
		this.minValueLabel.setTextFill(Color.LIMEGREEN);
		this.maxValueLabel.setTextFill(Color.LIMEGREEN);
	}
	
	private void setFinalWiew()
	{
		this.hbox_tentativo.setDisable(true);
		this.minValueLabel.setText("min:     ");
		this.maxValueLabel.setText("max:     ");
	}
	
	private void setBoundsVisibility(boolean bool)
	{
		this.minValueLabel.setVisible(bool);
		this.maxValueLabel.setVisible(bool);
	}
	
	@FXML
    void initialize() 
    {
        assert buttonNuovaPartita != null : "fx:id=\"buttonNuovaPartita\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert labelTentativiRimasti != null : "fx:id=\"labelTentativiRimasti\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert progressBarTentativi != null : "fx:id=\"progressBarTentativi\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert menuDifficolta != null : "fx:id=\"menuDifficolta\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert difficoltaFacile != null : "fx:id=\"difficoltaFacile\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert difficoltaMedio != null : "fx:id=\"difficoltaMedio\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert difficoltaDifficile != null : "fx:id=\"difficoltaDifficile\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert difficoltaEstremo != null : "fx:id=\"difficoltaEstremo\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert modalitaAssistitaCheckBox != null : "fx:id=\"modalitaAssistitaCheckBox\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert hbox_tentativo != null : "fx:id=\"hbox_tentativo\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert buttonProva != null : "fx:id=\"buttonProva\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert minValueLabel != null : "fx:id=\"minValueLabel\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert maxValueLabel != null : "fx:id=\"maxValueLabel\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
        assert textRisultato != null : "fx:id=\"textRisultato\" was not injected: check your FXML file 'Scene_indovinaNumero.fxml'.";
    }
}
