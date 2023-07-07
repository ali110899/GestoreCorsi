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

public class CorsoDao {

	public List<Corso> getCorsiByPeriodo(int valore) {
		
		List<Corso> listaVoti = new ArrayList<Corso>();
		
		String querySql ="SELECT* " + "FROM corso "+ "WHERE pd=?";
			
		try {
			Connection conn = ConnectDb.getConnection();
			PreparedStatement st = conn.prepareStatement(querySql);
			st.setInt(1, valore);
			
			//manda query al database e ce la rimanda come querySql
			ResultSet rs= st.executeQuery(); 
			
			while(rs.next()) { //punta riga successiva(True -False se c'è o no)
				//attenzione a mecciare il nome "" di quello nel database!
				//prendo i valori della query e creo il mio nuovo Corso
				Corso c = new Corso(rs.getString("codins"), rs.getString("nome")
						, rs.getInt("crediti"), rs.getInt("pd"));
				listaVoti.add(c);
			}
			rs.close();
			conn.close();
			return listaVoti;
		} catch (SQLException e) {
			System.out.println("Errore in Corso DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<Corso, Integer> getNumStudentiByCorso(int valore) {
		
		Map<Corso, Integer> mappaStudentiCorso = new HashMap<Corso, Integer>();
		
		String querySql = "SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS n "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins = i.codins and c.pd=? "
				+ "GROUP BY c.codins, c.crediti, c.nome, c.pd";
			
		try {
			Connection conn = ConnectDb.getConnection();
			PreparedStatement st = conn.prepareStatement(querySql);
			st.setInt(1, valore);
			
			//manda query al database e ce la rimanda come querySql
			ResultSet rs= st.executeQuery(); 
			
			while(rs.next()) { //punta riga successiva(True -False se c'è o no)
				//attenzione a mecciare il nome "" di quello nel database!
				//prendo i valori della query e creo il mio nuovo Corso
				Corso c = new Corso(rs.getString("codins"), rs.getString("nome")
						, rs.getInt("crediti"), rs.getInt("pd"));
				//prendo il valore della query e associo il mio count a n
				//Integer numero = rs.getInt("n");
				
				mappaStudentiCorso.put(c, rs.getInt("n"));
			}
			rs.close();
			conn.close();
			return mappaStudentiCorso;
		} catch (SQLException e) {
			System.out.println("Errore in Corso DAO");
			e.printStackTrace();
			return null;
		}
	}
	
	
}
