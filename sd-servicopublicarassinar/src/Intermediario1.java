
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
public class Intermediario1 {
    
    public Intermediario1() {
        try {
            IIntermediario1 inter1 = new Intermediario1Impl();
            Naming.rebind("//127.0.0.1:1099/Intermediario1Service", inter1);  
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
        new Intermediario1();
    }
    
}
