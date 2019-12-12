package view.main;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Jaakaappi;
import model.JaakaappiDAO;
/**
 * Controller for the HomePage.fxml-file.
 * @author Ville
 *
 */
public class HomePageController implements Initializable{
	/**
	 * Gets the singleton of the language in use.
	 */
	private Language language = Language.getInstance();
	/**
	 * Attribute used for setting the language into English.
	 */
	private Locale english = new Locale("en", "GB");
	/**
	 * Attribute used for setting the language into Finnish.
	 */
	private Locale finnish = new Locale("fi", "FI");
	/**
     * For accessing the Jaakaappi operations in the model.
     */
	static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	/**
	 * The border panel of the home page on which the content is placed.
	 */
	@FXML private BorderPane homePageBorderpane;
	/**
	 * The table which lists the products in the fridge with an expiration date in the next two days. 
	 */
	@FXML private TableView<Object> tableView;
	/**
	 * Column in the table. Displays the product's name.
	 */
	@FXML private TableColumn<Jaakaappi, String> productColumn;
	/**
	 * Column in the table. Displays the amount of calories in the product.
	 */
	@FXML private TableColumn<Jaakaappi, String> caloriesColumn;
	/**
	 * Column in the table. Displays the amount of salt in the product.
	 */
	@FXML private TableColumn<Jaakaappi, String> saltColumn;
	/**
	 * Column in the table. Displays the unit used to measure the product.
	 */
	@FXML private TableColumn<Jaakaappi, String> unitColumn;
	/**
	 * Column in the table. Displays the amount of the product.
	 */
	@FXML private TableColumn<Object, Number> amountColumn;
	/**
	 * Column in the table. Displays the expiration date of the product.
	 */
	@FXML private TableColumn<Object, Date> dateColumn;

	/**
	 * Initializes the home page by loading the table with the products in the fridge with an expirtaion date within the next two days.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		productColumn.setCellValueFactory(new Callback<CellDataFeatures<Jaakaappi, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Jaakaappi, String> result) {
	            return new SimpleStringProperty(result.getValue().getTuote().getTuote_nimi());                
	        }
		});
		
		caloriesColumn.setCellValueFactory(new Callback<CellDataFeatures<Jaakaappi, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Jaakaappi, String> result) {
	            return new SimpleStringProperty(Double.toString(result.getValue().getTuote().getTuote_kcal()));                
	        }
		});
		
		saltColumn.setCellValueFactory(new Callback<CellDataFeatures<Jaakaappi, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Jaakaappi, String> result) {
	            return new SimpleStringProperty(Double.toString(result.getValue().getTuote().getTuote_suola()));                
	        }
		});
		
		unitColumn.setCellValueFactory(new Callback<CellDataFeatures<Jaakaappi, String>, ObservableValue<String>>() {
	        @Override
	        public ObservableValue<String> call(CellDataFeatures<Jaakaappi, String> result) {
	            return new SimpleStringProperty(result.getValue().getTuote().getTuote_yksikko());                
	        }
		});
		
		amountColumn.setCellValueFactory(new PropertyValueFactory<Object, Number>("tuote_maara"));
		
		dateColumn.setCellValueFactory(new PropertyValueFactory<Object, Date>("tuote_pvm"));
		
		tableView.setItems(getGoingOldFridges());
	}
	/**
	 * Method for fetching the list of products, with an expiration date within the next two days, from the database.
	 * @return ArrayList of the products with an expiration date within the next two days.
	 */
	public ObservableList<Object> getGoingOldFridges() {
		ArrayList<Object> fridges = jaakaappi.readGoingOldJaakaapit();
		ObservableList<Object> result = FXCollections.observableArrayList(fridges);
		return result;
	}
	
	/**
	 * Event listener for the fridge navigation button.
	 * @param event Clicking the fridge button.
	 */
	@FXML
	public void fridge(MouseEvent event) {
		loadContent("Fridge");
	}
	/**
	 * Event listener for the products navigation button.
	 * @param event Clicking the products button.
	 */
	@FXML
	public void products(MouseEvent event) {
		loadContent("Products");
	}
	/**
	 * Event listener for the recipes navigation button.
	 * @param event Clicking the recipes button.
	 */
	@FXML
	public void recipes(MouseEvent event) {
		loadContent("Recipes");
	}
	/**
	 * Event listener for the food diary navigation button.
	 * @param event Clicking the food diary button.
	 */
	@FXML
	public void foodDiary(MouseEvent event) {
		loadContent("FoodDiary");
	}
	/**
	 * Event listener for the shopping list navigation button.
	 * @param event Clicking the shopping list button.
	 */
	@FXML
	public void shoppingList(MouseEvent event) {
		loadContent("ShoppingList");
	}
	/**
	 * Event listener for the exit button. Closes the application.
	 * @param event Clicking the exit button.
	 */
	@FXML
	public void exit(MouseEvent event) {
		Platform.exit();

	}
	/**
	 * Method for changing the language of the application on the fly. Changes from English to Finnish and from Finnish to English
	 * @param event Cliking the language change button.
	 * @throws IOException Incase the application fails to fetch the language resource bunlde.
	 */
	@FXML
	public void language(MouseEvent event) throws IOException {
		if (english.equals(language.getLocale())) {
			language.setLocale(finnish);
		} else if (finnish.equals(language.getLocale())) {
			language.setLocale(english);
		}

		Stage stage = (Stage) homePageBorderpane.getScene().getWindow();
		ResourceBundle bundle = ResourceBundle.getBundle("TextResources", language.getLocale());
		Parent root = FXMLLoader.load(getClass().getResource("NavBar.fxml"), bundle);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
	}
	/**
	 * For loading new content when user presses one of the navigation button.
	 * @param ui Which page will be loaded.
	 */
	private void loadContent(String ui) {
		Parent content = null;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("TextResources", language.getLocale());
			content = FXMLLoader.load(getClass().getResource(ui+".fxml"), bundle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homePageBorderpane.getChildren().setAll(content);
	}
}
