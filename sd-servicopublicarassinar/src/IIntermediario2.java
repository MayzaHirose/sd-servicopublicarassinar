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
    
    public boolean subscribe(Topicos topico, Assinantes assinante)
            throws java.rmi.RemoteException;
    
    public boolean unsubscribe(Topicos topico, Assinantes assinante)
            throws java.rmi.RemoteException;
    
    //para notificar que uma nova publicacao chegou I3
    public boolean publishAlert(Topicos topico, boolean inter3)
            throws java.rmi.RemoteException;
    
    //para receber notificacao de nova inscricao em um topico
    public boolean subscribeAlert(Topicos topico)
            throws java.rmi.RemoteException;
    
    //para receber inscrito
    public boolean setSubscriber(Topicos topico, Intermediarios inter_interessado)
            throws java.rmi.RemoteException;
    
    public boolean unsubscribeAlert(Topicos topico, Intermediarios inter_interessado, boolean veioDoInter3)
            throws java.rmi.RemoteException;
    
}
