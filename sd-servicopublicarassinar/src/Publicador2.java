
import java.rmi.Naming;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
//CLIENTE
public class Publicador2 {
    
    public static void main(String[] args) {
        
        try {           
            IIntermediario3 inter3 = (IIntermediario3) Naming.lookup( "//127.0.0.1:1099/Intermediario3Service");                 
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
}
