package view.shoppingList;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import model.OstoslistaDAO;
import model.Tuote;
import model.TuoteDAO;
import javafx.event.ActionEvent;

public class ShoppingListAddController {
    static TuoteDAO tuote = new TuoteDAO();
    static OstoslistaDAO ostoslista = new OstoslistaDAO();
	@FXML
	private TextField addProductName;
	@FXML
	private TextField addShoppingListAmount;

	// Event Listener on Button.onAction
	@FXML
	public void addShoppingListButton() {
        Tuote t = tuote.readTuoteNimi(addProductName.getText());
        ostoslista.createOstoslista(Double.parseDouble(addShoppingListAmount.getText()), t.getTuote_id());
	}
}