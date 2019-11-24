package view.products;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Tuote;
import model.TuoteDAO;

public class ProductsEditController implements Initializable{
	static TuoteDAO tuote = new TuoteDAO();
	Tuote editable = new Tuote();
	private String oldName;
	private String oldUnit;
	private String oldCalories;
	@FXML private TextField searchProducts;
	@FXML private Label lblOldName;
	@FXML private Label lblOldUnit;
	@FXML private Label lblOldCalories;
	@FXML private TextField newProductName;
	@FXML private TextField newProductUnit;
	@FXML private TextField newProductCalories;
	@FXML private TableView<Object> tableView;
	@FXML private TableColumn<Object, Number> productsIdColumn;
	@FXML private TableColumn<Object, String> productsNameColumn;
	@FXML private TableColumn<Object, String> productsUnitColumn;
	@FXML private TableColumn<Object, Number> productsCaloriesColumn;

	@FXML
	public void searchButtonAction() {
		editable = tuote.readTuoteNimi(searchProducts.getText());
		this.oldName = editable.getTuote_nimi();
		this.oldUnit = editable.getTuote_yksikko();
		this.oldCalories = String.valueOf(editable.getTuote_kcal());
		lblOldName.setText(oldName);
		lblOldUnit.setText(oldUnit);
		lblOldCalories.setText(oldCalories);
	}
	
	@FXML
	public void updateButtonAction() {
		tuote.updateTuote(oldName, newProductName.getText(), newProductUnit.getText(), Double.parseDouble(newProductCalories.getText()));
		tableView.setItems(getAllProducts());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		productsIdColumn.setCellValueFactory(new PropertyValueFactory<Object, Number>("tuote_id"));
		productsNameColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("tuote_nimi"));
		productsUnitColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("tuote_yksikko"));
		productsCaloriesColumn.setCellValueFactory(new PropertyValueFactory<Object, Number>("tuote_kcal"));
		
		tableView.setItems(getAllProducts());
	}

	public ObservableList<Object> getAllProducts() {
		ArrayList<Object> tuotteet = tuote.readTuotteet();
		ObservableList<Object> products = FXCollections.observableArrayList(tuotteet);
		return products;
	}
}