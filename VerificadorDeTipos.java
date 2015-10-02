/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho1;

/**
 *
 * @author Breno
 */
public class VerificadorDeTipos {
    public static String verificaTipo(LAParser.ExpressaoContext ctx) {
        String tipoExp = verificaTipo(ctx.termo_logico());
        
        if(ctx.outros_termos_logicos() == null) {
            return tipoExp;
        } else {
            for(LAParser.Termo_logicoContext termo: ctx.outros_termos_logicos().termo_logico()) {
                String tipoOutroTermo = verificaTipo(termo);
                tipoExp = regraTipos(tipoExp, tipoOutroTermo);
            }
        }
        
        return tipoExp;
    }
    
     public static String verificaTipo(LAParser.Termo_logicoContext ctx) {
         String tipoTermoLogico = verificaTipo(ctx.fator_logico());
        
        if(ctx.outros_fatores_logicos() == null) {
            return tipoTermoLogico;
        } else {
            for(LAParser.Fator_logicoContext termo: ctx.outros_fatores_logicos().fator_logico()) {
                String tipoOutroTermo = verificaTipo(termo);
                tipoTermoLogico = regraTipos(tipoTermoLogico, tipoOutroTermo);
            }
        }
        
        return tipoTermoLogico;
     }
     
    public static String verificaTipo(LAParser.Fator_logicoContext ctx) {
        String tipof_logico = verificaTipo(ctx.parcela_logica());
        
        return tipof_logico;
    }
    
    public static String verificaTipo(LAParser.Parcela_logicaContext ctx) {
        String tipoP_logica;
        if(ctx.getStart().getText().equals("verdadeiro") || ctx.getStart().getText().equals("falso"))
            tipoP_logica = "logico";
        else
            tipoP_logica = verificaTipo(ctx.exp_relacional());
        return tipoP_logica;
    }
    
    public static String verificaTipo(LAParser.Exp_relacionalContext ctx) {
        String tipoExp_rel = "tipo_invalido";
        if(ctx.op_opcional() != null)
        {
            if(ctx.op_opcional().op_relacional()!=null)
            {
                tipoExp_rel = "logico";
            }else{
                tipoExp_rel = verificaTipo(ctx.exp_aritmetica());
            }
        }    
        else
            tipoExp_rel = verificaTipo(ctx.exp_aritmetica());
        
        return tipoExp_rel;  
    }
    
    public static String verificaTipo(LAParser.Exp_aritmeticaContext ctx) {
        String tipoExp_ar = verificaTipo(ctx.termo());
        
        if(ctx.outros_termos()== null) {
            return tipoExp_ar;
        } else {
            for(LAParser.TermoContext termo: ctx.outros_termos().termo()) {
                String tipoOutroTermo = verificaTipo(termo);
                tipoExp_ar = regraTipos(tipoExp_ar, tipoOutroTermo);
            }
        }
        
        return tipoExp_ar;
    }
    
    public static String verificaTipo(LAParser.TermoContext ctx) {
        String tipotermo = verificaTipo(ctx.fator());
        
        if(ctx.outros_fatores() == null) {
            return tipotermo;
        } else {
            for(LAParser.FatorContext termo: ctx.outros_fatores().fator()) {
                String tipoOutroTermo = verificaTipo(termo);
                tipotermo = regraTipos(tipotermo, tipoOutroTermo);
            }
        }
        
        return tipotermo;
    }
    
    public static String verificaTipo(LAParser.FatorContext ctx) {
        String tipofator = verificaTipo(ctx.parcela());
        
        if(ctx.outras_parcelas() == null) {
            return tipofator;
        } else {
            for(LAParser.ParcelaContext termo: ctx.outras_parcelas().parcela()) {
                String tipoOutroTermo = verificaTipo(termo);
                tipofator = regraTipos(tipofator, tipoOutroTermo);
            }
        }
        
        return tipofator;
    }
    
    public static String verificaTipo(LAParser.ParcelaContext ctx) {
        String tipo;
//        if(ctx.getStart().getText().equals("%"))
//        {
//            tipo = "inteiro";
//        }else
//        {
            if(ctx.parcela_unario()!=null)
            {
                tipo = verificaTipo(ctx.parcela_unario());
            }else{
                tipo = verificaTipo(ctx.parcela_nao_unario());
            }
        //}
        
        
        return tipo;
    }
    
    public static String verificaTipo(LAParser.Parcela_unarioContext ctx) {
        String tipo = "tipo_invalido";
        if(ctx.NUM_INT() != null)
        {
            tipo = "inteiro";
        }else
        {
            if(ctx.NUM_REAL()!= null)
            {
                tipo = "real";
            }else
            {
                PilhaDeTabelas atual = AnalisadorSemantico.pilhaDeTabelas;
                if(ctx.outros_ident()!=null)
                {
                    if(ctx.outros_ident().identificador()!=null)
                    {
                        String nome = ctx.IDENT().getText();
                        String tipo1 = atual.getTipo(nome);
                        TabelaDeSimbolos TabelaReg = atual.getSubtabela(tipo1);
                        nome = ctx.outros_ident().identificador().IDENT().getText();
                        tipo = TabelaReg.getTipo(nome);
                    }
                    
                }else{
                    if(ctx.chamada_partes()!=null)
                    {
                        if(ctx.chamada_partes().outros_ident()!=null)
                        {    if(ctx.chamada_partes().outros_ident().identificador()!=null)
                            {
                                String nome = ctx.IDENT().getText();
                                String tipo1 = atual.getTipo(nome);
                                TabelaDeSimbolos TabelaReg = atual.getSubtabela(tipo1);
                                nome = ctx.chamada_partes().outros_ident().identificador().IDENT().getText();
                                tipo = TabelaReg.getTipo(nome);
                            }else{
                                if(ctx.IDENT() != null)
                                {
                                    String nome = ctx.IDENT().getText();
                                    tipo = atual.getTipo(nome);
                                }else
                                {
                                    if(ctx.getStart().getText().equals("("))
                                    {
                                        tipo = verificaTipo(ctx.expressao());
                                    }
                                }
                            }
                        }else{
                                if(ctx.IDENT() != null)
                                {
                                    String nome = ctx.IDENT().getText();
                                    tipo = atual.getTipo(nome);
                                }else
                                {
                                    if(ctx.getStart().getText().equals("("))
                                    {
                                        tipo = verificaTipo(ctx.expressao());
                                    }
                                }
                            }
                    }
                    else{
                        
                    
                    if(ctx.IDENT() != null)
                    {
                        String nome = ctx.IDENT().getText();
                        tipo = atual.getTipo(nome);
                    }else
                    {
                        if(ctx.getStart().getText().equals("("))
                        {
                            tipo = verificaTipo(ctx.expressao());
                        }
                    }
                }
                }
                
               
            }
        }
        
        return tipo;
    }
    
    public static String verificaTipo(LAParser.Parcela_nao_unarioContext ctx) {
        String tipo = "tipo_invalido";
        if(ctx.CADEIA() != null)
        {
            tipo = "literal";
        }else
        {
            PilhaDeTabelas atual = AnalisadorSemantico.pilhaDeTabelas;
            if(ctx.outros_ident()!=null)
            {
                if(ctx.outros_ident().identificador()!=null)
                {
                    String nome = ctx.IDENT().getText();
                    String tipo1 = atual.getTipo(nome);
                    TabelaDeSimbolos TabelaReg = atual.getSubtabela(tipo1);
                    nome = ctx.outros_ident().identificador().IDENT().getText();
                    tipo = TabelaReg.getTipo(nome);
                }else{
                    if(ctx.IDENT() != null)
                    {
                        String nome = ctx.IDENT().getText();
                        tipo = atual.getTipo(nome);
                    }
                }
            }else{
                if(ctx.IDENT() != null)
                {
                   String nome = ctx.IDENT().getText();
                   tipo = atual.getTipo(nome);
                }
            
        } 
        }
        
        return tipo;
    }
    

    private static String regraTipos(String tipoExp, String tipoOutroTermo) {
        if(tipoExp.equals(tipoOutroTermo)) {
            return tipoExp;
        } else if(tipoExp.equals("real") && tipoOutroTermo.equals("inteiro") ||
                tipoExp.equals("inteiro") && tipoOutroTermo.equals("real")) {
            return "real";
        } else {
            return "tipo_invalido";
        }
    }
}
