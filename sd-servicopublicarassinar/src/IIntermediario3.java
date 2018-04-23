/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
public interface IIntermediario3 extends java.rmi.Remote {
    
    public boolean subscribe(Topicos topico, Assinantes assinante)
            throws java.rmi.RemoteException;
    
    public boolean unsubscribe(Topicos topico, Assinantes assinante)
            throws java.rmi.RemoteException;
    
    public boolean publish(Topicos topico)
            throws java.rmi.RemoteException;
    
    //para notificar que uma nova publicacao chegou do I2 
    public boolean publishAlert(Topicos topico, boolean repassado)
            throws java.rmi.RemoteException;
    
}
