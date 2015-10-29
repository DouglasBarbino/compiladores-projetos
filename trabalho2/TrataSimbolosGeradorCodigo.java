/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3;

/**
 *
 * @author Douglas
 */
public class TrataSimbolosGeradorCodigo {
    public static String trataToken(FAZEDORESParser.Op_relacionalContext ctx) {
        String token = ctx.getStart().getText();
        
        if (token.equals("="))
            token = "==";
        else{
            if (token.equals("<>"))
                token = "!=";
        }
        
        return token;            
    }
    
    public static String trataToken(FAZEDORESParser.Outros_termos_logicosContext ctx) {
        String token = ctx.getStart().getText();
        
        if (token.equals("ou"))
            token = "||";
        
        return token;            
    }
    
    public static String trataToken(FAZEDORESParser.Outros_fatores_logicosContext ctx) {
        String token = ctx.getStart().getText();
        
        if (token.equals("e"))
            token = "&&";
        
        return token;            
    }
    
    public static String trataToken(FAZEDORESParser.Parcela_logicaContext ctx) {
        String token = ctx.getStart().getText();
        
        if (token.equals("verdadeiro"))
            token = "true";
        else{
            if (token.equals("falso"))
                token = "false";
        }
        
        return token;            
    }
}
