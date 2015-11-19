/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;

import java.util.LinkedList;
import java.util.*;

/**
 *
 * @author (versao original): Daniel
 * @author (modificacoes): Grupo 3
 */
public class PilhaDeTabelas {

    private LinkedList<TabelaDeSimbolos> pilha;

    public PilhaDeTabelas() {
        pilha = new LinkedList<TabelaDeSimbolos>();
    }

    public void empilhar(TabelaDeSimbolos ts) {
        pilha.push(ts);
    }

    public TabelaDeSimbolos topo() {
        return pilha.peek();
    }
    
    public String getTipo(String nome)
    {
        String tipo = null;
        for(int i = 0; i < pilha.size(); i++) {
            if(pilha.get(i).getTipo(nome)!=null) {
                tipo = pilha.get(i).getTipo(nome);
            }
        }
        
        return tipo;
    }
    
    public String getValor(String nome)
    {
        String valor = null;
        for(int i = 0; i < pilha.size(); i++) {
            if(pilha.get(i).getValor(nome)!=null) {
                valor = pilha.get(i).getValor(nome);
            }
        }
        
        return valor;
    }
    
    public List<String> getListaPar(String subRotina)
    {
        List<String> parametros = new ArrayList<String>();
        for(int i = 0; i < pilha.size(); i++) {
            if(pilha.get(i).existeSimbolo(subRotina)) {
                return pilha.get(i).getListaPar(subRotina);
            }
        }
        
        return parametros;
    }

    public boolean existeSimbolo(String nome) {
        for (TabelaDeSimbolos ts : pilha) {
            if (ts.existeSimbolo(nome)) {
                return true;
            }
        }
        return false;
    }
    
    public TabelaDeSimbolos getSubtabela(String tipo)
    {
        for(int i = 0; i < pilha.size(); i++) {
            if(pilha.get(i).existeSimbolo(tipo)) {
                return pilha.get(i).getSubtabela(tipo);
            }
        }
        
        return null;
    }
    
    public TabelaDeSimbolos getTabela(String escopo)
    {
        for(int i = 0; i < pilha.size(); i++) {
            if(pilha.get(i).getEscopo().equals(escopo)) {
                return pilha.get(i);
            }
        }
        
        return null;
    }

    public void desempilhar() {
        TabelaDeSimbolos ret = pilha.pop();
    }

    public List getTodasTabelas() {
        return pilha;
    }
}
