/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
public enum Topicos {
    TOPICO_1("Topico 1"), TOPICO_2("Topico 2"), TOPICO_3("Topico 3"), TOPICO_4("Topico 4"), TOPICO_5("Topico 5");
    
    private final String nome;
    
    Topicos(String nomeTopico){
        nome = nomeTopico;
    }
    
    public String getNome(){
        return nome;
    }
}
