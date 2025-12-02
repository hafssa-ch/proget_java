/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import entities.Client;
import service.ClientService;

/**
 *
 * @author Lenovo
 */
public class TestClient {
      public static void main(String[] args) {
          ClientService ct= new ClientService();
          
         // ct.create(new Client("asma","laouy","safi","asma@gmail.com"));
         // ct.create(new Client("salma","laouy","safi","salma@gmail.com"));
          
          ct.delete(ct.findById(1));
          for(Client c : ct.findAll())
              System.out.println(c);
          
        
    }

    
}
