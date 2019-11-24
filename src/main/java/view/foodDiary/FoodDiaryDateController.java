package view.foodDiary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Rpk;
import model.RpkDAO;
import javafx.scene.control.TableColumn;

public class FoodDiaryDateController implements Initializable{
	static RpkDAO rpk = new RpkDAO();
	Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	@FXML private DatePicker rpkSelectDate;
	@FXML private Button rpkSeachButton;
	@FXML private Label lblDate;
	@FXML private TableView<Object> tableView;
	@FXML private TableColumn<Object, Number> rpkIdColumn;
	@FXML private TableColumn<Rpk, String> rpkProductColumn;
	@FXML private TableColumn<Rpk, String> rpkCaloriesColumn;
	@FXML private TableColumn<Rpk, String> rpkAmountColumn;
	@FXML private TableColumn<Rpk, String> rpkUsageColumn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblDate.setText(String.valueOf(date));
		
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
		
		rpkUsageColumn.setCellValueFactory(new Callback<CellDataFeatures<Rpk, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Rpk, String> c) {
	            return new SimpleStringProperty(c.getValue().getJaakaappi().getTuote_status());                
	        }
		}); 
		
		tableView.setItems(getRpkDate());
	}

	// Event Listener on Button[#rpkSeachButton].onAction
	@FXML
	public void searchRpk(ActionEvent event) {
		this.date = java.sql.Date.valueOf(rpkSelectDate.getValue());
		lblDate.setText(String.valueOf(date));
		tableView.setItems(getRpkDate());
	}
	
	public ObservableList<Object> getRpkDate() {
		ArrayList<Object> rkpt = rpk.readRpkPvm(date);
		ObservableList<Object> result = FXCollections.observableArrayList(rkpt);
		return result;
	}
	
}
