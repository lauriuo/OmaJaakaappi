package view.products;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import model.TuoteDAO;
import javafx.event.ActionEvent;

public class ProductsAddController {
	static TuoteDAO tuote = new TuoteDAO();
	@FXML private TextField addProductName;
	@FXML private TextField addProductUnit;
	@FXML private TextField addProductCalories;

	// Event Listener on Button.onAction
	@FXML
	public void addProductButton() {
		tuote.createTuote(addProductName.getText(), addProductUnit.getText(), Double.parseDouble(addProductCalories.getText()));
	}
}
