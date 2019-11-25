package view.fridge;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Jaakaappi;
import model.JaakaappiDAO;
import model.Rpk;
import model.RpkDAO;
import model.TuoteDAO;

public class FridgeListController implements Initializable{
	
	static TuoteDAO tuote = new TuoteDAO();
	static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	static RpkDAO rpk = new RpkDAO();
	@FXML private TableView<Object> tableView;
	@FXML private TableColumn<Object, Number> fridgeIdColumn;
	@FXML private TableColumn<Jaakaappi, String> fridgeProductColumn;
	@FXML private TableColumn<Jaakaappi, String> fridgeCaloriesColumn;
	@FXML private TableColumn<Jaakaappi, String> fridgeUnitColumn;
	@FXML private TableColumn<Object, Number> fridgeAmountColumn;
	@FXML private TableColumn<Object, Date> fridgeDateColumn;
	@FXML private TextField fridgeMarkId;
	@FXML private TextField fridgeMarkAmount;
	@FXML private ToggleGroup statusSelect;
	@FXML private RadioButton fridgeMarkUsed;
	@FXML private RadioButton fridgeMarkWaste;
	
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
	
	
	public ObservableList<Object> getAllFridges() {
		ArrayList<Object> fridges = jaakaappi.readJaakaapit();
		ObservableList<Object> result = FXCollections.observableArrayList(fridges);
		return result;
	}

	@FXML
	public void fridgeMarkUsedWasted() {
		if (statusSelect.getSelectedToggle().equals(fridgeMarkUsed)) {
			jaakaappi.updateJkMaara(Integer.parseInt(fridgeMarkId.getText()), Double.parseDouble(fridgeMarkAmount.getText()), "Käytetty");
		} else if (statusSelect.getSelectedToggle().equals(fridgeMarkWaste)) {
			jaakaappi.updateJkMaara(Integer.parseInt(fridgeMarkId.getText()), Double.parseDouble(fridgeMarkAmount.getText()), "Hävikki");
		}
		tableView.setItems(getAllFridges());
		
	}
	
	
}
