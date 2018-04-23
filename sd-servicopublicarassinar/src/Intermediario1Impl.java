
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
    
    public Intermediario1Impl()
            throws java.rmi.RemoteException {
            super();
    }

    @Override
    public boolean publish(Topicos topico) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
