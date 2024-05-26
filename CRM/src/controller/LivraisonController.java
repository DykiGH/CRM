package controller;

import modele.Livraison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exec.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;


import javafx.scene.control.cell.PropertyValueFactory;

public class LivraisonController {
    // Reference to the main application.
    private MainApp mainApp;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    //Champs
    private Map<Integer, List<String>> erreurClientsLivraison = new HashMap<>();

    //Champs FXML
    @FXML
    private TableView<Livraison> tabLivraison;
    @FXML
    private TableColumn<Livraison, Boolean> fxClientLivre;

    @FXML
    private TableColumn<Livraison, Integer> fxNumLivraison;

    @FXML
    private TableColumn<Livraison, Integer> fxnumClient;
    
    @FXML
    private Button btnPrecedentLivraison;

    @FXML
    private Button btnSuivantLivraison;
    
    public LivraisonController() {
    }
    
    @FXML
    private void initialize() {
    	fxnumClient.setCellValueFactory(new PropertyValueFactory<Livraison, Integer>("numClient"));
    	fxNumLivraison.setCellValueFactory(new PropertyValueFactory<Livraison, Integer>("numLivraison"));
    	fxClientLivre.setCellValueFactory(new PropertyValueFactory<Livraison, Boolean>("colisLivre"));
    	
        // créer des articles à mettre dans le tableau de commandes
    	tabLivraison.getItems().add(new Livraison(12,1,true));
    	tabLivraison.getItems().add(new Livraison(13,1,true));
    	tabLivraison.getItems().add(new Livraison(14,1,false));
    	tabLivraison.getItems().add(new Livraison(87,1,true));
    	tabLivraison.getItems().add(new Livraison(102,1,true));
    	
        // Cell factory personnalisée pour afficher "Vrai" ou "Faux" au lieu de true ou false
        fxClientLivre.setCellFactory(column -> {
            return new TableCell<Livraison, Boolean>() {
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
    
    // Vérifie s'il y a des erreurs dans les Livraisons et les gère, puis les ajoute dans un tableau associatif, renvoie vrai si le tableau est vide
    public boolean verifLivraison() {
        for (Livraison livraison : tabLivraison.getItems()) {
            List<String> erreurs = erreurClientsLivraison.getOrDefault(livraison.getNumClient(), new ArrayList<>());

            if (!livraison.isColisLivre() && !erreurs.contains("Erreur de livraison")) {
                erreurs.add("Erreur de livraison");
            }

            if (!erreurs.isEmpty()) {
                erreurClientsLivraison.put(livraison.getNumClient(), erreurs);
            }
        }

        return erreurClientsLivraison.isEmpty();
    }

    
    //Méthodes liées au FXML
    @FXML
    void toCommande(ActionEvent event) throws Exception {
    	//permet d'enlever la scene au centre de la BorderPane et de mettre la scene commande
    	mainApp.getRootLayout().getChildren().remove(mainApp.getRootLayout().getCenter());
    	mainApp.showCommande();
    }

    @FXML
    void toFacturation(ActionEvent event) throws Exception {
    	mainApp.getRootLayout().getChildren().remove(mainApp.getRootLayout().getCenter());
    	mainApp.showFacturation();
    }
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public Map<Integer, List<String>> getErreurClientsLivraison() {
        return erreurClientsLivraison;
    }

    public void setErreurClientsLivraison(Map<Integer, List<String>> erreurClientsLivraison) {
        this.erreurClientsLivraison = erreurClientsLivraison;
    }
    
    
}