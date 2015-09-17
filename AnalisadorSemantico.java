/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

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
    
    public void programa() {
       // enterDeclaracao_local();
    }
    
    
    
    @Override
    public void enterDeclaracao_local(Declaracao_localContext ctx) {
           out.println("Declaracao linha ");
          
        if(ctx.variavel() != null) {
            String nomeVar = ctx.variavel().IDENT().getSymbol().getText();
            String tipoVar = ctx.variavel().tipo_var;
            int linha = ctx.variavel().IDENT().getSymbol().getLine();
            out.println("Declaracao linha "+linha);
            
            TabelaDeSimbolos tabelaDeSimbolosAtual = pilhaDeTabelas.topo();
                
		if(tipoVar == "literal" || tipoVar == "inteiro" || tipoVar == "real" || tipoVar == "logico")
		{
                    for (String nome : ctx.variavel().variaveis)
                    {
                        if(!tabelaDeSimbolosAtual.existeSimbolo(nomeVar))
                            tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar,tipoVar);
                        else //ERRO 1
                            out.println("identificador" +nomeVar+ "ja declarado anteriormente");
                    }
                    
		}else{
                    if(!tabelaDeSimbolosAtual.existeSimbolo(tipoVar))
		    {
			out.println("Linha : "+linha +  "tipo "+tipoVar+"  nao declarado");
                    }else{
                            for (String nome : ctx.variavel().variaveis)
                    {
                            if(!tabelaDeSimbolosAtual.existeSimbolo(nomeVar))
                        	tabelaDeSimbolosAtual.adicionarSimbolo(nomeVar,tipoVar);	
                            else //ERRO 1
                                out.println("identificador "+nomeVar+" ja declarado anteriormente");
                    }      
                    }
					
		}
            
        }
    }
    
    private String detectarTipo(LAParser.ExpressaoContext ctx) {
        return null;
    }
}
