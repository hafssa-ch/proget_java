/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Vente {
    private int id;
    private Produit produit;
    private Client client;
    private Date date_venete;
    private int quantite;
    private int prix_unitaire;

    public Vente(int id, Produit produit, Client client, Date date_venete, int quantite, int prix_unitaire) {
        this.id = id;
        this.produit = produit;
        this.client = client;
        this.date_venete = date_venete;
        this.quantite = quantite;
        this.prix_unitaire = prix_unitaire;
    }

    public Vente() {
    }

    public Vente(Produit produit, Client client, Date date_venete, int quantite, int prix_unitaire) {
        this.produit = produit;
        this.client = client;
        this.date_venete = date_venete;
        this.quantite = quantite;
        this.prix_unitaire = prix_unitaire;
    }

    public int getId() {
        return id;
    }

    public Produit getProduit() {
        return produit;
    }

    public Client getClient() {
        return client;
    }

    public Date getDate_venete() {
        return date_venete;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDate_venete(Date date_venete) {
        this.date_venete = date_venete;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix_unitaire(int prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    @Override
    public String toString() {
        return "Vente{" + "id=" + id + ", produit=" + produit + ", client=" + client + ", date_venete=" + date_venete + ", quantite=" + quantite + ", prix_unitaire=" + prix_unitaire + '}';
    }
    
    
    
    
    
    
}
