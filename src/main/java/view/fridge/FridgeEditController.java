package view.fridge;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Jaakaappi;
import model.JaakaappiDAO;
import model.TuoteDAO;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.DatePicker;

public class FridgeEditController implements Initializable{
	
	static TuoteDAO tuote = new TuoteDAO();
	static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	Jaakaappi editable = new Jaakaappi();
	private String productName;
	private String oldAmount;
	private String oldDate;
	private int editId;
	@FXML private TableView<Object> tableView;
	@FXML private TextField searchFridge;
	@FXML private Label lblFridgeProduct;
	@FXML private Label lblOldAmount;
	@FXML private Label lblOldDate;
	@FXML private TextField newFridgeAmount;
	@FXML private DatePicker newFridgeDate;
	@FXML private TableColumn<Object, Number> fridgeIdColumn;
	@FXML private TableColumn<Jaakaappi, String> fridgeProductColumn;
	@FXML private TableColumn<Jaakaappi, String> fridgeCaloriesColumn;
	@FXML private TableColumn<Jaakaappi, String> fridgeUnitColumn;
	@FXML private TableColumn<Object, Number> fridgeAmountColumn;
	@FXML private TableColumn<Object, Date> fridgeDateColumn;

	// Event Listener on Button.onAction
	@FXML
	public void searchFridgeAction(ActionEvent event) {
		this.editId = Integer.parseInt(searchFridge.getText());
		editable = jaakaappi.readJaakaappiId(editId);
		this.productName = editable.getTuote().getTuote_nimi();
		this.oldAmount = String.valueOf(editable.getTuote_maara());
		this.oldDate = String.valueOf(editable.getTuote_pvm());
		this.editId = Integer.parseInt(searchFridge.getText());
		lblFridgeProduct.setText(productName);
		lblOldAmount.setText(oldAmount);
		lblOldDate.setText(oldDate);
		
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void updateFridgeAction(ActionEvent event) {
		Date date = java.sql.Date.valueOf(newFridgeDate.getValue());
		String status = "Käytettävissä";
		jaakaappi.updateJaakaappi(editId, date, Double.parseDouble(newFridgeAmount.getText()), status);
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
	}
}
