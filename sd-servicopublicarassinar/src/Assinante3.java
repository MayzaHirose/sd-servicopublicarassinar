
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
public class Assinante3 {
    
    private boolean conectado = true;
    private int topico;
    private int acao;
    private boolean retorno;
    
    public Assinante3() {
        try {
            IIntermediario3 inter3 = (IIntermediario3) Naming.lookup("//127.0.0.1:1099/Intermediario3Service");
            IAssinante3 assinante3 = new Assinante3Impl();
            Naming.rebind("//127.0.0.1:1099/Assinante3Service", assinante3);  
            while(conectado) {
                System.out.println("-------------------------------------");
                System.out.println("-              TOPICOS              -");
                System.out.println("-------------------------------------\n");
                System.out.println("1 - " + Topicos.TOPICO_1);
                System.out.println("2 - " + Topicos.TOPICO_2);
                System.out.println("3 - " + Topicos.TOPICO_3);
                System.out.println("4 - " + Topicos.TOPICO_4);
                System.out.println("5 - " + Topicos.TOPICO_5);
                Scanner entrada = new Scanner(System.in);
                topico = entrada.nextInt();
               
                System.out.println("\n1 - Subscribe\n");
                System.out.println("2 - Unsubscribe\n");
                acao = entrada.nextInt();
                switch(topico){
                    case 1:
                        if(acao == 1) {
                            retorno = inter3.subscribe(Topicos.TOPICO_1, Assinantes.ASSINANTE_3);
                        } else {
                            retorno = inter3.unsubscribe(Topicos.TOPICO_1, Assinantes.ASSINANTE_3);
                        }
                        break;
                    case 2:
                        if(acao == 1) {
                            retorno = inter3.subscribe(Topicos.TOPICO_2, Assinantes.ASSINANTE_3);
                        } else {
                            retorno = inter3.unsubscribe(Topicos.TOPICO_2, Assinantes.ASSINANTE_3);
                        }
                        break;
                    case 3:
                        if(acao == 1) {
                            retorno = inter3.subscribe(Topicos.TOPICO_3, Assinantes.ASSINANTE_3);
                        } else {
                            retorno = inter3.unsubscribe(Topicos.TOPICO_3, Assinantes.ASSINANTE_3);
                        }
                        break;
                    case 4:
                        if(acao == 1) {
                            retorno = inter3.subscribe(Topicos.TOPICO_4, Assinantes.ASSINANTE_3);
                        } else {
                            retorno = inter3.unsubscribe(Topicos.TOPICO_4, Assinantes.ASSINANTE_3);
                        }
                        break;
                    case 5:
                        if(acao == 1) {
                            retorno = inter3.subscribe(Topicos.TOPICO_5, Assinantes.ASSINANTE_3);
                        } else {
                            retorno = inter3.unsubscribe(Topicos.TOPICO_5, Assinantes.ASSINANTE_3);
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
                        System.out.println("Voce nao esta inscrito neste topico.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }

    public static void main(String args[]) {
        new Assinante3();
    }
    
}
