
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
    private static Map<Topicos, List<Intermediarios>> tabelaRoteamento = new HashMap<>();
    private IAssinante1 assinante1;
    private IAssinante2 assinante2;
    private IIntermediario1 inter1;
    private IIntermediario3 inter3;
    
    public Intermediario2Impl()
            throws java.rmi.RemoteException {
            super();
            for(Topicos topico: Topicos.values()){
                inscritos.put(topico, new ArrayList<>());
                tabelaRoteamento.put(topico, new ArrayList<>());
            }
    }

    @Override
    public boolean subscribe(Topicos topico, Assinantes assinante) throws RemoteException {
        System.out.println("Recebendo solicitacao de assinatura do " + assinante.getNome() + " para o " + topico.getNome());
        List<Assinantes> temp = inscritos.get(topico);
        if(!temp.contains(assinante)){
            temp.add(assinante);
            inscritos.put(topico, temp);
            System.out.println("O " + assinante.getNome() + " foi inscrito para o " + topico.getNome());
            subscribeAlert(topico);
            return true;
        }      
        System.out.println("O " + assinante.getNome() + " ja estava inscrito no " + topico.getNome());
        System.out.println("--------------------------");
        return false;
    }

    @Override
    public boolean unsubscribe(Topicos topico, Assinantes assinante) throws RemoteException {
        System.out.println("Recebendo solicitacao de cancelamento do " + assinante.getNome() + " para o " + topico.getNome());
        List<Assinantes> temp = inscritos.get(topico);
        if(temp.contains(assinante)){
            temp.remove(assinante);
            inscritos.put(topico, temp);
            System.out.println("O " + assinante.getNome() + " foi cancelado");
            unsubscribeAlert(topico, Intermediarios.INTER_2, false);
            return true;
        } 
        System.out.println("O " + assinante.getNome() + " nao estava inscrito");
        System.out.println("--------------------------");
        return false;       
    }

    @Override
    public boolean publishAlert(Topicos topico, boolean veioDoInter3) throws RemoteException {
        System.out.println("Nova publicacao recebida do " + topico.getNome());
        try {
            assinante1 = (IAssinante1) Naming.lookup("//127.0.0.1:1099/Assinante1Service");
            assinante2 = (IAssinante2) Naming.lookup("//127.0.0.1:1099/Assinante2Service");
            inter3 = (IIntermediario3) Naming.lookup("//127.0.0.1:1099/Intermediario3Service");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
        List<Assinantes> temp = inscritos.get(topico);
        for(Assinantes a: temp){
            if(a.equals(Assinantes.ASSINANTE_1)){
                System.out.println("Notificando o Assinante 1");
                this.assinante1.notify("CONTEUDO DO TOPICO: " + topico.getNome());
            } else if(a.equals(Assinantes.ASSINANTE_2)) {
                System.out.println("Notificando o Assinante 2");
                this.assinante2.notify("CONTEUDO DO TOPICO: " + topico.getNome());
            } 
        }
        //se nao veio do inter 3
        if(!veioDoInter3) {
            List<Intermediarios> temp2 = tabelaRoteamento.get(topico); 
            if(temp2.contains(Intermediarios.INTER_3)){                   
                System.out.println("O intermediario 3 tem interesse no " + topico.getNome());
                System.out.println("Repassando para o Intermediario 3");
                this.inter3.publishAlert(topico, true);
            }
        }
        System.out.println("--------------------------");
        return true;
    }
    
    @Override
    public boolean subscribeAlert(Topicos topico) throws RemoteException{
        System.out.println("Repassando a inscricao para os vizinhos I1 e I3");
        try {
            inter1 = (IIntermediario1) Naming.lookup("//127.0.0.1:1099/Intermediario1Service");
            inter3 = (IIntermediario3) Naming.lookup("//127.0.0.1:1099/Intermediario3Service");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
        inter1.setSubscriber(topico, Intermediarios.INTER_2);
        inter3.setSubscriber(topico, Intermediarios.INTER_2);
        System.out.println("--------------------------");
        return true;
    }
    
    @Override
    public boolean setSubscriber(Topicos topico, Intermediarios inter_interessado){
        System.out.println("Recebendo Inscricao do " + inter_interessado.getNome() + " para o " + topico.getNome());
        List<Intermediarios> temp = tabelaRoteamento.get(topico);
        temp.add(inter_interessado);
        return true;
    }

    @Override
    public boolean unsubscribeAlert(Topicos topico, Intermediarios inter_2, boolean veioDoInter3) throws RemoteException {
        System.out.println("Repassando o cancelamento para os vizinhos I1 e I3");
        try {
            inter1 = (IIntermediario1) Naming.lookup("//127.0.0.1:1099/Intermediario1Service");
            inter3 = (IIntermediario3) Naming.lookup("//127.0.0.1:1099/Intermediario3Service");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
        if(!veioDoInter3){
            inter1.unsubscribeAlert(topico, inter_2);
            inter3.unsubscribeAlert(topico, inter_2, true);
        }
        System.out.println("--------------------------");
        return true;
    }
    
}
