package view.fridge;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

public class FridgeController implements Initializable{
	@FXML
	private BorderPane fridgeBorderpane;

	// Event Listener on Button.onMouseClicked
	@FXML
	public void fridgeList(MouseEvent event) {
		loadContent("FridgeList");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void fridgeAdd(MouseEvent event) {
		loadContent("FridgeAdd");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void fridgeEdit(MouseEvent event) {
		loadContent("FridgeEdit");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void fridgeDelete(MouseEvent event) {
		loadContent("FridgeDelete");
	}
	
	private void loadContent(String ui) {
		Parent content = null;
		try {
			content = FXMLLoader.load(getClass().getResource(ui+".fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fridgeBorderpane.setRight(content);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("FridgeList");
	}
	
}
