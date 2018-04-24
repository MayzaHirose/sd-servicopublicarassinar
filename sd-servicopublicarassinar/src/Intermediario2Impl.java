
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
public class Intermediario2Impl extends java.rmi.server.UnicastRemoteObject implements IIntermediario2 {
    
    private static Map<Topicos, List<Assinantes>> inscritos = new HashMap<>();
    private IAssinante1 assinante1;
    private IAssinante2 assinante2;
    private IIntermediario3 inter3;
    
    public Intermediario2Impl()
            throws java.rmi.RemoteException {
            super();
            for(Topicos topico: Topicos.values()){
                inscritos.put(topico, new ArrayList<>());
            }
    }

    @Override
    public boolean subscribe(Topicos topico, Assinantes assinante) throws RemoteException {
        System.out.println("Recebendo solicitacao de assinatura do " + assinante.getNome() + " para o " + topico.getNome());
        List temp = inscritos.get(topico);
        if(!temp.contains(assinante)){
            temp.add(assinante);
            inscritos.put(topico, temp);
            System.out.println("O " + assinante.getNome() + " foi inscrito");
            System.out.println("--------------------------");
            return true;
        }      
        System.out.println("O " + assinante.getNome() + " ja estava inscrito");
        System.out.println("--------------------------");
        return false;
    }

    @Override
    public boolean unsubscribe(Topicos topico, Assinantes assinante) throws RemoteException {
        System.out.println("Recebendo solicitacao de cancelamento do " + assinante.getNome() + " para o " + topico.getNome());
        List temp = inscritos.get(topico);
        if(temp.contains(assinante)){
            temp.remove(assinante);
            inscritos.put(topico, temp);
            System.out.println("O " + assinante.getNome() + " foi cancelado");
            System.out.println("--------------------------");
            return true;
        } 
        System.out.println("O " + assinante.getNome() + " nao estava inscrito");
        System.out.println("--------------------------");
        return false;       
    }

    @Override
    public boolean publishAlert(Topicos topico, boolean repassado) throws RemoteException {
        System.out.println("Nova publicacao recebida do " + topico.getNome());
        try {
            assinante1 = (IAssinante1) Naming.lookup("//127.0.0.1:1099/Assinante1Service");
            assinante2 = (IAssinante2) Naming.lookup("//127.0.0.1:1099/Assinante2Service");
            inter3 = (IIntermediario3) Naming.lookup("//127.0.0.1:1099/Intermediario3Service");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
        List<Assinantes> temp = inscritos.get(topico);
        if(temp.size() == 0){
            System.out.println("Nao ha inscritos para o " + topico.getNome() + " aqui.");
        }
        for(Assinantes a: temp){
            if(a.equals(Assinantes.ASSINANTE_1)){
                System.out.println("Notificando o Assinante 1");
                this.assinante1.notify("CONTEUDO DO TOPICO: " + topico.getNome());
            } else if(a.equals(Assinantes.ASSINANTE_2)) {
                System.out.println("Notificando o Assinante 2");
                this.assinante2.notify("CONTEUDO DO TOPICO: " + topico.getNome());
            } 
        }
        if(!repassado){
            System.out.println("Repassando para Intermediario 3");
            this.inter3.publishAlert(topico, true);
        }
        System.out.println("--------------------------");
        return true;
    }
    
}
