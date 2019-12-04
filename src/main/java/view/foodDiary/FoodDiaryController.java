package view.foodDiary;

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
import view.main.Language;

public class FoodDiaryController implements Initializable{
	private Language language = Language.getInstance();
	@FXML
	private BorderPane foodDiaryBorderpane;

	// Event Listener on Button.onMouseClicked
	@FXML
	public void foodDiaryList(MouseEvent event) {
		loadContent("FoodDiaryList");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void foodDiaryDate(MouseEvent event) {
		loadContent("FoodDiaryDate");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void foodDiaryEdit(MouseEvent event) {
		loadContent("FoodDiaryEdit");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void foodDiaryDelete(MouseEvent event) {
		loadContent("FoodDiaryDelete");
	}
	
	private void loadContent(String ui) {
		Parent content = null;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("TextResources", language.getLocale());
			content = FXMLLoader.load(getClass().getResource(ui+".fxml"), bundle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		foodDiaryBorderpane.setRight(content);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadContent("FoodDiaryList");
	}
	
}
