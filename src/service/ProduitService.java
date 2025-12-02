package service;

import connexion.Connexion;
import dao.IDao;
import entities.Produit;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class ProduitService implements IDao<Produit> {

    @Override
    public boolean create(Produit o) {
        try {
            String req = "INSERT INTO produit (libelle, categorie, prix, stock) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getLibelle());
            ps.setString(2, o.getCategorie());
            ps.setDouble(3, o.getPrix());
            ps.setInt(4, o.getStock());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Produit o) {
        try {
            String req = "UPDATE produit SET libelle=?, categorie=?, prix=?, stock=? WHERE id_produit=?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getLibelle());
            ps.setString(2, o.getCategorie());
            ps.setDouble(3, o.getPrix());
            ps.setInt(4, o.getStock());
            ps.setInt(5, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Produit o) {
        try {
            String req = "DELETE FROM produit WHERE id_produit=?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Produit findById(int id) {
        try {
            String req = "SELECT * FROM produit WHERE id_produit=?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Produit(
                    rs.getInt("id_produit"),
                    rs.getString("libelle"),
                    rs.getString("categorie"),
                    rs.getDouble("prix"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Produit> findAll() {
        List<Produit> produits = new ArrayList<>();
        try {
            String req = "SELECT * FROM produit";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                produits.add(new Produit(
                    rs.getInt("id_produit"),
                    rs.getString("libelle"),
                    rs.getString("categorie"),
                    rs.getDouble("prix"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produits;
    }
    public List<String> findCategories() {
        List<String> categories = new ArrayList<>();
        try {
            String req = "SELECT DISTINCT categorie FROM produit";
            Statement st = Connexion.getConnection().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                categories.add(rs.getString("categorie"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categories;
    }

    public List<Produit> findByCategorie(String categorie) {
    List<Produit> produits = new ArrayList<>();
    try {
        String req = "SELECT * FROM produit WHERE categorie = ?"; // Assurez-vous que le nom de colonne est correct
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, categorie);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            produits.add(new Produit(
                rs.getInt("id_produit"),
                rs.getString("libelle"),
                rs.getString("categorie"),
                rs.getDouble("prix"),
                rs.getInt("stock")
            ));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return produits;
}

}
