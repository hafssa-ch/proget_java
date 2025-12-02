/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Lenovo
 */
public class Client {
    private int id_client ;
    private String nom;
    private String prenom;
    private String ville;
    private String email;

    public Client(int id_client, String nom, String prenom, String ville, String email) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.email = email;
    }

    public Client(String nom, String prenom, String ville, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.email = email;
    }

    public Client() {
    }
    
    public int getId() {
    return id_client; // retourne l'identifiant du client
}

public void setId(int id) {
    this.id_client = id;
}


    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getVille() {
        return ville;
    }

    public String getEmail() {
        return email;
    }
    
 
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setEmail(String email) {
        this.email = email;
    }
@Override
public String toString() {
    return nom + " " + prenom; // ou juste nom si tu préfères
}


    
}
