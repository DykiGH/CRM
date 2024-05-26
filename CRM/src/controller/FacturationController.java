package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exec.MainApp;
import modele.Commande;
import modele.Facturation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FacturationController {
    // Reference to the main application.
    private MainApp mainApp;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    //Champs
    // Map pour stocker les informations clé/valeur des clients avec des erreurs
    private Map<Integer, List<String>> erreurClientsFacturation = new HashMap<>();
    //Champs FXML
    @FXML
    private TableView<Facturation> tabFacturation = new TableView<Facturation>();
    
    @FXML
    private TableColumn<Facturation, Integer> fxNumClient;
    
    @FXML
    private TableColumn<Facturation, String> fxAddresseFacturation;

    @FXML
    private TableColumn<Facturation, Float> fxMontant;
    
    @FXML
    private TableColumn<Facturation, Integer> fxNumFacture;

    @FXML
    private TableColumn<Facturation, Boolean> fxPaiement;

    @FXML
    private Button btnPrecedentFacturation;

    @FXML
    private Button btnSuivantFacturation;
    
    public FacturationController() {
    }
    
    @FXML
    private void initialize() {
        // permet d'attribuer les valeurs des colonnes du tableau
        fxNumClient.setCellValueFactory(new PropertyValueFactory<Facturation, Integer>("numClient"));
        fxNumFacture.setCellValueFactory(new PropertyValueFactory<Facturation, Integer>("numFacture"));
        fxAddresseFacturation.setCellValueFactory(new PropertyValueFactory<Facturation, String>("adresseFacturation"));
        fxMontant.setCellValueFactory(new PropertyValueFactory<Facturation, Float>("montant"));
        fxPaiement.setCellValueFactory(new PropertyValueFactory<Facturation, Boolean>("paiement"));
        
        // on met une valeur par défaut au montant, il sera remplacé avec mettreAJourMontants(...)
        tabFacturation.getItems().add(new Facturation(12, 1, "12 Rue de la Fontaine, 75001 Paris", 0.0f, true));
        tabFacturation.getItems().add(new Facturation(13, 2, "4 Avenue des Roses, 31000 Toulouse", 0.0f, true));
        tabFacturation.getItems().add(new Facturation(14, 3, "7 Rue du Commerce, 44000 Nantes", 0.0f, false));
        tabFacturation.getItems().add(new Facturation(87, 8, "1010 Les Moulins, 06000 Nice", 0.0f, false));
        tabFacturation.getItems().add(new Facturation(102, 15, "1313 Rue Principale, Canada", 0.0f, true));
        // Cell factory personnalisée pour afficher "Vrai" ou "Faux" au lieu de true ou false
        fxPaiement.setCellFactory(column -> {
            return new TableCell<Facturation, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item ? "Oui" : "Non");
                    }
                }
            };
        });
    }
    
    public Map<String, Double> calculerMontantTotalParArticle(int numClient, ObservableList<Commande> commandes) {
        Map<String, Double> montantParArticle = new HashMap<>();
        for (Commande commande : commandes) {
            if (commande.getNumClient() == numClient) {
                montantParArticle.put(commande.getArticle(),
                        // getOrDefault permet de spécifier une valeur par défaut à utiliser lorsque la clé recherchée n'est pas présente dans la map
                        montantParArticle.getOrDefault(commande.getArticle(), 0.0d) + commande.getQuantite() * commande.getPrix());
            }
        }
        return montantParArticle;
    }
    
    // Méthode pour mettre à jour les montants de facturation
    public void mettreAJourMontants(ObservableList<Commande> commandes) {
        for (Facturation facturation : tabFacturation.getItems()) {
            Map<String, Double> montants = calculerMontantTotalParArticle(facturation.getNumClient(), commandes);
            float montantTotal = 0.0f;
            for (Double montant : montants.values()) {
                montantTotal += montant;
            }
            facturation.setMontant(montantTotal);
        }
    }
    
    // Vérifie s'il y a des erreurs dans la facturation et les gère, puis les ajoute dans un tableau associatif, renvoie vrai si le tableau est vide
    public boolean verifPaiement() {
        for (Facturation facturation : tabFacturation.getItems()) {
            List<String> erreurs = erreurClientsFacturation.getOrDefault(facturation.getNumClient(), new ArrayList<>());

            if (!facturation.isPaiement() && !erreurs.contains("Erreur de paiement")) {
                erreurs.add("Erreur de paiement");
            }

            if (!erreurs.isEmpty()) {
                erreurClientsFacturation.put(facturation.getNumClient(), erreurs);
            }
        }

        return erreurClientsFacturation.isEmpty();
    }
    
    //Méthodes liées au FXML
    @FXML
    void toLivraison(ActionEvent event) throws Exception {
        // permet d'enlever la scene au centre de la BorderPane et de mettre la scene Livraison
        mainApp.getRootLayout().getChildren().remove(mainApp.getRootLayout().getCenter());
        mainApp.showLivraison();
    }

    @FXML
    void toDetailErreur(ActionEvent event) throws Exception {
        // permet d'enlever la scene au centre de la BorderPane et de mettre la scene Livraison
        mainApp.getRootLayout().getChildren().remove(mainApp.getRootLayout().getCenter());
        mainApp.showDetailErreur();
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public Map<Integer, List<String>> getErreurClientsFacturation() {
        return erreurClientsFacturation;
    }

    public void setErreurClientsFacturation(Map<Integer, List<String>> erreurClientsFacturation) {
        this.erreurClientsFacturation = erreurClientsFacturation;
    }
}
