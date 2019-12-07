package view.main;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application{
	
	@Override
	public void start(final Stage stage) throws Exception {
		
		Language language = Language.getInstance();
		ResourceBundle bundle = ResourceBundle.getBundle("TextResources", language.getLocale());
		Parent root = FXMLLoader.load(getClass().getResource("NavBar.fxml"), bundle);
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
