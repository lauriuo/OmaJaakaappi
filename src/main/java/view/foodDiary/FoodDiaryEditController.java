package view.foodDiary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Jaakaappi;
import model.JaakaappiDAO;
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

import javafx.scene.control.Label;

import javafx.scene.control.RadioButton;

import javafx.scene.control.DatePicker;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class FoodDiaryEditController implements Initializable {
	static RpkDAO rpk = new RpkDAO();
	static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	private Rpk editable = new Rpk();
	private String productName;
	private String oldAmount;
	private String oldDate;
	private String oldUsage;
	private int editRpkId;
	private Jaakaappi editableJk;
	@FXML private TextField searchRpk;
	@FXML private Label lblProduct;
	@FXML private Label lblOldAmount;
	@FXML private Label lblOldDate;
	@FXML private Label lblOldUsage;
	@FXML private TextField rpkNewAmount;
	@FXML private DatePicker rpkNewDate;
	@FXML private RadioButton rpkSelectUsed;
	@FXML private ToggleGroup newStatusSelect;
	@FXML private RadioButton rpkSelectWaste;
	@FXML private TableView<Object> tableView;
	@FXML private TableColumn<Object, Number> rpkIdColumn;
	@FXML private TableColumn<Rpk, String> rpkProductColumn;
	@FXML private TableColumn<Rpk, String> rpkCaloriesColumn;
	@FXML private TableColumn<Rpk, String> rpkSaltColumn;
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
		
		rpkSaltColumn.setCellValueFactory(new Callback<CellDataFeatures<Rpk, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Rpk, String> c) {
	            return new SimpleStringProperty(Double.toString(c.getValue().getJaakaappi().getTuote().getTuote_suola()));                
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
	public void searchRpkAction(ActionEvent event) {
		this.editRpkId = Integer.parseInt(searchRpk.getText());
		this.editable = rpk.readRpkId(editRpkId);
		this.editableJk = editable.getJaakaappi();
		this.oldAmount = String.valueOf(editable.getJaakaappi().getTuote_maara());
		this.oldDate = String.valueOf(editable.getRpk_pvm());
		this.oldUsage = editable.getJaakaappi().getTuote_status();
		this.productName = editable.getJaakaappi().getTuote().getTuote_nimi();
		this.lblProduct.setText(productName);
		this.lblOldAmount.setText(oldAmount);
		this.lblOldDate.setText(oldDate);
		this.lblOldUsage.setText(oldUsage);
		
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void rpkUpdateAction(ActionEvent event) {
		Date date = java.sql.Date.valueOf(rpkNewDate.getValue());
		if (newStatusSelect.getSelectedToggle().equals(rpkSelectUsed)) {
			jaakaappi.updateJaakaappi(editableJk.getJaakaappi_id(), editableJk.getTuote_pvm(), Double.parseDouble(rpkNewAmount.getText()), "Käytetty");
		} else if (newStatusSelect.getSelectedToggle().equals(rpkSelectWaste)) {
			jaakaappi.updateJaakaappi(editableJk.getJaakaappi_id(), editableJk.getTuote_pvm(), Double.parseDouble(rpkNewAmount.getText()), "Hävikki");
		}
		rpk.updateRpk(editRpkId, date);
		tableView.setItems(getAllRpk());
	}
}
