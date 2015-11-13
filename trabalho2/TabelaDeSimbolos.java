
package trabalho3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author (versao original): Daniel
 * @author (modificacoes): Grupo 3
 */
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Classe tabela de simbolos representa um escopo. 
 */
public class TabelaDeSimbolos {
    private String escopo;
    private List<EntradaTabelaDeSimbolos> simbolos;
    
    public TabelaDeSimbolos(String escopo) {
        simbolos = new ArrayList<EntradaTabelaDeSimbolos>();
        this.escopo = escopo;
    }
    
    public void adicionarSimbolo(String nome, String tipo, List<String> parametros, TabelaDeSimbolos sub, int valor) {
        simbolos.add(new EntradaTabelaDeSimbolos(nome,tipo, parametros, sub, valor));
    }
    
    public void adicionarSimbolo(String nome, String tipo, List<String> parametros, TabelaDeSimbolos sub) {
        simbolos.add(new EntradaTabelaDeSimbolos(nome,tipo, parametros, sub));
    }
    
    public void adicionarSimbolos(List<String> nomes, String tipo, List<String> parametros, TabelaDeSimbolos sub, int valor) {
        for(String s:nomes) {
            simbolos.add(new EntradaTabelaDeSimbolos(s, tipo, parametros, sub, valor));
        }
    }
    
    //Metodo para recuperar um tipo associado a um nome por meio de uma pesquisa nos simbolos da tabela.	
    public String getTipo(String nome)
    {
        String tipo = null;
        for(int i = 0; i < simbolos.size(); i++) {
            if(simbolos.get(i).getNome().equals(nome)) {
                tipo = simbolos.get(i).getTipo();
            }
        }
        return tipo;
    }
    
    public String getEscopo()
    {
        return this.escopo;
    }
    
    //Metodo para recuperar uma subtabela a partir de um tipo informado nos simbolos da tabela
    public TabelaDeSimbolos getSubtabela(String tipo)
    {
        for(int i = 0; i < simbolos.size(); i++) {
            if(simbolos.get(i).getNome().equals(tipo)) {
                return simbolos.get(i).getsubTabela();
            }
        }
        
        return null;
    }
    
    //metodo para recuperar a lista de parametros associada a um nome de uma subrotina
    public List<String> getListaPar(String nomeSubrotina)
    {
        List<String> ListaPar = new ArrayList<String>();
        
        for(int i = 0; i < simbolos.size(); i++) {
            if(simbolos.get(i).getNome().equals(nomeSubrotina)) {
                ListaPar = simbolos.get(i).getListaPar();
                return ListaPar;
            }
        }
        
       return null; 
    }

        
    public boolean existeSimbolo(String nome) {
        for(EntradaTabelaDeSimbolos etds:simbolos) {
            if(etds.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean existePino(int valor) {
        for(EntradaTabelaDeSimbolos etds:simbolos) {
            if(etds.getValor() == valor) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        String ret = "Escopo: "+escopo;
        for(EntradaTabelaDeSimbolos etds:simbolos) {
            ret += "\n   "+etds;
        }
        return ret;
    }
}
