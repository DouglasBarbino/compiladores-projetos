
package trabalho1;

import java.util.LinkedList;
import java.util.*;

/**
 *
 * @author (versao original): Daniel
 * @author (modificacoes): Grupo 3
 *Classe que representa a pilha de tabelas necessaria para a analise semantica.
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
    
   // metodo que procura em todas as tabelas da pilha um tipo a partir de um nome fornecido.
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
    
   //Metodo que recupera a lista de parametros associada a um nome de uma subrotina pela busca em todas as tabelas da pilha.
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
    
   //Metodo que recupera uma subtabela a partir de um tipo informado pela busca em todas as tabelas da pilha
    public TabelaDeSimbolos getSubtabela(String tipo)
    {
        for(int i = 0; i < pilha.size(); i++) {
            if(pilha.get(i).existeSimbolo(tipo)) {
                return pilha.get(i).getSubtabela(tipo);
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
