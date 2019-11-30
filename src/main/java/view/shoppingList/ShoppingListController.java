package view.shoppingList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

public class ShoppingListController implements Initializable{
	@FXML private BorderPane shoppingListBorderpane;
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void shoppingListList(MouseEvent event) {
		loadContent("ShoppingListList");
	}
	@FXML
	public void shoppingListAdd(MouseEvent event) {
		loadContent("ShoppingListAdd");
	}

	private void loadContent(String ui) {
		Parent content = null;
		try {
			content = FXMLLoader.load(getClass().getResource(ui+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		shoppingListBorderpane.setCenter(content);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("ShoppingListList");
	}
}