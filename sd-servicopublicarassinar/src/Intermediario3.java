
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
//CLIENTE/SERVIDOR
public class Intermediario3 {
    
    public Intermediario3() {
        try {
            IIntermediario3 inter3 = new Intermediario3Impl();
            Naming.rebind("//127.0.0.1:1099/Intermediario3Service", inter3);    
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
        new Intermediario3();
    }
    
}
