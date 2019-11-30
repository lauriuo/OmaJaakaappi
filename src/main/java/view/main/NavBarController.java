package view.main;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

public class NavBarController implements Initializable{
	@FXML
	private BorderPane mainBorderpane;

	// Event Listener on Button.onMouseClicked
	@FXML
	public void homePage(MouseEvent event) {
		loadContent("HomePage");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void fridge(MouseEvent event) {
		loadContent("Fridge");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void products(MouseEvent event) {
		loadContent("Products");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recipes(MouseEvent event) {
		loadContent("Recipes");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void foodDiary(MouseEvent event) {
		loadContent("FoodDiary");
	}
	
	private void loadContent(String ui) {
		Parent content = null;
		try {
			Locale locale = new Locale("fi", "FI");
			ResourceBundle bundle = ResourceBundle.getBundle("TextResources", locale);
			content = FXMLLoader.load(getClass().getResource(ui+".fxml"), bundle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainBorderpane.setLeft(content);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("HomePage");
	}
	
}
