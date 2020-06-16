package it.polito.tdp.formula1;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.formula1.model.Circuit;
import it.polito.tdp.formula1.model.Driver;
import it.polito.tdp.formula1.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Circuit> boxCircuit;

    @FXML
    private ComboBox<?> boxDriver;

    @FXML
    private ComboBox<Integer> boxSeason;

    @FXML
    private TextArea txtResult;

    @FXML
    void doFantaGara(ActionEvent event) {

    }

    @FXML
    void doInfoGara(ActionEvent event) {
    	this.txtResult.clear();
    	if (this.boxCircuit.getValue()==null||this.boxSeason.getValue()==null) {
    		this.txtResult.setText("Devi prima selezionare un anno, poi un circuito e poi cliccare!\n");
    		return;
    	}
    	this.txtResult.appendText("Ecco i piloti che hanno partecipato a questa gara:\n");
    	for(Driver d : this.model.listDriversByRace(this.boxSeason.getValue(), this.boxCircuit.getValue())) {
    		this.txtResult.appendText(d.toString()+"\n");
    	}
    	this.txtResult.appendText("Ecco le informazioni sulla gara:\n");
    	this.txtResult.appendText("DEVI ASSOLUTAMENTE RIVEDERE QUESTA PARTE!!!");
    //	this.txtResult.appendText(this.model.getRaceByCircuitAndYear(this.boxSeason.getValue(), this.boxCircuit.getValue()).toString());
    }
    
    @FXML
    void onChange(ActionEvent event) {
    	Integer anno = this.boxSeason.getValue();
    	if(anno!=null)
    		this.boxCircuit.getItems().addAll(this.model.getAllCircuitsByYear(anno));
    }

    @FXML
    void initialize() {
        assert boxCircuit != null : "fx:id=\"boxCircuit\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert boxDriver != null : "fx:id=\"boxDriver\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert boxSeason != null : "fx:id=\"boxSeason\" was not injected: check your FXML file 'Formula1.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Formula1.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.boxSeason.getItems().addAll(this.model.getAllYears());
		
	}
}
