
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
    private static Map<Topicos, List<Intermediarios>> tabelaRoteamento = new HashMap<>();
    private IAssinante3 assinante3;
    private IIntermediario2 inter2;
    
    public Intermediario3Impl()
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
            unsubscribeAlert(topico, Intermediarios.INTER_3, false);
            return true;
        } 
        System.out.println("O " + assinante.getNome() + " nao estava inscrito");
        System.out.println("--------------------------");
        return false; 
    }
    
    @Override
    public boolean publish(Topicos topico) throws RemoteException { 
        return publishAlert(topico, false);
    }
    
    @Override
    public boolean publishAlert(Topicos topico, boolean veioDoInter2) throws RemoteException {
        System.out.println("Nova publicacao recebida do " + topico.getNome());
        try {           
            assinante3 = (IAssinante3) Naming.lookup("//127.0.0.1:1099/Assinante3Service");
            inter2 = (IIntermediario2) Naming.lookup("//127.0.0.1:1099/Intermediario2Service");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
            return false;
        }
        List<Assinantes> temp = inscritos.get(topico);
        for(Assinantes a: temp){
            if(a.equals(Assinantes.ASSINANTE_3)){
                System.out.println("O " + a.getNome() + " tem interesse no " + topico.getNome());
                System.out.println("Notificando o " + a.getNome());
                this.assinante3.notify("CONTEUDO DO TOPICO: " + topico.getNome());
            } 
        }
        //se nao veio do inter 2
        if(!veioDoInter2) {
            List<Intermediarios> temp2 = tabelaRoteamento.get(topico); 
            if(temp2.contains(Intermediarios.INTER_2)){                   
                System.out.println("O intermediario 2 tem interesse no " + topico.getNome());
                System.out.println("Repassando para o Intermediario 2");
                this.inter2.publishAlert(topico, true);
            }
        }
        System.out.println("--------------------------");
        return true;
    }
    
    @Override
    public boolean subscribeAlert(Topicos topico) throws RemoteException{
        System.out.println("Repassando a inscricao para o vizinho I2");
        try {
            inter2 = (IIntermediario2) Naming.lookup("//127.0.0.1:1099/Intermediario2Service");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
        inter2.setSubscriber(topico, Intermediarios.INTER_3);
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
    public boolean unsubscribeAlert(Topicos topico, Intermediarios inter_3, boolean veioDoInter2) throws RemoteException {
        System.out.println("Repassando o cancelamento para o vizinho I2");
        try {
            inter2 = (IIntermediario2) Naming.lookup("//127.0.0.1:1099/Intermediario2Service");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
        if(!veioDoInter2){
            inter2.unsubscribeAlert(topico, inter_3, true);
        }
        System.out.println("--------------------------");
        return true;
    }
    
    
      
}
