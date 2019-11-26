package view.recipes;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.TuoteDAO;
import model.AinesDAO;

public class RecipesIngredientController {
	
	AinesDAO aines = new AinesDAO();
	@FXML
	private TextField addIngredientProductID;
	@FXML
	private TextField addIngredientRecipeID;
	@FXML
	private TextField addIngredientAmount;

	// Event Listener on Button.onAction
	@FXML
	public void addProductButton() {
		aines.createAines(Integer.parseInt(addIngredientProductID.getText()), Integer.parseInt(addIngredientRecipeID.getText()), Double.parseDouble(addIngredientAmount.getText()));
	}
}
