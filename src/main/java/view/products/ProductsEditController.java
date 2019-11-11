package view.products;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Tuote;
import model.TuoteDAO;

public class ProductsEditController {
	static TuoteDAO tuote = new TuoteDAO();
	Tuote editable = new Tuote();
	private String oldName = "The current name";
	private String oldUnit = "The current unit";
	private String oldCalories = "The current calories";
	@FXML private TextField searchProducts;
	@FXML private Label lblOldName;
	@FXML private Label lblOldUnit;
	@FXML private Label lblOldCalories;
	
	@FXML private TextField newProductName;
	@FXML private TextField newProductUnit;
	@FXML private TextField newProductCalories;

	@FXML
	public void searchButtonAction() {
		editable = tuote.readTuoteNimi(searchProducts.getText());
		this.oldName = editable.getTuote_nimi();
		this.oldUnit = editable.getTuote_yksikko();
		this.oldCalories = String.valueOf(editable.getTuote_kcal());
		lblOldName.setText(oldName);
		lblOldUnit.setText(oldUnit);
		lblOldCalories.setText(oldCalories);
	}
	
	@FXML
	public void updateButtonAction() {
		tuote.updateTuote(oldName, newProductName.getText(), newProductUnit.getText(), Double.parseDouble(newProductCalories.getText()));
	}
}