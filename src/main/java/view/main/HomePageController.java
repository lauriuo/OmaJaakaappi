package view.main;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class HomePageController {
	@FXML
	private BorderPane homePageBorderpane;

	// Event Listener on Button.onMouseClicked
	@FXML
	public void fridge(MouseEvent event) {
		loadContent("fridge");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void products(MouseEvent event) {
		loadContent("products");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recipes(MouseEvent event) {
		loadContent("recipes");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void foodDiary(MouseEvent event) {
		loadContent("fooddiary");
	}

	private void loadContent(String ui) {
		Parent content = null;
		try {
			content = FXMLLoader.load(getClass().getResource(ui+".fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homePageBorderpane.getChildren().setAll(content);
	}
}
