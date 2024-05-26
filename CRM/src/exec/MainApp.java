package exec;
import controller.RootLayoutController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.*;
//import modele.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class MainApp extends Application{

	private Stage stage;
    private BorderPane rootLayout;
    
    private CommandeController commandeController;
    private LivraisonController livraisonController;
    private FacturationController facturationController;
    private DetailErreurController detailErreurController;
    private RootLayoutController rootLayoutController;

	@Override
	public void start(Stage stage) throws Exception {

    	this.stage = stage;
    	initRootLayout();
    	showCommande();
    	// empeche de redimensionner la fenêtre
    	this.stage.setMaxWidth(this.stage.getWidth());
        this.stage.setMinWidth(this.stage.getWidth());
    	this.stage.setMaxHeight(this.stage.getHeight());
        this.stage.setMinHeight(this.stage.getHeight());
    }

    public void initRootLayout() throws Exception {
        try {
            // Charge le fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(Config.ROOTLAYOUT));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);

            // Donne au controller l'accès à mainApp
            rootLayoutController = loader.getController();
            rootLayoutController.setMainApp(this);
            // Affiche la scene
            stage.show();
        } catch (Exception e) {
            throw new Exception("Un problème est survenu veuillez retélécharger l'application");
        }
    }
    
    public void showCommande() throws Exception {
        try {
        	// Charge le fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(Config.COMMANDE));
            AnchorPane commandeLayout = (AnchorPane) loader.load();
            rootLayout.setCenter(commandeLayout);
            
            // Donne au controller l'accès à mainApp
            commandeController = loader.getController();
            commandeController.setMainApp(this);
            boolean quantiteDispo = commandeController.verifQuantiteDispo();
             
            // Affiche un message d'erreur si la quantite commandée par un client est trop élevée OU si elle dépasse la quantite totale de l'article
            if(!quantiteDispo) {
            	showAlert(AlertType.WARNING,"ERREUR DE STOCK","WARNING","Au moins une erreur est survenue et sera présente dans le rapport");
            }
            // gère le css des boutons
            rootLayoutController.clearBtn();
            rootLayoutController.getBtnLivraison().getStyleClass().add("button-white");
            rootLayoutController.getBtnFacturation().getStyleClass().add("button-white");
            rootLayoutController.getBtnDetailErreur().getStyleClass().add("button-white");
            rootLayoutController.getBtnCommande().getStylesheets().add(getClass().getResource(Config.CSS).toExternalForm());
            rootLayoutController.getBtnCommande().getStyleClass().add("button-blue");
            // Affiche la scene
            stage.show();
            
        } catch (Exception e) {
            throw new Exception("Un problème est survenu veuillez retélécharger l'application");
        }
    }
    
    public void showLivraison() throws Exception {
        try {
        	
        	// Charge le fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(Config.LIVRAISON));
            AnchorPane commandeLayout = (AnchorPane) loader.load();
            rootLayout.setCenter(commandeLayout);
            
            // Donne au controller l'accès à mainApp
            livraisonController = loader.getController();
            livraisonController.setMainApp(this);
            boolean estLivre = livraisonController.verifLivraison();
            // Affiche un message d'erreur si la livraison
            if(!estLivre) {
            	showAlert(AlertType.WARNING,"ERREUR DE LIVRAISON","WARNING","Au moins une erreur est survenue et sera présente dans le rapport");
            }
            // gère le css des boutons
            rootLayoutController.clearBtn();
            rootLayoutController.getBtnCommande().getStyleClass().add("button-white");
            rootLayoutController.getBtnFacturation().getStyleClass().add("button-white");
            rootLayoutController.getBtnDetailErreur().getStyleClass().add("button-white");
            rootLayoutController.getBtnLivraison().getStylesheets().add(getClass().getResource(Config.CSS).toExternalForm());
            rootLayoutController.getBtnLivraison().getStyleClass().add("button-blue");
            // Affiche la scene
            stage.show();
        } catch (Exception e) {
            throw new Exception("Un problème est survenu veuillez retélécharger l'application");
        }
    }
    
    public void showFacturation() throws Exception {
        try {
        	
        	// Charge le fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(Config.FACTURATION));
            AnchorPane commandeLayout = (AnchorPane) loader.load();
            rootLayout.setCenter(commandeLayout);
            
            // Donne au controller l'accès à mainApp
            facturationController = loader.getController();
            facturationController.setMainApp(this);
            facturationController.mettreAJourMontants(commandeController.getCommandes());
            boolean estPaye = facturationController.verifPaiement();
            // Affiche un message d'erreur si la livraison
            if(!estPaye) {
            	showAlert(AlertType.WARNING,"ERREUR DE FACTURATION","WARNING","Au moins une erreur est survenue et sera présente dans le rapport");
            }
            // gère le css des boutons
            rootLayoutController.clearBtn();
            rootLayoutController.getBtnCommande().getStyleClass().add("button-white");
            rootLayoutController.getBtnLivraison().getStyleClass().add("button-white");
            rootLayoutController.getBtnDetailErreur().getStyleClass().add("button-white");
            rootLayoutController.getBtnFacturation().getStylesheets().add(getClass().getResource(Config.CSS).toExternalForm());
            rootLayoutController.getBtnFacturation().getStyleClass().add("button-blue");
            // Affiche la scene
            stage.show();
        } catch (Exception e) {
            throw new Exception("Un problème est survenu veuillez retélécharger l'application");
        }
    }
    
    public void showDetailErreur() throws Exception {
        try {
            // Charge le fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(Config.DETAILERREUR));
            AnchorPane commandeLayout = (AnchorPane) loader.load();
            rootLayout.setCenter(commandeLayout);

            // Donne au controller l'accès à mainApp
            detailErreurController = loader.getController();
            detailErreurController.setMainApp(this);
            
            // Fusionne les erreurs de toutes les sources
            @SuppressWarnings("unchecked")
			Map<Integer, List<String>> mergedErrors = fusionnerMaps(
                commandeController.getErreurClientsCommande(),
                livraisonController.getErreurClientsLivraison(),
                facturationController.getErreurClientsFacturation()
            );
            
            // Passe les erreurs fusionnées au DetailErreurController
            detailErreurController.setErreurData(mergedErrors);
            
            // gère le css des boutons
            rootLayoutController.clearBtn();
            rootLayoutController.getBtnCommande().getStyleClass().add("button-white");
            rootLayoutController.getBtnLivraison().getStyleClass().add("button-white");
            rootLayoutController.getBtnFacturation().getStyleClass().add("button-white");
            rootLayoutController.getBtnDetailErreur().getStylesheets().add(getClass().getResource(Config.CSS).toExternalForm());
            rootLayoutController.getBtnDetailErreur().getStyleClass().add("button-blue");
            
            // Affiche la scene
            stage.show();
        } catch (Exception e) {
            throw new Exception("Un problème est survenu veuillez retélécharger l'application");
        }
    }

    public Map<Integer, List<String>> fusionnerMaps(Map<Integer, List<String>>... maps) {
        Map<Integer, List<String>> fusion = new HashMap<>();
        for (Map<Integer, List<String>> map : maps) {
            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                fusion.merge(entry.getKey(), entry.getValue(), (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                });
            }
        }
        return fusion;
    }
    
    // Afficher message d'alerte
    public static void showAlert(AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
    public static void main(String[] args) {
    	launch(args);
    }
    
    // Getters & Setters
    public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
    public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
    
}
