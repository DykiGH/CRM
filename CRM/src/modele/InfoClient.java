package modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InfoClient {

    public IntegerProperty numClient;

    public InfoClient(int numClient) {
    	this.numClient = new SimpleIntegerProperty(numClient);
    }

    public int getNumClient() {
        return numClient.get();
    }

    public void setNumClient(int numClient) {
        this.numClient.set(numClient);
    }

    public IntegerProperty numClientProperty() {
        return numClient;
    }
}
