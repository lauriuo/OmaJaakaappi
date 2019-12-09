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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Aines;
import model.AinesDAO;
import model.Resepti;
import model.ReseptiDAO;

public class RecipesDetailsController implements Initializable {
    @FXML private Label totalCalories;
    @FXML private Label totalSalt;
    @FXML private TableView<Aines> tableViewFridge;
    @FXML private TableColumn<Aines, String> inFridgeNameColumn;
    @FXML private TableColumn<Aines, Number> inFridgeAmountColumn;
    @FXML private TableColumn<Aines, String> inFridgeUnitColumn;
    @FXML private TableColumn<Aines, String> inFridgeKcalColumn;

    @FXML private TableView<Aines> tableViewNotFridge;
    @FXML private TableColumn<Aines, String> notFridgeNameColumn;
    @FXML private TableColumn<Aines, Number> notFridgeAmountColumn;
    @FXML private TableColumn<Aines, String> notFridgeUnitColumn;
    @FXML private TableColumn<Aines, String> notFridgeKcalColumn;
    AinesDAO aines_dao = new AinesDAO();
    ReseptiDAO resepti_dao = new ReseptiDAO();
    @FXML private TextArea textAreaDetails;

    public void fillFridgeColumns() {
        @SuppressWarnings("unchecked")
        ArrayList<Aines> inFridgeAines = (ArrayList<Aines>)(ArrayList<?>)
            aines_dao.availableForResepti(Context.getInstance().getId());

        ObservableList<Aines> aineet = FXCollections.observableArrayList(inFridgeAines);

        inFridgeNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Aines, String>, 
                                                         ObservableValue<String>>() {  
	    	@Override  
	    	public ObservableValue<String> call(CellDataFeatures<Aines, String> aines){  
				return new SimpleStringProperty(
                    String.valueOf(aines.getValue().getTuote().getTuote_nimi()));
        }});

        inFridgeAmountColumn.setCellValueFactory(new PropertyValueFactory<Aines, Number>("aines_maara"));

        inFridgeUnitColumn.setCellValueFactory(new Callback<CellDataFeatures<Aines, String>, 
                                                         ObservableValue<String>>() {  
	    	@Override  
	    	public ObservableValue<String> call(CellDataFeatures<Aines, String> aines){  
				return new SimpleStringProperty(
                    String.valueOf(aines.getValue().getTuote().getTuote_yksikko()));
        }});

        inFridgeKcalColumn.setCellValueFactory(new Callback<CellDataFeatures<Aines, String>, 
                                                         ObservableValue<String>>() {  
	    	@Override  
	    	public ObservableValue<String> call(CellDataFeatures<Aines, String> aines){  
				return new SimpleStringProperty(
                    String.valueOf(aines.getValue().getTuote().getTuote_kcal() * 10 *
                                   aines.getValue().getAines_maara()));
        }});

        tableViewFridge.setItems(aineet);
    }
    public void fillnotFridgeColumns() {
        @SuppressWarnings("unchecked")
        ArrayList<Aines> notInFridgeAines = (ArrayList<Aines>)(ArrayList<?>)
            aines_dao.notAvailableForResepti(Context.getInstance().getId());
        ObservableList<Aines> aineet = FXCollections.observableArrayList(notInFridgeAines);

        notFridgeNameColumn.setCellValueFactory(new Callback<CellDataFeatures<Aines, String>, 
                                                         ObservableValue<String>>() {  
	    	@Override  
	    	public ObservableValue<String> call(CellDataFeatures<Aines, String> aines){  
				return new SimpleStringProperty(
                    String.valueOf(aines.getValue().getTuote().getTuote_nimi()));
        }});

        notFridgeAmountColumn.setCellValueFactory(new PropertyValueFactory<Aines, Number>("aines_maara"));

        notFridgeUnitColumn.setCellValueFactory(new Callback<CellDataFeatures<Aines, String>, 
                                                         ObservableValue<String>>() {  
	    	@Override  
	    	public ObservableValue<String> call(CellDataFeatures<Aines, String> aines){  
				return new SimpleStringProperty(
                    String.valueOf(aines.getValue().getTuote().getTuote_yksikko()));
        }});

        notFridgeKcalColumn.setCellValueFactory(new Callback<CellDataFeatures<Aines, String>, 
                                                         ObservableValue<String>>() {  
	    	@Override  
	    	public ObservableValue<String> call(CellDataFeatures<Aines, String> aines){  
				return new SimpleStringProperty(
                    String.valueOf(aines.getValue().getTuote().getTuote_kcal() * 10 *
                                   aines.getValue().getAines_maara()));
        }});
        tableViewNotFridge.setItems(aineet);
    }
    public void fillTextArea() {
        Resepti r = resepti_dao.readReseptiId(Context.getInstance().getId());
        double kcal = resepti_dao.countKcalResepti(r.getResepti_id());
        double salt = 0;
        @SuppressWarnings("unchecked")
        ArrayList<Aines> aineet = (ArrayList<Aines>)(ArrayList<?>)
            aines_dao.readAineksetReseptiID(Context.getInstance().getId());       

        for (Aines aines : aineet) {
            salt += (aines.getAines_maara() * (10 * aines.getTuote().getTuote_suola()));
        }
        String textAreaText = r.getResepti_ohje();
        totalCalories.setText(String.format("%.2f kcal", kcal));
        totalSalt.setText(String.format("%.2f g", salt));
        textAreaDetails.setWrapText(true); //makes a new line when text goes over the area width
        textAreaDetails.setText(textAreaText);
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        textAreaDetails.setEditable(false);
        Resepti resepti = resepti_dao.readReseptiId(Context.getInstance().getId());
        if (resepti != null) {
            fillFridgeColumns();
            fillnotFridgeColumns();
            fillTextArea();
        }
    }
}