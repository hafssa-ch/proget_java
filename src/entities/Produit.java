package entities;

public class Produit {
    private int id;
    private String libelle;
    private String categorie;
    private double prix;
    private int stock;

    // Constructeur sans id (pour l'insertion)
    public Produit(String libelle, String categorie, double prix, int stock) {
        this.libelle = libelle;
        this.categorie = categorie;
        this.prix = prix;
        this.stock = stock;
    }

    // Constructeur avec id (pour la récupération depuis la DB)
    public Produit(int id, String libelle, String categorie, double prix, int stock) {
        this.id = id;
        this.libelle = libelle;
        this.categorie = categorie;
        this.prix = prix;
        this.stock = stock;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_produit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public String toString() {
        return libelle + " (" + categorie + ") - " + prix + " DH";
    }
}
