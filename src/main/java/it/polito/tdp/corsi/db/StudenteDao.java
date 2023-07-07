package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Studente;

public class StudenteDao {

	public List<Studente> getIscrittiCorso(String codins){
		
		List<Studente> listaStudenti = new ArrayList<Studente>();
		
		String querySql ="SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM iscrizione i, studente s "
				+ "WHERE s.matricola=i.matricola AND i.codins=?";
			
		try {
			Connection conn = ConnectDb.getConnection();
			PreparedStatement st = conn.prepareStatement(querySql);
			st.setString(1, codins);
			
			//manda query al database e ce la rimanda come querySql
			ResultSet rs= st.executeQuery(); 
			
			while(rs.next()) { //punta riga successiva(True -False se c'è o no)
				//attenzione a mecciare il nome "" di quello nel database!
				//prendo i valori della query e creo il mio nuovo studente
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome")
						, rs.getString("nome"), rs.getString("CDS"));
				listaStudenti.add(s);
			}
			rs.close();
			conn.close();
			return listaStudenti;
		} catch (SQLException e) {
			System.out.println("Errore connessione al Database");
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Integer> getStudenteByCds(String codins){
		
		 Map<String,Integer> mappaCDS = new  HashMap<String,Integer>();
		
		String querySql ="SELECT s.CDS, COUNT(*) AS n "
				+ "FROM iscrizione i, studente s "
				+ "WHERE s.matricola=i.matricola AND i.codins=? "
				+ "GROUP BY s.CDS";
			
		try {
			Connection conn = ConnectDb.getConnection();
			PreparedStatement st = conn.prepareStatement(querySql);
			st.setString(1, codins);
			
			//manda query al database e ce la rimanda come querySql
			ResultSet rs= st.executeQuery(); 
			
			while(rs.next()) { //punta riga successiva(True -False se c'è o no)
				//attenzione a mecciare il nome "" di quello nel database!
				//prendo i valori della query e creo il mio nuovo studente
				
				/*
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome")
						, rs.getString("nome"), rs.getString("CDS"));
						*/
				String cds = rs.getString("CDS");
				int numero = rs.getInt("n");
				mappaCDS.put(cds, numero);
			}
			rs.close();
			conn.close();
			return mappaCDS;
		} catch (SQLException e) {
			System.out.println("Errore connessione al Database");
			e.printStackTrace();
			return null;
		}
		
		
	}
	
		
		
		
		
}
