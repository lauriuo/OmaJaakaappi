package view.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import com.sun.istack.logging.Logger;

import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashController implements Initializable {

	@FXML
	private HBox rootPane;
	
	@FXML
	Rectangle r1;
	
	@FXML
	Rectangle r2;
	
	@FXML
	Rectangle r3;
	
	@FXML
	Rectangle r4;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		new SplashScreen().start();
		setRotate(r1, true, 360, 5);
		setRotate(r2, true, 360, 5);
		setRotate(r3, false, 180, 5);
		setRotate(r4, false, 180, 5);
		

	}

	class SplashScreen extends Thread {

		public void run() {
			try {
				Thread.sleep(5000);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Parent root = null;
				        Language language = Language.getInstance();
				        ResourceBundle bundle = ResourceBundle.getBundle("TextResources", language.getLocale());
				        try {
				          root = FXMLLoader.load(getClass().getResource("NavBar.fxml"), bundle);
				          }
				        catch (IOException ex) {
				          Logger.getLogger(SplashController.class.getName(), null).log(Level.SEVERE, null, ex);
				        }

				        Scene scene = new Scene(root);
				        Stage stage = new Stage();
				        stage.setScene(scene);
				        stage.show();

				        rootPane.getScene().getWindow().hide();
					}
				});
			}
			catch (InterruptedException ex) {
				Logger.getLogger(SplashController.class.getName(), null).log(Level.SEVERE, null, ex);
				}
		}
	}
	
	private void setRotate(Rectangle r, boolean reverse, int angle, int duration) {
		
		RotateTransition rt = new RotateTransition(Duration.seconds(5), r);
		
		rt.setAutoReverse(reverse);
		
		rt.setByAngle(angle);
		rt.setDelay(Duration.seconds(0));
		rt.setRate(3);
		rt.setCycleCount(20);
		rt.play();
	}
}
