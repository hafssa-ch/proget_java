/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import entities.Produit;
import service.ProduitService;

/**
 *
 * @author Lenovo
 */
public class TestProduit {
    public static void main(String[] args) {

        ProduitService pt = new ProduitService();

        // insertion
        pt.create(new Produit("milk", "enfant", 12, 20));

        // suppression si trouvé
        Produit p = pt.findById(1);
        if (p != null) {
            pt.delete(p);
        }

        // affichage
        for (Produit pr : pt.findAll()) {
            System.out.println(pr);
        }
    }
}

