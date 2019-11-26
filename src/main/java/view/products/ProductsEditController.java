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
	private String oldSalt;
	@FXML private TextField searchProducts;
	@FXML private Label lblOldName;
	@FXML private Label lblOldUnit;
	@FXML private Label lblOldCalories;
	@FXML private Label lblOldSalt;
	@FXML private TextField newProductName;
	@FXML private TextField newProductUnit;
	@FXML private TextField newProductCalories;
	@FXML private TextField newProductSalt;
	@FXML private TableView<Object> tableView;
	@FXML private TableColumn<Object, Number> productsIdColumn;
	@FXML private TableColumn<Object, String> productsNameColumn;
	@FXML private TableColumn<Object, String> productsUnitColumn;
	@FXML private TableColumn<Object, Number> productsCaloriesColumn;
	@FXML private TableColumn<Object, Number> productsSaltColumn;

	@FXML
	public void searchButtonAction() {
		editable = tuote.readTuoteNimi(searchProducts.getText());
		this.oldName = editable.getTuote_nimi();
		this.oldUnit = editable.getTuote_yksikko();
		this.oldCalories = String.valueOf(editable.getTuote_kcal());
		this.oldSalt = String.valueOf(editable.getTuote_suola());
		lblOldName.setText(oldName);
		lblOldUnit.setText(oldUnit);
		lblOldCalories.setText(oldCalories);
		lblOldSalt.setText(oldSalt);
	}
	
	@FXML
	public void updateButtonAction() {
		tuote.updateTuote(oldName, newProductName.getText(), newProductUnit.getText(), Double.parseDouble(newProductCalories.getText()), Double.parseDouble(newProductSalt.getText()));
		tableView.setItems(getAllProducts());
	}

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