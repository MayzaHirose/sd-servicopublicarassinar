
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
    
    public Intermediario1Impl(IIntermediario2 inter2)
            throws java.rmi.RemoteException {
            super();
            this.inter2 = inter2;
    }

    @Override
    public boolean publish(Topicos topico) throws RemoteException {
        inter2.publishAlert(topico, false);
        return true;
    }
    
}
