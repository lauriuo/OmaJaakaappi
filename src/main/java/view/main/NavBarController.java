package view.main;

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
/**
 * The controller for the navigation bar of the application.
 * @author Ville
 *
 */
public class NavBarController implements Initializable{
	/**
	 * Gets the singleton of the language in use.
	 */
	private Language language = Language.getInstance();
	/**
	 * The border panel of the navigation bar on which the content is placed.
	 */
	@FXML
	private BorderPane mainBorderpane;

	/**
	 * Event listener for the home page navigation button.
	 * @param event Clicking the home page button.
	 */
	@FXML
	public void homePage(MouseEvent event) {
		loadContent("HomePage");
	}
	/**
	 * Event listener for the fridge navigation button.
	 * @param event Clicking the fridge button.
	 */
	@FXML
	public void fridge(MouseEvent event) {
		loadContent("Fridge");
	}
	/**
	 * Event listener for the products navigation button.
	 * @param event Clicking the products button.
	 */
	@FXML
	public void products(MouseEvent event) {
		loadContent("Products");
	}
	/**
	 * Event listener for the recipes navigation button.
	 * @param event Clicking the recipes button.
	 */
	@FXML
	public void recipes(MouseEvent event) {
		loadContent("Recipes");
	}
	/**
	 * Event listener for the food diary navigation button.
	 * @param event Clicking the food diary button.
	 */
	@FXML
	public void foodDiary(MouseEvent event) {
		loadContent("FoodDiary");
	}
	/**
	 * Event listener for the shopping list navigation button.
	 * @param event Clicking the shopping list button.
	 */
	@FXML
	public void shoppingList(MouseEvent event) {
		loadContent("ShoppingList");
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
		mainBorderpane.setLeft(content);
	}
	/**
	 * Initializes the navigation bar by loading the home page view on it's border panel.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("HomePage");
	}
	
}
