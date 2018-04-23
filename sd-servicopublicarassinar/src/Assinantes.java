/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
public enum Assinantes {
    ASSINANTE_1("Assinante 1"), ASSINANTE_2("Assinante 2"), ASSINANTE_3("Assinante 3");
    
    private final String nome;
    
    Assinantes(String nomeAssinante){
        nome = nomeAssinante;
    }
    
    public String getNome(){
        return nome;
    }
}
