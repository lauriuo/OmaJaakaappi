package view.recipes;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

public class RecipesController {
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
		// TODO Autogenerated
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recipesEdit(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recipesDelete(MouseEvent event) {
		// TODO Autogenerated
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
}