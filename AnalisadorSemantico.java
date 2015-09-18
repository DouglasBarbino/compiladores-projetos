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
    
    /* //IDEIA PARA O QUARTO
    @Override
    public void enterPrograma(ProgramaContext ctx) { 
    //2 jeitos para se chamar uma funcao: pela regra <chamada_atribuicao> ou pela regra <chamada_partes>
    //Verifica se a funcao que esta sendo chamada eh a que foi declarada, pois nao vamos comparar parametros de uma funcao com outra... :)
    //OBS: primeira parte if: chamada_atribuicao, segunda parte if: chamada_partes
    if ((ctx.declaracoes().ctx.decl_local_global().ctx.declaracao_global().IDENT().getSymbol().getText() ==
        ctx.corpo().ctx.comandos().ctx.cmd().IDENT().getSymbol().getText()) ||
        (ctx.declaracoes().ctx.decl_local_global().ctx.declaracao_global().IDENT().getSymbol().getText() ==
        ctx.corpo().ctx.comandos().ctx.cmd().ctx.expressao().ctx.termo_logico().ctx.fator_logico().ctx.parcela_logica().ctx.exp_relacional().ctx.exp_aritmetica().ctx.termo().ctx.fator().ctx.parcela().ctx.parcela_unario().IDENT().getSymbol().getText())){
        //eu sei que ja temos um return que percorre de parcela_unario ate expressao, porem como lah retorna uma lista e teria que procurar nela qual eh o nome da funcao entao ja fui buscar direto
        
        }
    }
    */
    
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
                            out.println("Linha "+linha+": identificador" +nomeVar+ "ja declarado anteriormente");
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
                                out.println("Linha "+linha+": identificador "+nomeVar+" ja declarado anteriormente");
                    }      
                    }
					
		}
            
        }
    }
    
     /* //IDEIA PARA O SEXTO
    @Override
    public void enterDeclaracao_global(Declaracao_globalContext ctx) { } { 
	int linha = ctx.comandos().ctx.cmd().ctx.match("retorne").getSymbol().getLine();
    
    	if (ctx.comandos().ctx.cmd().ctx.match("retorne") != null){ //Verifica se existe um retorne dentro de uma declaracao global
    	//duvida no ctx.match("retorne") => serah que nao eh algo do tipo .retorne() como o ident, ou entao .28() que eh o indice
    	// dele no public static final String[] tokenNames do LAParser
    		if (ctx.match("funcao") == null	){ //Verifica se o que chama o retorne nao eh uma funcao, ou seja, um procedimento
    			out.println("Linha "+linha+": comando retorne nao permitido nesse escopo");
    		}
    	}
    }
    // OS OUTROS JEITOS QUE TALVEZ PODEM ESTAR CERTOS (retorne):
    //ctx.comandos().ctx.cmd().retorne()
    //ctx.comandos().ctx.cmd().28()
    // (funcao):
    //.funcao()
    //.24()
    */
    

    private String detectarTipo(LAParser.ExpressaoContext ctx) {
        return null;
    }
}
