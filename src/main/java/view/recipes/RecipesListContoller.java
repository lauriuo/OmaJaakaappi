package view.recipes;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.IReseptiDAO;
import model.ReseptiDAO;
import model.TuoteDAO;

public class RecipesListContoller implements Initializable{

	static ReseptiDAO resepti = new ReseptiDAO();
	@FXML private TableView<Object> tableView;
	@FXML private TableColumn<Object, Number> recipesIdColumn;
	@FXML private TableColumn<Object, String> recipesNameColumn;
	@FXML private TableColumn<Object, String> recipesQuideColumn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recipesIdColumn.setCellValueFactory(new PropertyValueFactory<Object, Number>("resepti_id"));
		recipesNameColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("resepti_nimi"));
		recipesQuideColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("resepti_yksikko"));
		
		tableView.setItems(getAllRecipes());
	}

	public ObservableList<Object> getAllRecipes() {
		ArrayList<Object> reseptit = resepti.readReseptit();
		ObservableList<Object> recipes = FXCollections.observableArrayList(reseptit);
		return recipes;
	}
	
}