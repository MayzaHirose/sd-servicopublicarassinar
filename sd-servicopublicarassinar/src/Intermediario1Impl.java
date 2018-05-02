
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
        List<Intermediarios> temp = tabelaRoteamento.get(topico);
        if(!temp.isEmpty()){
            for(Intermediarios i: temp){
                System.out.println("O " + i.getNome() + " tem interesse no " + topico.getNome());
                System.out.println("Repassando para o " + i.getNome());
            }
            inter2.publishAlert(topico, false);
            System.out.println("--------------------------");
            return true;
        }
        System.out.println("Nao ha interessados no " + topico.getNome() + ". Nenhum intermediario sera notificado");
        return true;
    }
    
    @Override
    public boolean setSubscriber(Topicos topico, Intermediarios inter_interessado){
        System.out.println("Recebendo Inscricao do " + inter_interessado.getNome() + " para o " + topico.getNome());
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
       
}
