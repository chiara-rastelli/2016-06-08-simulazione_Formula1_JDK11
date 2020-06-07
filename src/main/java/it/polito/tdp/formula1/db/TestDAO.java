package it.polito.tdp.formula1.db;

import java.util.List;

import it.polito.tdp.formula1.model.Season;

public class TestDAO {
	
	public static void main(String[] args) {
		F1DAO dao = new F1DAO() ;
		
		List<Season> seasons = dao.getAllSeasons() ;
		System.out.println(seasons);
	}

}
