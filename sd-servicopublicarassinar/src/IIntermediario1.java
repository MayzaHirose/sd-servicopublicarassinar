/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
public interface IIntermediario1 extends java.rmi.Remote {
    
    public boolean publish(Topicos topico)
            throws java.rmi.RemoteException;
    
    public boolean setSubscriber(Topicos topico, Intermediarios inter_interessado)
            throws java.rmi.RemoteException;
    
    public boolean setUnsubscriber(Topicos topico, Intermediarios inter_interessado)
            throws java.rmi.RemoteException;
    
    public boolean unsubscribeAlert(Topicos topico, Intermediarios inter_interessado)
            throws java.rmi.RemoteException;
}
