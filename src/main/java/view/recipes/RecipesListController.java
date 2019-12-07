package view.recipes;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Aines;
import model.AinesDAO;
import model.Resepti;
import model.ReseptiDAO;
import model.TuoteDAO;
import view.main.Language;

public class RecipesListController implements Initializable{
	Language language = Language.getInstance();
	static AinesDAO aines = new AinesDAO();
	static ReseptiDAO resepti = new ReseptiDAO();
	@FXML private TableView<Resepti> tableView;
	@FXML private TableColumn<Resepti, Number> recipesIdColumn;
	@FXML private TableColumn<Resepti, String> recipesNameColumn;
	@FXML private TableColumn<Resepti, String> recipesInstructColumn;
	@FXML private TableColumn<Resepti, String> recipesCaloriesColumn;
	@FXML private Button recipesDetails;	
	@FXML private Button recipesRemove;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableView.setFixedCellSize(25); //without this, row height will expand if instructions have more lines
		recipesDetails = new Button();
		recipesRemove = new Button();
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
	public void showRecipesDetails() {
		Resepti chosenRecipe = tableView.getSelectionModel().getSelectedItem();
		if (chosenRecipe != null) {
			Context.getInstance().setId(chosenRecipe.getResepti_id());
			try {
				ResourceBundle bundle = ResourceBundle.getBundle("TextResources", language.getLocale());
				FXMLLoader loader = new FXMLLoader(getClass().getResource("./RecipesDetails.fxml"), bundle);
				Parent root = loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setTitle(chosenRecipe.getResepti_nimi());
				stage.show();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
	public void removeRecipe() {
		Resepti recipe_remove = tableView.getSelectionModel().getSelectedItem();
		int remove_idx = tableView.getSelectionModel().getSelectedIndex();
		if (aines.deleteAineksetResepti(recipe_remove.getResepti_id())) {
			tableView.getItems().remove(remove_idx);
			aines.deleteAineksetResepti(recipe_remove.getResepti_id());
			resepti.deleteResepti(recipe_remove.getResepti_id());
		}
	}
}