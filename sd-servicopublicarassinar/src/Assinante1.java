
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
public class Assinante1 {

    public Assinante1() {
        try {
            IIntermediario2 inter2 = (IIntermediario2) Naming.lookup("//127.0.0.1:1099/Intermediario2Service");
            IAssinante1 assinante1 = new Assinante1Impl();
            Naming.rebind("//127.0.0.1:1099/Assinante1Service", assinante1);   
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
        new Assinante1();
    }
    
}
