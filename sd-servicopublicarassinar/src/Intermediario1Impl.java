
import java.rmi.Naming;
import java.rmi.RemoteException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
public class Intermediario1Impl extends java.rmi.server.UnicastRemoteObject implements IIntermediario1 {
    
    private IIntermediario2 inter2;
    
    public Intermediario1Impl()
            throws java.rmi.RemoteException {
            super();
    }

    @Override
    public boolean publish(Topicos topico) throws RemoteException {
        try {
            inter2 = (IIntermediario2) Naming.lookup("//127.0.0.1:1099/Intermediario2Service"); 
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
        System.out.println("Nova publicacao recebida! Sera repassada para o Intermediario 2.");
        inter2.publishAlert(topico, false);
        return true;
    }
    
}
