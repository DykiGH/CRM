package controller;

import exec.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import modele.DetailErreur;

import java.util.List;
import java.util.Map;

public class DetailErreurController {

    private MainApp mainApp;

    @FXML
    private Button btnSDetailErreur;

    @FXML
    private TableView<DetailErreur> tabDetailErreur = new TableView<>();

    @FXML
    private TableColumn<DetailErreur, Integer> fxNumClient;

    @FXML
    private TableColumn<DetailErreur, String> fxErreur;

    private ObservableList<DetailErreur> erreurData = FXCollections.observableArrayList();

    public DetailErreurController() {
    }

    @FXML
    private void initialize() {
        fxNumClient.setCellValueFactory(new PropertyValueFactory<>("numClient"));
        fxErreur.setCellValueFactory(new PropertyValueFactory<>("detailErreur"));
        tabDetailErreur.setItems(erreurData);
    }

    public void setErreurData(Map<Integer, List<String>> erreurs) {
        erreurData.clear();  // Clear existing data
        for (Map.Entry<Integer, List<String>> entry : erreurs.entrySet()) {
            Integer numClient = entry.getKey();
            for (String erreur : entry.getValue()) {
                erreurData.add(new DetailErreur(numClient, erreur));
            }
        }
    }

    @FXML
    void toFacturation(ActionEvent event) throws Exception {
        mainApp.getRootLayout().getChildren().remove(mainApp.getRootLayout().getCenter());
        mainApp.showFacturation();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public ObservableList<DetailErreur> getErreurData() {
        return erreurData;
    }
}
