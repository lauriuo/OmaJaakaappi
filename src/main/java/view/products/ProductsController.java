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
/**
 * The controller for the products view which handles the navigation bar on the products view.
 * @author Ville
 *
 */
public class ProductsController implements Initializable{
	/**
	 * Gets the singleton of the language in use.
	 */
	private Language language = Language.getInstance();
	/**
	 * The border panel of the products view on which the content is placed.
	 */
	@FXML
	private BorderPane productsBorderpane;
	
	/**
	 * Event listener for the products listing navigation button.
	 * @param event Clicking the products list button.
	 */
	@FXML
	public void productsList(MouseEvent event) {
		loadContent("ProductsList");
	}
	/**
	 * Event listener for the products add navigation button.
	 * @param event Clicking the products add button.
	 */
	@FXML
	public void productsAdd(MouseEvent event) {
		loadContent("ProductsAdd");
	}
	/**
	 * Event listener for the products edit navigation button.
	 * @param event Clicking the products edit button.
	 */
	@FXML
	public void productsEdit(MouseEvent event) {
		loadContent("ProductsEdit");
	}
	/**
	 * Event listener for the products delete navigation button.
	 * @param event Clicking the products delete button.
	 */
	@FXML
	public void productsDelete(MouseEvent event) {
		loadContent("ProductsDelete");
	}
	/**
	 * For loading new content when user presses one of the navigation button.
	 * @param ui Which page will be loaded.
	 */
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
	/**
	 * Initializes the products page by loading the products list view.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("ProductsList");
	}
	
}
