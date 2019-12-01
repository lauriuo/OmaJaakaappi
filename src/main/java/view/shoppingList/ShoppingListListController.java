package view.shoppingList;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.Ostoslista;
import model.OstoslistaDAO;
import model.Tuote;

public class ShoppingListListController implements Initializable{
	static OstoslistaDAO ostoslista = new OstoslistaDAO();
	@FXML private TableView<Ostoslista> tableView;
    @FXML private TableColumn<Ostoslista, Number> shoppingListIdColumn;
    @FXML private TableColumn<Ostoslista, Number> shoppingListAmountColumn;
	@FXML private TableColumn<Ostoslista, String> shoppingListProductsNameColumn;
	@FXML private Button shoppingListRemove;
	/*
	@FXML private TableColumn<Object, String> productsNameColumn;
	@FXML private TableColumn<Object, String> productsUnitColumn;
	@FXML private TableColumn<Object, Number> productsCaloriesColumn;*/
	@FXML 
	public void shoppingListClear(MouseEvent event) {
		if (ostoslista.emptyOstoslista()) {
			tableView.getItems().clear();
		}
	}
	@FXML
	public void shoppingListRemove(MouseEvent event) {
		Ostoslista ostoslista_remove = tableView.getSelectionModel().getSelectedItem();
		int ostoslista_idx = tableView.getSelectionModel().getSelectedIndex();
		if (ostoslista.deleteOstoslista(ostoslista_remove.getOstoslista_id())) {
			tableView.getItems().remove(ostoslista_idx);
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		shoppingListRemove = new Button();
		shoppingListIdColumn.setCellValueFactory(new PropertyValueFactory<Ostoslista, Number>("ostoslista_id"));
		shoppingListProductsNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Ostoslista, String>, 
                                                         ObservableValue<String>>() {  
	    	@Override  
	    	public ObservableValue<String> call(CellDataFeatures<Ostoslista, String> data){  
				return new SimpleStringProperty(data.getValue().getTuote().getTuote_nimi());
	    	}  
		}); 
		shoppingListAmountColumn.setCellValueFactory(new PropertyValueFactory<Ostoslista, Number>("tuote_maara"));
		tableView.setItems(getAllProducts());
	}

	public ObservableList<Ostoslista> getAllProducts() {
		ArrayList<Object> tuotteet = ostoslista.readOstoslistat();
		ArrayList<Ostoslista> os = new ArrayList<>();
		for (Object tuote : tuotteet) {
			os.add((Ostoslista) tuote);	
		}
		ObservableList<Ostoslista> products = FXCollections.observableArrayList(os);
		return products;
	}
	
}