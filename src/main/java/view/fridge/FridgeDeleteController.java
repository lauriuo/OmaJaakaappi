package view.fridge;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Jaakaappi;
import model.JaakaappiDAO;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class FridgeDeleteController implements Initializable{
	
	static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	@FXML private TextField fridgeSelectId;
	@FXML private TableView<Object> tableView;
	@FXML private TableColumn<Object, Number> fridgeIdColumn;
	@FXML private TableColumn<Jaakaappi, String> fridgeProductColumn;
	@FXML private TableColumn<Jaakaappi, String> fridgeCaloriesColumn;
	@FXML private TableColumn<Jaakaappi, String> fridgeUnitColumn;
	@FXML private TableColumn<Object, Number> fridgeAmountColumn;
	@FXML private TableColumn<Object, Date> fridgeDateColumn;

	// Event Listener on Button.onAction
	@FXML
	public void deleteFromFridge(ActionEvent event) {
		jaakaappi.deleteJaakaappi(Integer.parseInt(fridgeSelectId.getText()));
		tableView.setItems(getAllFridges());
	}

	public ObservableList<Object> getAllFridges() {
		ArrayList<Object> tuotteet = jaakaappi.readJaakaapit();
		ObservableList<Object> fridges = FXCollections.observableArrayList(tuotteet);
		return fridges;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fridgeIdColumn.setCellValueFactory(new PropertyValueFactory<Object, Number>("jaakaappi_id"));
		
		fridgeProductColumn.setCellValueFactory(new Callback<CellDataFeatures<Jaakaappi, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Jaakaappi, String> result) {
	            return new SimpleStringProperty(result.getValue().getTuote().getTuote_nimi());                
	        }
		});
		
		fridgeCaloriesColumn.setCellValueFactory(new Callback<CellDataFeatures<Jaakaappi, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Jaakaappi, String> result) {
	            return new SimpleStringProperty(Double.toString(result.getValue().getTuote().getTuote_kcal()));                
	        }
		});
		
		fridgeUnitColumn.setCellValueFactory(new Callback<CellDataFeatures<Jaakaappi, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Jaakaappi, String> result) {
	            return new SimpleStringProperty(result.getValue().getTuote().getTuote_yksikko());                
	        }
		});
		
		fridgeAmountColumn.setCellValueFactory(new PropertyValueFactory<Object, Number>("tuote_maara"));
		
		fridgeDateColumn.setCellValueFactory(new PropertyValueFactory<Object, Date>("tuote_pvm"));
		
		tableView.setItems(getAllFridges());
		
		tableView.setItems(getAllFridges());
	}
}
