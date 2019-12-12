package view.foodDiary;

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
 * The controller for the food diary view which handles the navigation bar on the food diary view.
 * @author Ville
 *
 */
public class FoodDiaryController implements Initializable{
	/**
	 * Gets the singleton of the language in use.
	 */
	private Language language = Language.getInstance();
	/**
	 * The border panel of the food diary view on which the content is placed.
	 */
	@FXML
	private BorderPane foodDiaryBorderpane;

	/**
	 * Event listener for the food diary listing navigation button.
	 * @param event Clicking the food diary list button.
	 */
	@FXML
	public void foodDiaryList(MouseEvent event) {
		loadContent("FoodDiaryList");
	}
	/**
	 * Event listener for the food diary with specific date navigation button.
	 * @param event Clicking the food diary on a specific date button.
	 */
	@FXML
	public void foodDiaryDate(MouseEvent event) {
		loadContent("FoodDiaryDate");
	}
	/**
	 * Event listener for the food diary edit navigation button.
	 * @param event Clicking the food diary edit button.
	 */
	@FXML
	public void foodDiaryEdit(MouseEvent event) {
		loadContent("FoodDiaryEdit");
	}
	/**
	 * Event listener for the food diary delete navigation button.
	 * @param event Clicking the food diary delete button.
	 */
	@FXML
	public void foodDiaryDelete(MouseEvent event) {
		loadContent("FoodDiaryDelete");
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
		foodDiaryBorderpane.setRight(content);
	}
	/**
	 * Initializes the food diary page by loading the food diary list view.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("FoodDiaryList");
	}
	
}
