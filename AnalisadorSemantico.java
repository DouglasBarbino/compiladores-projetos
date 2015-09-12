/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

import trabalho1.LAParser.Declaracao_localContext;

/**
 *
 * @author gabriela
 */
public class AnalisadorSemantico extends LABaseListener {

    Saida out;

    public AnalisadorSemantico(Saida out) {
        this.out = out;
    }
    
    
    
    @Override
    public void enterDeclaracao_local(Declaracao_localContext ctx) {
        if(ctx.variavel() != null) {
            String nomeVar = ctx.variavel().IDENT().getSymbol().getText();
            String tipoVar = ctx.variavel().tipo_var;
            int linha = ctx.variavel().IDENT().getSymbol().getLine();
            out.println("Declaracao linha "+linha);
        }
    }
    
    private String detectarTipo(LAParser.ExpressaoContext ctx) {
        return null;
    }
}
