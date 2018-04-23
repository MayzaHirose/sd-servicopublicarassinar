
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
//CLIENTE
public class Publicador1 {
    
    public static void main(String[] args) {
        
        boolean conectado = true;
        int topico;
        boolean retorno = true;
        
        try {           
            IIntermediario1 inter1 = (IIntermediario1) Naming.lookup( "//127.0.0.1:1099/Intermediario1Service");
            while(conectado) {
                System.out.println("-------------------------------------");
                System.out.println("-        TOPICOS PARA ENVIAR        -");
                System.out.println("-------------------------------------\n\n");
                System.out.println("1 - " + Topicos.TOPICO_1 + "\n");
                System.out.println("2 - " + Topicos.TOPICO_2 + "\n");
                System.out.println("3 - " + Topicos.TOPICO_3 + "\n");
                System.out.println("4 - " + Topicos.TOPICO_4 + "\n");
                System.out.println("5 - " + Topicos.TOPICO_5 + "\n");
                Scanner entrada = new Scanner(System.in);
                topico = entrada.nextInt();
               
                switch(topico){
                    case 1:
                        retorno = inter1.publish(Topicos.TOPICO_1);
                        break;
                    case 2:
                        retorno = inter1.publish(Topicos.TOPICO_2);
                        break;
                    case 3:
                        retorno = inter1.publish(Topicos.TOPICO_3);
                        break;
                    case 4:
                        retorno = inter1.publish(Topicos.TOPICO_4);
                        break;
                    case 5:
                        retorno = inter1.publish(Topicos.TOPICO_5);
                        break;
                }
                if(retorno){                    
                    System.out.println("Publicado com sucesso!");
                } else {
                    System.out.println("Nao foi possivel publicar o conteudo.");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
}
