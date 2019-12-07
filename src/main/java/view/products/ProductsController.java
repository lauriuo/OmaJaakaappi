package view.products;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import view.main.Language;

public class ProductsController implements Initializable{
	private Language language = Language.getInstance();
	@FXML
	private BorderPane productsBorderpane;
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void productsList(MouseEvent event) {
		loadContent("ProductsList");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void productsAdd(MouseEvent event) {
		loadContent("ProductsAdd");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void productsEdit(MouseEvent event) {
		loadContent("ProductsEdit");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void productsDelete(MouseEvent event) {
		loadContent("ProductsDelete");
	}
	
	private void loadContent(String ui) {
		Parent content = null;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("TextResources", language.getLocale());
			content = FXMLLoader.load(getClass().getResource(ui+".fxml"), bundle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productsBorderpane.setRight(content);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("ProductsList");
	}
	
}
