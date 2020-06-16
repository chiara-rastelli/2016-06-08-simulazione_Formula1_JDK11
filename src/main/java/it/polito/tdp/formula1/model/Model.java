package it.polito.tdp.formula1.model;

import java.util.List;

import it.polito.tdp.formula1.db.F1DAO;

public class Model {

	F1DAO db;
	
	public Model() {
		this.db = new F1DAO();
	}
	
	public List<Integer> getAllYears(){
		return this.db.getAllYears();
	}
	
	public List<Circuit> getAllCircuitsByYear(Integer year){
		return this.db.getAllCircuitsByYear(year);
	}
	
	public List<Driver> listDriversByRace(Integer year, Circuit c){
		return this.db.getAllDriversByRace(year, c);
	}
	
	public Race getRaceByCircuitAndYear(Integer year, Circuit c) {
		return null;
	}
	
}
