
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
public class Intermediario1Impl extends java.rmi.server.UnicastRemoteObject implements IIntermediario1 {
    
    private static Map<Topicos, List<Intermediarios>> tabelaRoteamento = new HashMap<>();
    private IIntermediario2 inter2;
    
    public Intermediario1Impl()
            throws java.rmi.RemoteException {
            super();
            for(Topicos topico: Topicos.values()){
                tabelaRoteamento.put(topico, new ArrayList<>());
            }
    }

    @Override
    public boolean publish(Topicos topico) throws RemoteException {
        System.out.println("Nova publicacao recebida do Publicador 1 sobre o " + topico.getNome());
        try {
            inter2 = (IIntermediario2) Naming.lookup("//127.0.0.1:1099/Intermediario2Service"); 
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
            return false;
        }
        List<Intermediarios> inscritos = tabelaRoteamento.get(topico);
        if(!inscritos.isEmpty()){
            //for(Intermediarios i: inscritos){
                System.out.println("O intermediario 2 tem interesse no " + topico.getNome());
                System.out.println("Repassando para o intermediario 2");
                System.out.println("--------------------------");
            //}
            inter2.publishAlert(topico, false);
            return true;
        }
        System.out.println("Nao ha interessados no " + topico.getNome() + ". Nenhum intermediario sera notificado");
        System.out.println("--------------------------");
        return true;
    }
    
    @Override
    public boolean setSubscriber(Topicos topico, Intermediarios inter_interessado){
        System.out.println("Recebendo Inscricao do " + inter_interessado.getNome() + " para o " + topico.getNome());
        System.out.println("--------------------------");
        List<Intermediarios> temp = tabelaRoteamento.get(topico);
        temp.add(inter_interessado);
        tabelaRoteamento.put(topico, temp);
        return true;
    }

    @Override
    public boolean unsubscribeAlert(Topicos topico, Intermediarios inter_interessado) throws RemoteException {
        List<Intermediarios> temp = tabelaRoteamento.get(topico);
        temp.remove(inter_interessado);
        tabelaRoteamento.put(topico, temp);
        return true;
    }
    
    @Override
    public boolean setUnsubscriber(Topicos topico, Intermediarios inter_interessado){
        System.out.println("Recebendo cancelamento do " + inter_interessado.getNome() + " para o " + topico.getNome());
        System.out.println("--------------------------");
        List<Intermediarios> temp = tabelaRoteamento.get(topico);
        temp.remove(inter_interessado);
        tabelaRoteamento.put(topico, temp);
        return true;
    }

       
}
