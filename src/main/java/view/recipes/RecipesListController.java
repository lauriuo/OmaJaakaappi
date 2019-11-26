package view.recipes;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Resepti;
import model.ReseptiDAO;
import model.TuoteDAO;

public class RecipesListController implements Initializable{

	static ReseptiDAO resepti = new ReseptiDAO();
	@FXML private TableView<Resepti> tableView;
	@FXML private TableColumn<Resepti, Number> recipesIdColumn;
	@FXML private TableColumn<Resepti, String> recipesNameColumn;
	@FXML private TableColumn<Resepti, String> recipesInstructColumn;
	@FXML private TableColumn<Resepti, String> recipesCaloriesColumn;
	//@FXML private TableColumn<Object, Number> recipesIngredientColumn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recipesIdColumn.setCellValueFactory(new PropertyValueFactory<Resepti, Number>("resepti_id"));
		recipesNameColumn.setCellValueFactory(new PropertyValueFactory<Resepti, String>("resepti_nimi"));
		recipesInstructColumn.setCellValueFactory(new PropertyValueFactory<Resepti, String>("resepti_ohje"));
		recipesCaloriesColumn.setCellValueFactory(new Callback<CellDataFeatures<Resepti, String>,ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Resepti, String> data) {
				return new SimpleStringProperty(String.valueOf(resepti.countKcalResepti(data.getValue().getResepti_id())));

			}
		});
		
		tableView.setItems(getAllRecipes());
	}

	public ObservableList<Resepti> getAllRecipes() {
		ArrayList<Object> reseptit = resepti.readReseptit();
		ArrayList<Resepti> recipes = new ArrayList<>();
		for (Object resepti : reseptit) {
			recipes.add((Resepti) resepti);
		}
		ObservableList<Resepti> reseptilista = FXCollections.observableArrayList(recipes);
		return reseptilista;
	}
}