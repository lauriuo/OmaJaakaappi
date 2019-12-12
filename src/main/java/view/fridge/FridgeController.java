package view.fridge;

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
 * The controller for the fridge view which handles the navigation bar on the fridge view.
 * @author Ville
 *
 */
public class FridgeController implements Initializable{
	/**
	 * Gets the singleton of the language in use.
	 */
	private Language language = Language.getInstance();
	/**
	 * The border panel of the fridge view on which the content is placed.
	 */
	@FXML
	private BorderPane fridgeBorderpane;

	/**
	 * Event listener for the fridge listing navigation button.
	 * @param event Clicking the fridge list button.
	 */
	@FXML
	public void fridgeList(MouseEvent event) {
		loadContent("FridgeList");
	}
	/**
	 * Event listener for the fridge add navigation button.
	 * @param event Clicking the fridge add button.
	 */
	@FXML
	public void fridgeAdd(MouseEvent event) {
		loadContent("FridgeAdd");
	}
	/**
	 * Event listener for the fridge edit navigation button.
	 * @param event Clicking the fridge edit button.
	 */
	@FXML
	public void fridgeEdit(MouseEvent event) {
		loadContent("FridgeEdit");
	}
	/**
	 * Event listener for the fridge delete navigation button.
	 * @param event Clicking the fridge delete button.
	 */
	@FXML
	public void fridgeDelete(MouseEvent event) {
		loadContent("FridgeDelete");
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
		fridgeBorderpane.setRight(content);
	}
	/**
	 * Initializes the fridge page by loading the fridge list view.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("FridgeList");
	}
	
}
