/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	//leggo valore casella testo
    	String input = txtPeriodo.getText();
    	int inputNum=0;
    	//vedere se input è un intero
    	try {
    		//metodo vede se è un intero
    		inputNum=Integer.parseInt(input);//può avere eccezione
    	} catch (NumberFormatException e){
    		txtRisultato.setText("Inserito valore non intero");
    		return;
    	}
    	
    	if(inputNum<1 || inputNum>2) {
    		txtRisultato.setText("Inserito valore diverso da 1 o 2");
    		return;
    	}
    	
    	List<Corso> risultati = new ArrayList<Corso>();
    	risultati=model.getCorsoByPeriodo(inputNum);
    	txtRisultato.clear();
    	txtRisultato.setText("Ho trovato i seguenti corsi:\n");
    	
    	for(Corso c : risultati) {
    		txtRisultato.appendText(""+c+"\n");
    	}
    	
    }

    @FXML
    void numeroStudenti(ActionEvent event) {
    	
    	String input = txtPeriodo.getText();
    	int inputNum=0;
    	//vedere se input è un intero
    	try {
    		//metodo vede se è un intero
    		inputNum=Integer.parseInt(input);//può avere eccezione
    	} catch (NumberFormatException e){
    		txtRisultato.setText("Inserito valore non intero");
    		return;
    	}
    	
    	if(inputNum<1 || inputNum>2) {
    		txtRisultato.setText("Inserito valore diverso da 1 o 2");
    		return;
    	}
    	
    	Map<Corso, Integer> risultato = new HashMap<Corso, Integer>();
    	risultato= this.model.getNumStudentiByCorso(inputNum);
    	this.txtRisultato.clear();
    	
    	for(Corso c : risultato.keySet()) { //per ogni Key
    		txtRisultato.appendText(""+c.getNome()+": "+risultato.get(c)+"\n");
    	}
    }

    @FXML
    void stampaDivisione(ActionEvent event) {

    	String input=txtCorso.getText();
    	
    	//controllo stringa non vuota
    	if(input.isEmpty()) {
    		txtRisultato.setText("Inserire codice di un corso");
    		return;
    	}
    	
    	Map<String,Integer> risultato = new HashMap<String,Integer>() ;
    	risultato = this.model.getStudenteByCds(input);
    	txtRisultato.clear();
    	
    	for(String cds : risultato.keySet()) { //per ogni Key
    		txtRisultato.appendText(""+cds+" "+risultato.get(cds)+"\n");
    	}
    	
    }

    @FXML
    void stampaStudenti(ActionEvent event) {

    	String input=txtCorso.getText();
    	
    	//controllo stringa non vuota
    	if(input.isEmpty()) {
    		txtRisultato.setText("Inserire codice di un corso");
    		return;
    	}
    	
    	List<Studente> risultato = new ArrayList<Studente>();
    	risultato = this.model.getIscrittiCorso(input);
    	txtRisultato.clear();
    	
    	for(Studente s : risultato) { 
    		txtRisultato.appendText(""+s.getMatricola()+"-"+s.getCognome()+"-"+s.getNome()+"-"+s.getCDS()+"\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
}