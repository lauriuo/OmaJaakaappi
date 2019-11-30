package view.main;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application{
	
	@Override
	public void start(final Stage stage) throws Exception {
		
		Locale locale = new Locale("fi", "FI");
		ResourceBundle bundle = ResourceBundle.getBundle("TextResources", locale);
		
		Parent root = FXMLLoader.load(getClass().getResource("NavBar.fxml"), bundle);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
