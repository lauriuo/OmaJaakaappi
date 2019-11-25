package view.foodDiary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Rpk;
import model.RpkDAO;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FoodDiaryDeleteController implements Initializable{
	static RpkDAO rpk = new RpkDAO();
	@FXML private TextField rpkSelectId;
	@FXML private TableView<Object> tableView;
	@FXML private TableColumn<Object, Number> rpkIdColumn;
	@FXML private TableColumn<Rpk, String> rpkProductColumn;
	@FXML private TableColumn<Rpk, String> rpkCaloriesColumn;
	@FXML private TableColumn<Rpk, String> rpkAmountColumn;
	@FXML private TableColumn<Object, Date> rpkDateColumn;
	@FXML private TableColumn<Rpk, String> rpkUsageColumn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rpkIdColumn.setCellValueFactory(new PropertyValueFactory<Object, Number>("rpk_id"));
		
		rpkProductColumn.setCellValueFactory(new Callback<CellDataFeatures<Rpk, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Rpk, String> result) {
	            return new SimpleStringProperty(result.getValue().getJaakaappi().getTuote().getTuote_nimi());                
	        }
		}); 
		
		rpkCaloriesColumn.setCellValueFactory(new Callback<CellDataFeatures<Rpk, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Rpk, String> c) {
	            return new SimpleStringProperty(Double.toString(c.getValue().getJaakaappi().getTuote().getTuote_kcal()));                
	        }
		}); 
		
		rpkAmountColumn.setCellValueFactory(new Callback<CellDataFeatures<Rpk, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Rpk, String> c) {
	            return new SimpleStringProperty(Double.toString(c.getValue().getJaakaappi().getTuote_maara()));                
	        }
		}); 
		
		rpkDateColumn.setCellValueFactory(new PropertyValueFactory<Object, Date>("rpk_pvm"));
		
		rpkUsageColumn.setCellValueFactory(new Callback<CellDataFeatures<Rpk, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Rpk, String> c) {
	            return new SimpleStringProperty(c.getValue().getJaakaappi().getTuote_status());                
	        }
		}); 
		
		tableView.setItems(getAllRpk());
	}
	
	public ObservableList<Object> getAllRpk() {
		ArrayList<Object> allRpk = rpk.readRpkt();
		ObservableList<Object> result = FXCollections.observableArrayList(allRpk);
		return result;
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void deleteFromRpk(ActionEvent event) {
		rpk.deleteRpk(Integer.parseInt(rpkSelectId.getText()));
		tableView.setItems(getAllRpk());
	}
}
