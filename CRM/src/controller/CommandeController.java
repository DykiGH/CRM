package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exec.MainApp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modele.Commande;
import javafx.scene.control.cell.PropertyValueFactory;

public class CommandeController {

    // Reference au main
    private MainApp mainApp;
    // Champs
    // Map pour stocker les informations clé/valeur des clients avec des erreurs
    private Map<Integer, List<String>> erreurClientsCommande = new HashMap<>();
    // Champs FXML
    @FXML
    private Button btnSuivantCommande;
    @FXML
    private TableView<Commande> tabCommande = new TableView<>();
    @FXML
    private TableColumn<Commande, Integer> fxNumClient;
    @FXML
    private TableColumn<Commande, String> fxArticle;
    @FXML
    private TableColumn<Commande, Double> fxPrix;
    @FXML
    private TableColumn<Commande, Integer> fxQuantite;
    @FXML
    private TableColumn<Commande, Integer> fxStock;

    public CommandeController() {
    }

    @FXML
    private void initialize() {
        fxNumClient.setCellValueFactory(new PropertyValueFactory<>("numClient"));
        fxArticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        fxQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        fxPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        fxStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
        // créer des articles à mettre dans le tableau de commandes
        tabCommande.getItems().add(new Commande(12, "Ordinateur Papple", 5, 499.99, 99));
        tabCommande.getItems().add(new Commande(13, "Souris optique filaire", 5, 12.50, 420));
        tabCommande.getItems().add(new Commande(13, "Imprimante HP", 7, 40.50, 420));
        tabCommande.getItems().add(new Commande(13, "Clavier Logitech", 19, 15.99, 18));
        tabCommande.getItems().add(new Commande(14, "Souris sans fil", 2, 25.50, 25));
        tabCommande.getItems().add(new Commande(14, "Écran LED 24 pouces", 1, 159.99, 12));
        tabCommande.getItems().add(new Commande(14, "Disque dur externe 1 To", 8, 69.99, 8));
        tabCommande.getItems().add(new Commande(87, "Clavier mécanique RGB", 1, 89.99, 15));
        tabCommande.getItems().add(new Commande(87, "Casque audio sans fil", 2, 79.50, 20));
        tabCommande.getItems().add(new Commande(87, "Webcam HD 1080p", 1, 49, 10));
        tabCommande.getItems().add(new Commande(87, "Routeur Wi-Fi 6", 1, 129, 5));
        tabCommande.getItems().add(new Commande(102, "Ordinateur Papple", 5, 499.99, 99));
    }

    @FXML
    void toLivraison(ActionEvent event) throws Exception {
        mainApp.getRootLayout().getChildren().remove(mainApp.getRootLayout().getCenter());
        mainApp.showLivraison();
    }

    // Vérifie s'il y a des erreurs dans les commandes et les gère, puis les ajoute dans un tableau associatif, renvoie vrai si le tableau est vide
    public boolean verifQuantiteDispo() {
    	String erreur1 = "Le nombre d'articles commandés est plus grand que le stock";
    	String erreur2 = "Trop d'articles commandés (supérieur à 6)";
        for (Commande commande : tabCommande.getItems()) {
            List<String> erreurs = erreurClientsCommande.getOrDefault(commande.getNumClient(), new ArrayList<>());

            if (commande.getQuantite() > commande.getStock() && !erreurs.contains(erreur1)) {
                erreurs.add(erreur1);
            }
            if (commande.getQuantite() > 6 && !erreurs.contains(erreur2)) {
                erreurs.add(erreur2);
            }

            if (!erreurs.isEmpty()) {
                erreurClientsCommande.put(commande.getNumClient(), erreurs);
            }
        }

        return erreurClientsCommande.isEmpty();
    }


    public ObservableList<Commande> getCommandes() {
        return tabCommande.getItems();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public Map<Integer, List<String>> getErreurClientsCommande() {
        return erreurClientsCommande;
    }

    public void setErreurClientsCommande(Map<Integer, List<String>> erreurClientsCommande) {
        this.erreurClientsCommande = erreurClientsCommande;
    }
}
