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
    private TabelaDeSimbolos subTabeladoRegistro;
    
    public EntradaTabelaDeSimbolos(String nome, String tipo, List<String> listaPassada, TabelaDeSimbolos sub) 
    {
        this.nome = nome;
        this.tipo = tipo;
        this.subTabeladoRegistro = sub;
        
        if(listaPassada == null)
        {
            this.parametros = listaPassada;
        }else
        {
            this.parametros = new ArrayList<String>();
        
            for(int i=0; i< listaPassada.size(); i++)
            {
               this.parametros.add(i,listaPassada.get(i));
            }
        }
        

//parametros;
        
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public TabelaDeSimbolos getsubTabela()
    {
        return this.subTabeladoRegistro;
    }
    
    public List<String> getListaPar()
    {
        return this.parametros;
    }
            
    
    public String getTipo() {
        return this.tipo;
    }
    
    @Override
    public String toString() {
        return nome+"("+tipo+")";
    }
}
