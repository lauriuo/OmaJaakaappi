package view.recipes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.AinesDAO;
import model.Resepti;
import model.ReseptiDAO;

public class RecipesEditController {
	static ReseptiDAO resepti = new ReseptiDAO();
	Resepti editable = new Resepti();
	private String oldName = "The current name";
	private String oldInst = "The current instructions";
	private int reseptiID;
	@FXML private TextField searchRecipes;
	@FXML private Label lblOldName;
	@FXML private Label lblOldInst;
	
	@FXML private TextField newRecipeName;
	@FXML private TextField newRecipeInstructions;

	@FXML
	public void searchButtonAction() {
		try {
			editable = resepti.readReseptiNimi(searchRecipes.getText());
			this.oldName = editable.getResepti_nimi();
			this.oldInst = editable.getResepti_ohje();
			this.reseptiID = editable.getResepti_id();
			lblOldName.setText(oldName);
			lblOldInst.setText(oldInst); }
		catch (Exception e) {
			lblOldName.setText("Error: recipe not found");
			lblOldInst.setText("Error: recipe not found");
		}
	}
	
	@FXML
	public void updateButtonEditAction() {
		resepti.updateResepti(reseptiID, newRecipeName.getText(), newRecipeInstructions.getText());
	}
	
	public void updateemptyButtonEditAction() {
		AinesDAO aines = new AinesDAO();
		aines.deleteAineksetResepti(editable.getResepti_id());
		resepti.updateResepti(reseptiID, newRecipeName.getText(), newRecipeInstructions.getText());
	}
}