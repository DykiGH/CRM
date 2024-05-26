package modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Livraison extends InfoClient {

    private IntegerProperty numLivraison;
    private BooleanProperty colisLivre;

    public Livraison(int numClient, int numLivraison, boolean colisLivre) {
        super(numClient);
        this.numLivraison = new SimpleIntegerProperty(numLivraison);
        this.colisLivre = new SimpleBooleanProperty(colisLivre);
    }

    // Getters
    public int getNumLivraison() {
        return numLivraison.get();
    }

    public boolean isColisLivre() {
        return colisLivre.get();
    }

    // Setters
    public void setNumLivraison(int numLivraison) {
        this.numLivraison.set(numLivraison);
    }

    public void setColisLivre(boolean colisLivre) {
        this.colisLivre.set(colisLivre);
    }

    // Property getters
    public IntegerProperty numLivraisonProperty() {
        return numLivraison;
    }

    public BooleanProperty colisLivreProperty() {
        return colisLivre;
    }
}
