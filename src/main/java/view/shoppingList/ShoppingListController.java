package view.shoppingList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import view.main.Language;
/**
 * The controller for the shopping list view which handles the navigation bar on the shopping list view.
 * @author Ville
 *
 */
public class ShoppingListController implements Initializable{
	/**
	 * Gets the singleton of the language in use.
	 */
	private Language language = Language.getInstance();
	/**
	 * The border panel of the shopping list view on which the content is placed.
	 */
	@FXML private BorderPane shoppingListBorderpane;
	
	/**
	 * Event listener for the shopping list listing navigation button.
	 * @param event Clicking the shopping list list button.
	 */
	@FXML
	public void shoppingListList(MouseEvent event) {
		loadContent("ShoppingListList");
	}
	/**
	 * Event listener for the shopping list add navigation button.
	 * @param event Clicking the shopping list add button.
	 */
	@FXML
	public void shoppingListAdd(MouseEvent event) {
		loadContent("ShoppingListAdd");
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
			e.printStackTrace();
		}
		shoppingListBorderpane.setRight(content);
	}
	/**
	 * Initializes the shopping list page by loading the products list view.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("ShoppingListList");
	}
}