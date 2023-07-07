package it.polito.tdp.corsi.db;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.corsi.model.Corso;

public class testDao {

	public static void main(String[] args) {
		
		CorsoDao corso  = new CorsoDao();
		List<Corso> result = new ArrayList<>();
		
		result=corso.getCorsiByPeriodo(2);
		
		for(Corso c :result) {
			System.out.println(c);
		}
		
	}

}


//NON UTILE PER LA RISOLUZIONE DEL COMPITO