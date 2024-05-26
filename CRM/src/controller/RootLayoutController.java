package controller;

import exec.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RootLayoutController {
    // Reference to the main application.
    @SuppressWarnings("unused")
	private MainApp mainApp;
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    
    @FXML
    private Button btnCommande;

    @FXML
    private Button btnDetailErreur;

    @FXML
    private Button btnFacturation;

    @FXML
    private Button btnLivraison;
    public RootLayoutController() {
    }
    
    @FXML
    private void initialize() {
    }
    
    public void clearBtn() {
    	this.getBtnCommande().getStyleClass().clear();
    	this.getBtnLivraison().getStyleClass().clear();
    	this.getBtnFacturation().getStyleClass().clear();
    	this.getBtnDetailErreur().getStyleClass().clear();
    }
    
    // getters & setters
    public Button getBtnCommande() {
		return btnCommande;
	}

	public void setBtnCommande(Button btnCommande) {
		this.btnCommande = btnCommande;
	}

	public Button getBtnDetailErreur() {
		return btnDetailErreur;
	}

	public void setBtnDetailErreur(Button btnDetailErreur) {
		this.btnDetailErreur = btnDetailErreur;
	}

	public Button getBtnFacturation() {
		return btnFacturation;
	}

	public void setBtnFacturation(Button btnFacturation) {
		this.btnFacturation = btnFacturation;
	}

	public Button getBtnLivraison() {
		return btnLivraison;
	}

	public void setBtnLivraison(Button btnLivraison) {
		this.btnLivraison = btnLivraison;
	}

	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}