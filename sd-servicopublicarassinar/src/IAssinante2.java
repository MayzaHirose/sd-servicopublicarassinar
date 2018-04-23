/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
public interface IAssinante2 extends java.rmi.Remote {

    //Notificado pelo I2
    public boolean notify(String conteudo)
            throws java.rmi.RemoteException;
    
}
