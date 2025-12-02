package service;

import connexion.Connexion;
import dao.IDao;
import entities.Vente;
import entities.Produit;
import entities.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class VenteService implements IDao<Vente> {

    ProduitService ps = new ProduitService();
    ClientService cs = new ClientService();

    @Override
    public boolean create(Vente v) {
        try {
            String req = "INSERT INTO vente(produit_id, client_id, date_vente, quantite, prix_unitaire) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = Connexion.getConnection().prepareStatement(req);
            stmt.setInt(1, v.getProduit().getId());
            stmt.setInt(2, v.getClient().getId());
            stmt.setDate(3, new Date(v.getDate_venete().getTime()));
            stmt.setInt(4, v.getQuantite());
            stmt.setInt(5, v.getPrix_unitaire());

            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Vente v) {
        try {
            String req = "UPDATE vente SET produit_id = ?, client_id = ?, date_vente = ?, quantite = ?, prix_unitaire = ? WHERE id = ?";
            PreparedStatement stmt = Connexion.getConnection().prepareStatement(req);

            stmt.setInt(1, v.getProduit().getId());
            stmt.setInt(2, v.getClient().getId());
            stmt.setDate(3, new Date(v.getDate_venete().getTime()));
            stmt.setInt(4, v.getQuantite());
            stmt.setInt(5, v.getPrix_unitaire());
            stmt.setInt(6, v.getId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Vente v) {
        try {
            String req = "DELETE FROM vente WHERE id = ?";
            PreparedStatement stmt = Connexion.getConnection().prepareStatement(req);
            stmt.setInt(1, v.getId());
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Vente findById(int id) {
        try {
            String req = "SELECT * FROM vente WHERE id = ?";
            PreparedStatement stmt = Connexion.getConnection().prepareStatement(req);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                Produit p = ps.findById(rs.getInt("produit_id"));
                Client c = cs.findById(rs.getInt("client_id"));

                return new Vente(
                        rs.getInt("id"),
                        p,
                        c,
                        rs.getDate("date_vente"),
                        rs.getInt("quantite"),
                        rs.getInt("prix_unitaire")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Vente> findAll() {
        List<Vente> ventes = new ArrayList<>();

        try {
            String req = "SELECT * FROM vente";
            PreparedStatement stmt = Connexion.getConnection().prepareStatement(req);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produit p = ps.findById(rs.getInt("produit_id"));
                Client c = cs.findById(rs.getInt("client_id"));

                ventes.add(new Vente(
                        rs.getInt("id"),
                        p,
                        c,
                        rs.getDate("date_vente"),
                        rs.getInt("quantite"),
                        rs.getInt("prix_unitaire")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(VenteService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ventes;
    }
    public List<String> findPeriodes() {
    List<String> periodes = new ArrayList<>();
    try {
        String req = "SELECT DISTINCT DATE_FORMAT(date_vente, '%Y-%m') as periode FROM vente";
        Statement st = Connexion.getConnection().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            periodes.add(rs.getString("periode"));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return periodes;
}

public List<Vente> findByPeriode(String periode) {
    List<Vente> ventes = new ArrayList<>();
    try {
        String req = "SELECT v.id, v.produit_id, v.client_id, v.date_vente, v.quantite, v.prix_unitaire, " +
                     "p.libelle, p.categorie, p.prix AS produit_prix, p.stock " +
                     "FROM vente v " +
                     "JOIN produit p ON v.produit_id = p.id_produit " +
                     "WHERE DATE_FORMAT(v.date_vente, '%Y-%m') = ? " +
                     "ORDER BY v.date_vente DESC";

        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, periode);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ventes.add(new Vente(
                rs.getInt("id"),
                new Produit(
                    rs.getInt("produit_id"),
                    rs.getString("libelle"),
                    rs.getString("categorie"),
                    rs.getDouble("produit_prix"),
                    rs.getInt("stock")
                ),
                null, // client si nécessaire
                rs.getDate("date_vente"),
                rs.getInt("quantite"),
                rs.getInt("prix_unitaire")
            ));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return ventes;
}
public Map<String, Double> getChiffreAffairesParMois() {
    Map<String, Double> data = new LinkedHashMap<>();
    try {
        String req = "SELECT DATE_FORMAT(date_vente, '%Y-%m') AS mois, "
                   + "SUM(quantite * prix_unitaire) AS ca "
                   + "FROM vente GROUP BY mois ORDER BY mois";

        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            data.put(rs.getString("mois"), rs.getDouble("ca"));
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return data;
}


}
