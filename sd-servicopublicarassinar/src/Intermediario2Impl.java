
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
public class Intermediario2Impl extends java.rmi.server.UnicastRemoteObject implements IIntermediario2 {
    
    private static Map<Topicos, List<Assinantes>> inscritos = new HashMap<>();
    private IAssinante1 assinante1;
    private IAssinante2 assinante2;
    private IIntermediario3 inter3;
    
    public Intermediario2Impl(IAssinante1 a1, IAssinante2 a2, IIntermediario3 i3)
            throws java.rmi.RemoteException {
            super();
            for(Topicos topico: Topicos.values()){
                inscritos.put(topico, new ArrayList<>());
            }
            this.assinante1 = a1;
            this.assinante2 = a2;
            this.inter3 = i3;
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
    public boolean publishAlert(Topicos topico, boolean repassado) throws RemoteException {
        List<Assinantes> temp = inscritos.get(topico);
        for(Assinantes a: temp){
            if(a.equals(Assinantes.ASSINANTE_1)){
                this.assinante1.notify("CONTEUDO DO TOPICO: " + topico.getNome());
            } else if(a.equals(Assinantes.ASSINANTE_2)) {
                this.assinante2.notify("CONTEUDO DO TOPICO: " + topico.getNome());
            } else if(!repassado){
                this.inter3.publishAlert(topico, true);
            }
        }
        return true;
    }
    
}
