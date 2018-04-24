
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
public class Intermediario3Impl extends java.rmi.server.UnicastRemoteObject implements IIntermediario3 {
    
    private static Map<Topicos, List<Assinantes>> inscritos = new HashMap<>();
    private IAssinante3 assinante3;
    private IIntermediario2 inter2;
    
    public Intermediario3Impl()
            throws java.rmi.RemoteException {
            super();
            for(Topicos topico: Topicos.values()){
                inscritos.put(topico, new ArrayList<>());
            }
    }

    @Override
    public boolean subscribe(Topicos topico, Assinantes assinante) throws RemoteException {
        List temp = inscritos.get(topico);
        if(!temp.contains(assinante)){
            temp.add(assinante);
            inscritos.put(topico, temp);
            return true;
        }      
        return false;
    }

    @Override
    public boolean unsubscribe(Topicos topico, Assinantes assinante) throws RemoteException {
        List temp = inscritos.get(topico);
        if(temp.contains(assinante)){
            temp.remove(assinante);
            inscritos.put(topico, temp);
            return true;
        } 
        return false; 
    }
    
    @Override
    public boolean publish(Topicos topico) throws RemoteException { 
        return publishAlert(topico, false);
    }
    
    @Override
    public boolean publishAlert(Topicos topico, boolean repassado) throws RemoteException {
        System.out.println("Nova publicacao recebida!");
        try {
            assinante3 = (IAssinante3) Naming.lookup("//127.0.0.1:1099/Assinante3Service");
            inter2 = (IIntermediario2) Naming.lookup("//127.0.0.1:1099/Intermediario2Service");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
        List<Assinantes> temp = inscritos.get(topico);
        for(Assinantes a: temp){
            if(a.equals(Assinantes.ASSINANTE_3)){
                System.out.println("Notificando o Assinante 3");
                this.assinante3.notify("CONTEUDO DO TOPICO: " + topico.getNome());
            } 
        }
        if(!repassado){
            System.out.println("Repassando para Intermediario 2");
            this.inter2.publishAlert(topico, true);
        }
        return true;
    }
   
}
