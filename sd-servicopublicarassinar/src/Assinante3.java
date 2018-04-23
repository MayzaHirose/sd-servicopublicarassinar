
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
public class Assinante3 {
    
    public Assinante3() {
        try {
            IIntermediario3 inter3 = (IIntermediario3) Naming.lookup("//127.0.0.1:1099/Intermediario3Service");
            IAssinante3 assinante3 = new Assinante3Impl();
            Naming.rebind("//127.0.0.1:1099/Assinante3Service", assinante3);   
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
        new Assinante3();
    }
    
}
