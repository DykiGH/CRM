package modele;

import javafx.beans.property.*;

public class Facturation extends InfoClient {

    private IntegerProperty numFacture;
    private StringProperty adresseFacturation;
    private FloatProperty montant;
    private BooleanProperty paiement;

    // Les paramètres doivent être des objets
    public Facturation(int numClient, int numFacture, String adresseFacturation, float montant, boolean paiement) {
        super(numClient);
        this.numFacture = new SimpleIntegerProperty(numFacture);
        this.adresseFacturation = new SimpleStringProperty(adresseFacturation);
        this.montant = new SimpleFloatProperty(montant);
        this.paiement = new SimpleBooleanProperty(paiement);
    }

    // Getters
    public int getNumFacture() {
        return numFacture.get();
    }

    public String getAdresseFacturation() {
        return adresseFacturation.get();
    }

    public float getMontant() {
        return montant.get();
    }

    public boolean isPaiement() {
        return paiement.get();
    }

    // Setters
    public void setNumFacture(int numFacture) {
        this.numFacture.set(numFacture);
    }

    public void setAdresseFacturation(String adresseFacturation) {
        this.adresseFacturation.set(adresseFacturation);
    }

    public void setMontant(float montant) {
        this.montant.set(montant);
    }

    public void setPaiement(boolean paiement) {
        this.paiement.set(paiement);
    }

    // Property getters
    public IntegerProperty numFactureProperty() {
        return numFacture;
    }

    public StringProperty adresseFacturationProperty() {
        return adresseFacturation;
    }

    public FloatProperty montantProperty() {
        return montant;
    }

    public BooleanProperty paiementProperty() {
        return paiement;
    }
}
