/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

import java.util.ArrayList;
import java.util.List;
import org.antlr.runtime.RecognitionException;
import trabalho1.LAParser.Declaracao_localContext;

/**
 *
 * @author gabriela
 */
public class AnalisadorSemantico extends LABaseListener {

    Saida out;

    PilhaDeTabelas pilhaDeTabelas = new PilhaDeTabelas();
    
    public AnalisadorSemantico(Saida out) {
        this.out = out;
    }

    @Override
    public void enterPrograma(LAParser.ProgramaContext ctx) {
        
        pilhaDeTabelas.empilhar(new TabelaDeSimbolos("global"));
        
        
    }
    
 
    
    @Override
    public void enterDeclaracao_local(Declaracao_localContext ctx) {
           
        if(ctx.variavel() != null) {
            String nomeVar = ctx.variavel().IDENT().getSymbol().getText();
            String tipoVar = ctx.variavel().tipo_var;
            int linha = ctx.variavel().IDENT().getSymbol().getLine();
            
            TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
                
		if(tipoVar == "literal" || tipoVar == "inteiro" || tipoVar == "real" || tipoVar == "logico")
		{
                    for (String nome : ctx.variavel().variaveis)
                    {
                        if(!tabelaDeSimbolosAtual.existeSimbolo(nomeVar))
                            tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar,tipoVar, null);
                        else //ERRO 1
                            out.println("Linha "+linha+": identificador " +nomeVar+ " ja declarado anteriormente");
                    }
                    
		}else{
                    if(!tabelaDeSimbolosAtual.existeSimbolo(tipoVar))
		    {
			out.println("Linha "+linha +  ": tipo "+tipoVar+" nao declarado");
                    }else{
                            for (String nome : ctx.variavel().variaveis)
                    {
                            if(!tabelaDeSimbolosAtual.existeSimbolo(nomeVar))
                        	tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar,tipoVar, null);	
                            else //ERRO 1
                                out.println("Linha "+linha+": identificador "+nomeVar+" ja declarado anteriormente");
                    }      
                    }
					
		}
            
        }
    }
    
    @Override
    public void enterCmd(LAParser.CmdContext ctx)
    {
        if(ctx.getStart().getText().equals("leia"))
        {
            String nomeVar = ctx.identificador().primParametro;
            int linha = ctx.identificador().IDENT().getSymbol().getLine();
            
            TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
            
            if(!tabelaDeSimbolosAtual.existeSimbolo(nomeVar))
            {
                 out.println("Linha "+linha+": identificador "+nomeVar+" não declarado");
            }
            
        }
    }
    
    @Override
    public void enterDeclaracao_global(LAParser.Declaracao_globalContext ctx)
    {
        String nome = ctx.IDENT().getText();
        
        if (ctx.getStart().getText().equals("procedimento"))
        {
            
            List<String> listaPar = new ArrayList<String>();
            TabelaDeSimbolos tabelaDeSimbolosGlobal = pilhaDeTabelas.topo();
                
            if(!tabelaDeSimbolosGlobal.existeSimbolo(nome))
            {
                TabelaDeSimbolos tabelaDeSimbolosProcedimento = new TabelaDeSimbolos("procedimento "+nome);
                
                for (int i=0; i<ctx.parametros_opcional().parametros.size(); i++)      
                {
                    String nomePar = ctx.parametros_opcional().parametros.get(i);
                    String tipoPar = ctx.parametros_opcional().tipo_parametros.get(i);
                    
                    if(!tabelaDeSimbolosGlobal.existeSimbolo(nomePar) && tabelaDeSimbolosProcedimento.existeSimbolo(nomePar))
                    {
                        tabelaDeSimbolosProcedimento.adicionarSimbolo(nomePar, tipoPar, null);
                        listaPar.add(tipoPar);
                    }
                    else
                    {
                        out.println("identificador "+nomePar+" ja declarado anteriormente");
                    }
                }
                tabelaDeSimbolosGlobal.adicionarSimbolo(nome, "procedimento", listaPar);
                   
                pilhaDeTabelas.empilhar(tabelaDeSimbolosProcedimento);  

                }
            }
            else //ERRO 1
                out.println("identificador "+nome+" ja declarado anteriormente");
            
    }
    
    

    private String detectarTipo(LAParser.ExpressaoContext ctx) {
       // String tipo = ctx.nome_par; //descobrir como atribuir lista
        //atribuir nome_par a uma lista
        //percorrer essa lista na tabela de simbolos e recuperar os tipos de cada nome
         String tipo = null;
         TabelaDeSimbolos tabelaDeSimbolosAtual2 = pilhaDeTabelas.topo();
        
        for (String nome : ctx.nome_par)
        {
            if (nome != null)
            {
                
                //ctx.tipo_par.add(tipo);
            }
        }
        return tipo;
    }
    
    @Override
    public void enterArgumentos_opcional(LAParser.Argumentos_opcionalContext ctx)
    {
        //percorrer a lista de nomes 
    }
}


