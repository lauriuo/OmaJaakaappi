package view.products;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.TuoteDAO;

public class ProductsDeleteController {
	static TuoteDAO tuote = new TuoteDAO();
	@FXML private TextField deleteProductName;
	
	@FXML
	public void deleteProductButton() {
		tuote.deleteTuote(deleteProductName.getText());
	}
}