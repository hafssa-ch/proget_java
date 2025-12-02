package Test;

import entities.Vente;
import entities.Produit;
import entities.Client;
import service.VenteService;
import service.ProduitService;
import service.ClientService;

import java.util.Date;

public class TestVente {
    public static void main(String[] args) {

        ProduitService ps = new ProduitService();
        ClientService cs = new ClientService();
        VenteService vs = new VenteService();

       
        Produit p = new Produit("Lait", "Enfant", 10.5, 50);
        ps.create(p);

        Produit pLast = ps.findAll().get(ps.findAll().size() - 1);

        
        
        Client c = new Client("Hassan","aghoram", "Rabat", "hassan@gmail.com");
        cs.create(c);

        Client cLast = cs.findAll().get(cs.findAll().size() - 1);

        
        Vente v = new Vente(
                pLast,         
                cLast,         
                new Date(),    
                3,             
                (int) pLast.getPrix() 
        );

        vs.create(v);

        
        System.out.println("Liste des ventes :");
        for (Vente vente : vs.findAll()) {
            System.out.println(vente);
        }

        
        Vente v1 = vs.findById(1);
        if (v1 != null)
            System.out.println("Vente ID=1 : " + v1);
        else
            System.out.println("Aucune vente avec ID=1");
    }
}
