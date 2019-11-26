package view.main;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import model.Jaakaappi;
import model.JaakaappiDAO;

public class HomePageController implements Initializable{
	static JaakaappiDAO jaakaappi = new JaakaappiDAO();
	@FXML private BorderPane homePageBorderpane;
	@FXML private TableView<Object> tableView;
	@FXML private TableColumn<Jaakaappi, String> productColumn;
	@FXML private TableColumn<Jaakaappi, String> caloriesColumn;
	@FXML private TableColumn<Jaakaappi, String> saltColumn;
	@FXML private TableColumn<Jaakaappi, String> unitColumn;
	@FXML private TableColumn<Object, Number> amountColumn;
	@FXML private TableColumn<Object, Date> dateColumn;

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
	
	public ObservableList<Object> getGoingOldFridges() {
		ArrayList<Object> fridges = jaakaappi.readGoingOldJaakaapit();
		ObservableList<Object> result = FXCollections.observableArrayList(fridges);
		return result;
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

	@FXML
	public void shoppingList(MouseEvent event) {
		loadContent("ShoppingList");

	
	@FXML
	public void exit(MouseEvent event) {
		Platform.exit();

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
