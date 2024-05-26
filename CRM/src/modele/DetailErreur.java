package modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DetailErreur extends InfoClient {
    private StringProperty detailErreur;

    public DetailErreur(int numClient, String detailErreur) {
        super(numClient);
        this.detailErreur = new SimpleStringProperty(detailErreur);
    }

    // Getter
    public String getDetailErreur() {
        return detailErreur.get();
    }

    // Setter
    public void setDetailErreur(String detailErreur) {
        this.detailErreur.set(detailErreur);
    }

    // Property getter
    public StringProperty detailErreurProperty() {
        return detailErreur;
    }
}
