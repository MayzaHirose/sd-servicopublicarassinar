
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
public class Assinante1Impl extends java.rmi.server.UnicastRemoteObject implements IAssinante1 {
    
    public Assinante1Impl()
            throws java.rmi.RemoteException {
            super();
    }

    @Override
    public boolean notify(String conteudo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
