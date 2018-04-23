
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
public class Intermediario2 {
    
    public Intermediario2() {
        try {
            IIntermediario2 inter2 = new Intermediario2Impl();
            Naming.rebind("//127.0.0.1:1099/Intermediario2Service", inter2);    
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
        new Intermediario2();
    }
    
}
