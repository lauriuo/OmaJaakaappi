package view.recipes;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.ReseptiDAO;
import javafx.event.ActionEvent;

public class RecipesAddController {
	static ReseptiDAO resepti = new ReseptiDAO();
	@FXML
	private TextField addRecipeName;
	@FXML
	private TextArea addRecipeGuide;
	
	// Event Listener on Button.onAction
	@FXML
	public void addRecipeButton() {
		resepti.createResepti(addRecipeName.getText(), addRecipeGuide.getText());
	}
}
