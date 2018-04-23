
import java.rmi.Naming;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
//CLIENTE/SERVIDOR
public class Assinante2 {
    
    private boolean conectado = true;
    private int topico;
    private int acao;
    private boolean retorno;
    
    public Assinante2() {
        try {
            IIntermediario2 inter2 = (IIntermediario2) Naming.lookup("//127.0.0.1:1099/Intermediario2Service");
            IAssinante2 assinante2 = new Assinante2Impl();
            Naming.rebind("//127.0.0.1:1099/Assinante2Service", assinante2);   
            while(conectado) {
                System.out.println("-------------------------------------");
                System.out.println("-              TOPICOS              -");
                System.out.println("-------------------------------------\n\n");
                System.out.println("1 - " + Topicos.TOPICO_1 + "\n");
                System.out.println("2 - " + Topicos.TOPICO_2 + "\n");
                System.out.println("3 - " + Topicos.TOPICO_3 + "\n");
                System.out.println("4 - " + Topicos.TOPICO_4 + "\n");
                System.out.println("5 - " + Topicos.TOPICO_5 + "\n");
                Scanner entrada = new Scanner(System.in);
                topico = entrada.nextInt();
                
                System.out.println("\n\n1 - Subscribe\n");
                System.out.println("2 - Unsubscribe\n");
                acao = entrada.nextInt();
                switch(topico){
                    case 1:
                        if(acao == 1) {
                            retorno = inter2.subscribe(Topicos.TOPICO_1, Assinantes.ASSINANTE_2);
                        } else {
                            retorno = inter2.unsubscribe(Topicos.TOPICO_1, Assinantes.ASSINANTE_2);
                        }
                        break;
                    case 2:
                        if(acao == 1) {
                            retorno = inter2.subscribe(Topicos.TOPICO_2, Assinantes.ASSINANTE_2);
                        } else {
                            retorno = inter2.unsubscribe(Topicos.TOPICO_2, Assinantes.ASSINANTE_2);
                        }
                        break;
                    case 3:
                        if(acao == 1) {
                            retorno = inter2.subscribe(Topicos.TOPICO_3, Assinantes.ASSINANTE_2);
                        } else {
                            retorno = inter2.unsubscribe(Topicos.TOPICO_3, Assinantes.ASSINANTE_2);
                        }
                        break;
                    case 4:
                        if(acao == 1) {
                            retorno = inter2.subscribe(Topicos.TOPICO_4, Assinantes.ASSINANTE_2);
                        } else {
                            retorno = inter2.unsubscribe(Topicos.TOPICO_4, Assinantes.ASSINANTE_2);
                        }
                        break;
                    case 5:
                        if(acao == 1) {
                            retorno = inter2.subscribe(Topicos.TOPICO_5, Assinantes.ASSINANTE_2);
                        } else {
                            retorno = inter2.unsubscribe(Topicos.TOPICO_5, Assinantes.ASSINANTE_2);
                        }
                        break;
                }
                if(retorno){
                    if(acao == 1) {
                        System.out.println("Inscrito com sucesso.");
                    } else {
                        System.out.println("Inscricao cancelada com sucesso.");
                    }
                } else {
                    if(acao == 1) {
                        System.out.println("Você ja esta inscrito neste topico.");
                    } else {
                        System.out.println("Nao foi possivel cancelar a inscricao.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
        new Assinante2();
    }
    
}
