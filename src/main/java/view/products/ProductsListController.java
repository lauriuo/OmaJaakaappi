package view.products;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TuoteDAO;

public class ProductsListController implements Initializable{

	static TuoteDAO tuote = new TuoteDAO();
	@FXML private TableView<Object> tableView;
	@FXML private TableColumn<Object, Number> productsIdColumn;
	@FXML private TableColumn<Object, String> productsNameColumn;
	@FXML private TableColumn<Object, String> productsUnitColumn;
	@FXML private TableColumn<Object, Number> productsCaloriesColumn;
	@FXML private TableColumn<Object, Number> productsSaltColumn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		productsIdColumn.setCellValueFactory(new PropertyValueFactory<Object, Number>("tuote_id"));
		productsNameColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("tuote_nimi"));
		productsUnitColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("tuote_yksikko"));
		productsCaloriesColumn.setCellValueFactory(new PropertyValueFactory<Object, Number>("tuote_kcal"));
		productsSaltColumn.setCellValueFactory(new PropertyValueFactory<Object, Number>("tuote_suola"));
		
		tableView.setItems(getAllProducts());
	}

	public ObservableList<Object> getAllProducts() {
		ArrayList<Object> tuotteet = tuote.readTuotteet();
		ObservableList<Object> products = FXCollections.observableArrayList(tuotteet);
		return products;
	}
	
}