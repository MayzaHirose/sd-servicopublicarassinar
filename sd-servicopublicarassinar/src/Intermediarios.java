/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mayza
 */
public enum Intermediarios {
    INTER_1("Intermediario 1"), INTER_2("Intermmediario 2"), INTER_3("Intermediario 3");
    
    private final String nome;
    
    Intermediarios(String nomeInter){
        nome = nomeInter;
    }
    
    public String getNome(){
        return nome;
    }
}
