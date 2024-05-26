package modele;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Commande extends InfoClient {
    private StringProperty article;
    private IntegerProperty quantite;
    private SimpleDoubleProperty prix;
    private IntegerProperty stock;

    public Commande(int numClient, String article, int quantite, double prix, int stock) {
        super(numClient);
        this.article = new SimpleStringProperty(article);
        this.quantite = new SimpleIntegerProperty(quantite);
        this.prix = new SimpleDoubleProperty(prix);
        this.stock = new SimpleIntegerProperty(stock);
    }

    // Getters
    public String getArticle() {
        return article.get();
    }

    public int getQuantite() {
        return quantite.get();
    }

    public double getPrix() {
        return prix.get();
    }

    public int getStock() {
        return stock.get();
    }

    // Setters
    public void setArticle(String article) {
        this.article.set(article);
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }

    public void setPrix(int prix) {
        this.prix.set(prix);
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    // Property getters
    public StringProperty articleProperty() {
        return article;
    }

    public IntegerProperty quantiteProperty() {
        return quantite;
    }

    public DoubleProperty prixProperty() {
        return prix;
    }

    public IntegerProperty stockProperty() {
        return stock;
    }
}
