package view.recipes;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.AinesDAO;
import model.ReseptiDAO;


public class RecipesDeleteController {
	AinesDAO aines = new AinesDAO();
	static ReseptiDAO resepti = new ReseptiDAO();
	@FXML private TextField deleteRecipeID;
	
	@FXML
	public void deleteRecipeButton() {
		aines.deleteAineksetResepti(Integer.parseInt(deleteRecipeID.getText()));
		resepti.deleteResepti(Integer.parseInt(deleteRecipeID.getText()));
	}
}