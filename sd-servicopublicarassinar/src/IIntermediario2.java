/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
public interface IIntermediario2 extends java.rmi.Remote {
    
    public boolean subscribe(Topicos topico)
            throws java.rmi.RemoteException;
    
    public boolean unsubscribe(Topicos topico)
            throws java.rmi.RemoteException;
    
    //para notificar que uma nova publicacao chegou do I1 ou I3
    public boolean publishAlert(Topicos topico)
            throws java.rmi.RemoteException;
    
}
