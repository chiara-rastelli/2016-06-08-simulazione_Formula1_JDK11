package it.polito.tdp.formula1.model;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Model m = new Model();
		int anno = m.getAllYears().get(0);
		Circuit c = m.getAllCircuitsByYear(anno).get(0);
		System.out.println(m.getRaceByCircuitAndYear(anno, c));
	}

}
