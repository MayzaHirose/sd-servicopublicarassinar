
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
public class Publicador1 {
    
    public static void main(String[] args) {
        
        try {           
            IIntermediario1 inter1 = (IIntermediario1) Naming.lookup( "//127.0.0.1:1099/Intermediario1Service");                
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
}
