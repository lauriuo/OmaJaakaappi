package view.main;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application{
	
	@Override
	public void start(final Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("Splash.fxml"));
		Scene scene = new Scene(root);
		
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setScene(scene);
		stage.show();
	}
		

	public static void main(String[] args) {
		launch(args);
	}
}
