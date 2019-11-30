package view.fridge;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.JaakaappiDAO;
import model.TuoteDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;

public class FridgeAddController implements Initializable{
	static TuoteDAO tuote = new TuoteDAO();
	static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	@FXML private BorderPane fridgeAddBorderpane;
	@FXML private TextField addFridgeName;
	@FXML private TextField addFridgeQuantity;
	@FXML private DatePicker addFridgeDate;

	// Event Listener on Button.onAction
	@FXML
	public void addFridgeButton(ActionEvent event) {
		int product_id = tuote.readTuoteNimi(addFridgeName.getText()).getTuote_id();
		String status = "Käytettävissä";
		Date date = java.sql.Date.valueOf(addFridgeDate.getValue());
		jaakaappi.createJaakaappi(date, Double.parseDouble(addFridgeQuantity.getText()), status, product_id);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Parent content = null;
		try {
			Locale locale = new Locale("fi", "FI");
			ResourceBundle bundle = ResourceBundle.getBundle("TextResources", locale);
			content = FXMLLoader.load(getClass().getResource("/view/products/ProductsList.fxml"), bundle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fridgeAddBorderpane.setBottom(content);
	}

	public ObservableList<Object> getAllProducts() {
		ArrayList<Object> tuotteet =tuote.readTuotteet();
		ObservableList<Object> products = FXCollections.observableArrayList(tuotteet);
		return products;
	}
	
	@FXML
	public void productsAdd(ActionEvent event) {
		Parent content = null;
		try {
			Locale locale = new Locale("fi", "FI");
			ResourceBundle bundle = ResourceBundle.getBundle("TextResources", locale);
			content = FXMLLoader.load(getClass().getResource("/view/products/ProductsAdd.fxml"), bundle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fridgeAddBorderpane.setBottom(content);
	}
	
	@FXML
	public void productsList(ActionEvent event) {
		Parent content = null;
		try {
			Locale locale = new Locale("fi", "FI");
			ResourceBundle bundle = ResourceBundle.getBundle("TextResources", locale);
			content = FXMLLoader.load(getClass().getResource("/view/products/ProductsList.fxml"), bundle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fridgeAddBorderpane.setBottom(content);
	}
	
}