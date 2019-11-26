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

public class RecipesController implements Initializable{
	@FXML
	private BorderPane recipesBorderpane;

	// Event Listener on Button.onMouseClicked
	@FXML
	public void recipesList(MouseEvent event) {
		loadContent("RecipesList");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recipesAdd(MouseEvent event) {
		loadContent("RecipesAdd");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recipesEdit(MouseEvent event) {
		loadContent("RecipesEdit");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recipesDelete(MouseEvent event) {
		loadContent("RecipesDelete");
	}
	
	public void ingredientAdd(MouseEvent event) {
		loadContent("IngredientAdd");
	}
	
	
	
	private void loadContent(String ui) {
		Parent content = null;
		try {
			content = FXMLLoader.load(getClass().getResource(ui+".fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recipesBorderpane.setCenter(content);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("RecipesList");
	}
}
