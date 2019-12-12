package view.recipes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import view.main.Language;
/**
 * The controller for the recipes view which handles the navigation bar on the food diary view.
 * @author Ville
 *
 */
public class RecipesController implements Initializable{
	/**
	 * Gets the singleton of the language in use.
	 */
	private Language language = Language.getInstance();
	/**
	 * The border panel of the recipes view on which the content is placed.
	 */
	@FXML
	private BorderPane recipesBorderpane;

	/**
	 * Event listener for the recipes listing navigation button.
	 * @param event Clicking the recipes list button.
	 */
	@FXML
	public void recipesList(MouseEvent event) {
		loadContent("RecipesList");
	}
	/**
	 * Event listener for the recipes add navigation button.
	 * @param event Clicking the recipes add button.
	 */
	@FXML
	public void recipesAdd(MouseEvent event) {
		loadContent("RecipesAdd");
	}
	/**
	 * Event listener for the recipes edit navigation button.
	 * @param event Clicking the recipes edit button.
	 */
	@FXML
	public void recipesEdit(MouseEvent event) {
		loadContent("RecipesEdit");
	}
	/**
	 * Event listener for the recipes delete navigation button.
	 * @param event Clicking the recipes delete button.
	 */
	@FXML
	public void recipesDelete(MouseEvent event) {
		loadContent("RecipesDelete");
	}
	/**
	 * Event listener for the recipes ingredient add navigation button.
	 * @param event Clicking the recipes ingredient add button.
	 */
	@FXML
	public void ingredientAdd(MouseEvent event) {
		loadContent("IngredientAdd");
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
		recipesBorderpane.setRight(content);
	}
	/**
	 * Initializes the recipes page by loading the recipes list view.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("RecipesList");
	}
}
