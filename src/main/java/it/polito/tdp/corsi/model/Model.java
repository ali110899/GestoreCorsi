package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDao;
import it.polito.tdp.corsi.db.StudenteDao;

public class Model {
	
	private CorsoDao corso;
	private StudenteDao studente;
	
	//metodo modello per prendere dati dal DAO
	//nell'FXMLLoader utilizzo i metodi del modello Model
	public Model() {
		this.corso = new CorsoDao(); //assegno a corso un nuovo valore
		this.studente = new StudenteDao();
	}
	
	public List<Corso> getCorsoByPeriodo(int periodo) {
		//interroga il Dao per ricevere la lista corsi
		return this.corso.getCorsiByPeriodo(periodo);
		
	}
	
	public Map<Corso, Integer> getNumStudentiByCorso(int periodo) {	
		//interroga il Dao per ricevere la lista corsi
		return this.corso.getNumStudentiByCorso(periodo);
		
	}
	
	
	public List<Studente> getIscrittiCorso(String codins) {
		//interroga il Dao per ricevere la lista corsi
		return this.studente.getIscrittiCorso(codins);
		
	}
	
	public Map<String, Integer> getStudenteByCds(String codins) {
		//interroga il Dao per ricevere la lista corsi
		return this.studente.getStudenteByCds(codins);
		
	}
	
}
