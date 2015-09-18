/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author (versao original): Daniel
 * @author (modificacoes): Grupo 3
 */
public class EntradaTabelaDeSimbolos {
    private String nome, tipo;
    private List<String> parametros;
    
    public EntradaTabelaDeSimbolos(String nome, String tipo, List<String> parametros) 
    {
        this.nome = nome;
        this.tipo = tipo;
        this.parametros = parametros;
    }
    
    public String getNome() {
        return nome;
    }
    
    
    public String getTipo() {
        return tipo;
    }
    
    @Override
    public String toString() {
        return nome+"("+tipo+")";
    }
}
