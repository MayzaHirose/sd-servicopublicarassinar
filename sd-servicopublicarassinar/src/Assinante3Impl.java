
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
public class Assinante3Impl extends java.rmi.server.UnicastRemoteObject implements IAssinante3 {
    
    public Assinante3Impl()
            throws java.rmi.RemoteException {
            super();
    }

    @Override
    public boolean notify(String conteudo) throws RemoteException {
        System.out.println(conteudo);
        return true;
    }
    
}
